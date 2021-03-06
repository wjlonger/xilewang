package com.wuwei.provider.wechat.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.util.AES;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.base.wechat.service.XiLeWangUserService;
import com.wuwei.provider.wechat.mapper.XiLeWangUserMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@Service("xiLeWangUserService")
public class XiLeWangUserServiceImpl implements XiLeWangUserService {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private XiLeWangUserMapper xiLeWangUserMapper;

    @Override
    public String code2Session(String code,String inviteCode) {
        if(StringUtils.isEmpty(code)){
            return null;
        }
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        XiLeWangUser xiLeWangUser = null;
        try {
            String httpUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", AES.decode(appId), AES.decode(secret),code);
            // 创建远程url连接对象
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                if(null == (xiLeWangUser = JSONObject.parseObject(sbf.toString(),XiLeWangUser.class))){
                    return null;
                }
                xiLeWangUser.setMasterOpenid(inviteCode);
                amqpTemplate.convertAndSend("xilewang_user_save",xiLeWangUser);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                }finally {
                    br = null;
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                } finally {
                    is = null;
                }
            }
            if(connection != null){
                connection.disconnect();// 关闭远程连接
                connection = null;
            }
        }
        return xiLeWangUser.getOpenid();
    }

    @Override
    public XiLeWangUser selectByPrimaryKey(String openid) {
        if(StringUtils.isEmpty(openid)){
            return null;
        }
        return xiLeWangUserMapper.selectByPrimaryKey(openid);
    }

    @Override
    public int insert(XiLeWangUser xiLeWangUser) {
        if(null == xiLeWangUser || StringUtils.isEmpty(xiLeWangUser.getOpenid())){
            return 0;
        }
        xiLeWangUser.setGmtCreate(new Date());
        xiLeWangUser.setGmtModified(new Date());
        return xiLeWangUserMapper.insert(xiLeWangUser);
    }

    @Override
    public int insertSelective(XiLeWangUser xiLeWangUser) {
        if(null == xiLeWangUser || StringUtils.isEmpty(xiLeWangUser.getOpenid())){
            return 0;
        }
        xiLeWangUser.setGmtCreate(new Date());
        xiLeWangUser.setGmtModified(new Date());
        return xiLeWangUserMapper.insertSelective(xiLeWangUser);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangUser xiLeWangUser) {
        if(null == xiLeWangUser || StringUtils.isEmpty(xiLeWangUser.getOpenid())){
            return 0;
        }
        xiLeWangUser.setGmtModified(new Date());
        return xiLeWangUserMapper.updateByPrimaryKey(xiLeWangUser);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangUser xiLeWangUser) {
        if(null == xiLeWangUser || StringUtils.isEmpty(xiLeWangUser.getOpenid())){
            return 0;
        }
        xiLeWangUser.setGmtModified(new Date());
        return xiLeWangUserMapper.updateByPrimaryKeySelective(xiLeWangUser);
    }

    @Override
    public int save(XiLeWangUser xiLeWangUser) {
        if(null == xiLeWangUser || StringUtils.isEmpty(xiLeWangUser.getOpenid())){
            return 0;
        }
        if(null == this.selectByPrimaryKey(xiLeWangUser.getOpenid())){
            return this.insertSelective(xiLeWangUser);
        }
        return this.updateByPrimaryKeySelective(xiLeWangUser);
    }

    @Override
    public int updateMoneyByPrimaryKey(int type, BigDecimal modifyMoney, String openid) {
        switch (type){
            case -1:
                // 提现佣金
                return xiLeWangUserMapper.updateMoneyByPrimaryKey(modifyMoney,openid,new Date());
            case 0:
                // 返利佣金
                return xiLeWangUserMapper.updateRebateMoneyByPrimaryKey(modifyMoney,openid,new Date());
            case 1:
                // 助力奖励
                return xiLeWangUserMapper.updateAssistanceMoneyByPrimaryKey(modifyMoney,openid,new Date());
            case 2:
                // 师徒奖励
                return xiLeWangUserMapper.updateMasterMoneyByPrimaryKey(modifyMoney,openid,new Date());
            default:
                return 0;
        }
    }

    @Override
    public int inviteCount(String openid) {
        if(StringUtils.isEmpty(openid)){
            return 0;
        }
        return xiLeWangUserMapper.inviteCount(openid);
    }

    @Override
    public PageInfo<XiLeWangUser> listByMasterOpenid(String openid, Integer pageNo, Integer pageSize) {
        PageInfo<XiLeWangUser> xiLeWangUserPageInfo = PageHelper.startPage(pageNo, pageSize).setOrderBy("gmt_create desc")
                .doSelectPageInfo(()->this.xiLeWangUserMapper.listByMasterOpenid(openid));
        return xiLeWangUserPageInfo;
    }

}
