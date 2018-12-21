/*
Navicat MySQL Data Transfer

Source Server         : localhost-root
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : xilewang

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-21 11:49:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(120) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`),
  KEY `idx_qrtz_ft_trig_inst_name` (`sched_name`,`instance_name`),
  KEY `idx_qrtz_ft_inst_job_req_rcvry` (`sched_name`,`instance_name`,`requests_recovery`),
  KEY `idx_qrtz_ft_j_g` (`sched_name`,`job_name`,`job_group`),
  KEY `idx_qrtz_ft_jg` (`sched_name`,`job_group`),
  KEY `idx_qrtz_ft_t_g` (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `idx_qrtz_ft_tg` (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`),
  KEY `idx_qrtz_j_req_recovery` (`sched_name`,`requests_recovery`),
  KEY `idx_qrtz_j_grp` (`sched_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `idx_qrtz_t_j` (`sched_name`,`job_name`,`job_group`),
  KEY `idx_qrtz_t_jg` (`sched_name`,`job_group`),
  KEY `idx_qrtz_t_c` (`sched_name`,`calendar_name`),
  KEY `idx_qrtz_t_g` (`sched_name`,`trigger_group`),
  KEY `idx_qrtz_t_state` (`sched_name`,`trigger_state`),
  KEY `idx_qrtz_t_n_state` (`sched_name`,`trigger_name`,`trigger_group`,`trigger_state`),
  KEY `idx_qrtz_t_n_g_state` (`sched_name`,`trigger_group`,`trigger_state`),
  KEY `idx_qrtz_t_next_fire_time` (`sched_name`,`next_fire_time`),
  KEY `idx_qrtz_t_nft_st` (`sched_name`,`trigger_state`,`next_fire_time`),
  KEY `idx_qrtz_t_nft_misfire` (`sched_name`,`misfire_instr`,`next_fire_time`),
  KEY `idx_qrtz_t_nft_st_misfire` (`sched_name`,`misfire_instr`,`next_fire_time`,`trigger_state`),
  KEY `idx_qrtz_t_nft_st_misfire_grp` (`sched_name`,`misfire_instr`,`next_fire_time`,`trigger_group`,`trigger_state`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_union
-- ----------------------------
DROP TABLE IF EXISTS `wx_union`;
CREATE TABLE `wx_union` (
  `unionid` varchar(50) NOT NULL,
  PRIMARY KEY (`unionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wx_union_xilewang
-- ----------------------------
DROP TABLE IF EXISTS `wx_union_xilewang`;
CREATE TABLE `wx_union_xilewang` (
  `unionid` varchar(50) NOT NULL,
  `openid` varchar(50) NOT NULL,
  PRIMARY KEY (`unionid`,`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_assistance
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_assistance`;
CREATE TABLE `xilewang_assistance` (
  `id` bigint(20) unsigned NOT NULL COMMENT '自定义主键',
  `openid` varchar(50) NOT NULL DEFAULT '' COMMENT '小程序用户唯一ID',
  `sku_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品id',
  `initial_ratio` decimal(4,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '返利比例',
  `assistance_ratio` varchar(50) NOT NULL DEFAULT '12,12,12' COMMENT '分配助力比例',
  `assistance_people_num` tinyint(3) unsigned NOT NULL DEFAULT '3' COMMENT '助力人数',
  `state` int(18) unsigned NOT NULL DEFAULT '0' COMMENT '0 助力中  1已完成',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_openid_skuid` (`openid`,`sku_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_assistance_user
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_assistance_user`;
CREATE TABLE `xilewang_assistance_user` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键ID',
  `openid` varchar(50) NOT NULL DEFAULT '' COMMENT '微信小程序用户唯一ID',
  `nick_name` varchar(20) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar_url` varchar(500) NOT NULL DEFAULT '' COMMENT '头像',
  `assistance_ratio` decimal(4,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '助力比例',
  `reward_ratio` decimal(4,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '奖励比例',
  `assistance_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'xilewang_assistance表主键',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_coupon
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_coupon`;
CREATE TABLE `xilewang_coupon` (
  `id` bigint(20) unsigned NOT NULL COMMENT '自增id',
  `bind_type` int(1) unsigned NOT NULL COMMENT '券种类 (优惠券种类：0 - 全品类，1 - 限品类（自营商品），2 - 限店铺，3 - 店铺限商品券)',
  `discount` double(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '券面额',
  `link` varchar(255) NOT NULL COMMENT '券链接',
  `platform_type` int(1) unsigned NOT NULL COMMENT '券使用平台 (平台类型：0 - 全平台券，1 - 限平台券)',
  `quota` double(18,2) unsigned NOT NULL COMMENT '券消费限额',
  `get_start_time` int(18) unsigned NOT NULL COMMENT '领取开始时间(时间戳，毫秒)',
  `get_end_time` int(18) unsigned NOT NULL COMMENT '券领取结束时间(时间戳，毫秒)',
  `use_start_time` int(18) unsigned NOT NULL COMMENT '券有效使用开始时间(时间戳，毫秒)',
  `use_end_time` int(18) unsigned NOT NULL COMMENT '券有效使用结束时间(时间戳，毫秒)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_favorite
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_favorite`;
CREATE TABLE `xilewang_favorite` (
  `openid` varchar(50) NOT NULL COMMENT 'openid',
  `sku_id` bigint(20) unsigned NOT NULL COMMENT '商品skuid',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0 正常 1 删除',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`openid`,`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_goods
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_goods`;
CREATE TABLE `xilewang_goods` (
  `sku_id` int(18) unsigned NOT NULL COMMENT '商品ID',
  `sku_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
  `brand_code` varchar(100) NOT NULL DEFAULT '' COMMENT '品牌code',
  `brand_name` varchar(100) NOT NULL DEFAULT '' COMMENT '品牌名',
  `owner` varchar(1) NOT NULL DEFAULT '' COMMENT 'g=自营，p=pop',
  `cid1_id` int(18) unsigned NOT NULL DEFAULT '0' COMMENT '一级类目ID',
  `cid2_id` int(18) unsigned NOT NULL DEFAULT '0' COMMENT '二级类目ID',
  `cid3_id` int(18) unsigned NOT NULL DEFAULT '0' COMMENT '三级类目ID',
  `is_jd_sale` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否自营 (1 : 是, 0 : 否)，后续会废弃，请用owner',
  `material_url` varchar(100) NOT NULL DEFAULT '' COMMENT '商品落地页',
  `spuid` int(18) unsigned NOT NULL DEFAULT '0' COMMENT 'spuid，其值为同款商品的主skuid',
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_history
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_history`;
CREATE TABLE `xilewang_history` (
  `id` bigint(20) unsigned NOT NULL COMMENT '自定义主键',
  `openid` varchar(50) NOT NULL DEFAULT '' COMMENT 'user表openid',
  `sku_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '京东商品id',
  `sku_name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
  `shop_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `shop_name` varchar(50) NOT NULL DEFAULT '' COMMENT '店铺名称',
  `category_one_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '一级分类id',
  `category_one_name` varchar(20) NOT NULL DEFAULT '' COMMENT '一级分类名称',
  `category_two_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '二级分类id',
  `category_two_name` varchar(20) NOT NULL DEFAULT '' COMMENT '二级分类名称',
  `category_three_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '三级分类id',
  `category_three_name` varchar(20) NOT NULL DEFAULT '' COMMENT '三级分类名称',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `stay_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '停留时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_income_report
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_income_report`;
CREATE TABLE `xilewang_income_report` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键id',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '类型（-1：提现  0：返利 1：助力 2：师徒）',
  `valid_code` int(3) NOT NULL DEFAULT '0' COMMENT '状态,遵从京东validCode',
  `openid` varchar(50) NOT NULL DEFAULT '' COMMENT '小程序openid',
  `sku_info_id` bigint(20) unsigned NOT NULL COMMENT '京东skuinfo表的id',
  `money` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '收益',
  `state` int(3) NOT NULL DEFAULT '0' COMMENT '-1失效 0 已写入收入明细 待写入余额  1已写入余额',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_openid_skuinfoid` (`openid`,`sku_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_jd_order
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_jd_order`;
CREATE TABLE `xilewang_jd_order` (
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单ID',
  `finish_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单完成时间(时间戳，毫秒)',
  `order_emt` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '下单设备(1:PC,2:无线)',
  `order_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '下单时间(时间戳，毫秒)',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父单的订单ID，仅当发生订单拆分时返回， 0：未拆分，有值则表示此订单为子订单',
  `pay_month` varchar(50) NOT NULL DEFAULT '0' COMMENT '结算时间（格式：yyyyMMdd），0：未结算',
  `plus` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '下单用户是否为PLUS会员 0：否，1：是',
  `pop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商家ID',
  `union_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '推客的联盟ID',
  `ext1` varchar(100) NOT NULL DEFAULT '' COMMENT '推客生成推广链接时传入的扩展字段，订单维度（需要联系运营开放白名单才能拿到数据）',
  `valid_code` int(18) NOT NULL DEFAULT '0' COMMENT '订单维度的有效码（-1：未知,2.无效-拆单,3.无效-取消,4.无效-京东帮帮主订单,5.无效-账号异常,6.无效-赠品类目不返佣,7.无效-校园订单,8.无效-企业订单,9.无效-团购订单,10.无效-开增值税专用发票订单,11.无效-乡村推广员下单,12.无效-自己推广自己下单,13.无效-违规订单,14.无效-来源与备案网址不符,15.待付款,16.已付款,17.已完成,18.已结算）',
  `openid` varchar(50) NOT NULL DEFAULT '' COMMENT '小程序用户id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_jd_order_skuinfo
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_jd_order_skuinfo`;
CREATE TABLE `xilewang_jd_order_skuinfo` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键id',
  `actual_cos_price` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '实际计算佣金的金额（订单完成时，会将误扣除的运费券金额更正）',
  `actual_fee` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '推客获得的实际佣金（实际计佣金额*佣金比例*最终比例）',
  `commission_rate` decimal(5,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '佣金比例',
  `estimate_cos_price` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '预估计佣金额，即用户下单的金额(已扣除优惠券、白条、支付优惠、进口税，未扣除红包和京豆)，有时会误扣除运费券金额，完成结算时会在实际计佣金额中更正',
  `estimate_fee` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '推客的预估佣金（预估计佣金额*佣金比例*最终比例）',
  `final_rate` decimal(5,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '最终比例（分成比例+补贴比例）',
  `cid1` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '一级类目ID',
  `frozen_sku_num` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品售后中数量',
  `price` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '商品单价',
  `cid2` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '二级类目ID',
  `sku_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品ID',
  `sku_name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
  `sku_num` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品数量',
  `sku_return_num` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品已退货数量',
  `sub_side_rate` decimal(5,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '分成比例',
  `subsidy_rate` decimal(5,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '补贴比例',
  `cid3` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '三级类目ID',
  `valid_code` int(11) NOT NULL DEFAULT '0' COMMENT 'sku维度的有效码（-1：未知,2.无效-拆单,3.无效-取消,4.无效-京东帮帮主订单,5.无效-账号异常,6.无效-赠品类目不返佣,7.无效-校园订单,8.无效-企业订单,9.无效-团购订单,10.无效-开增值税专用发票订单,11.无效-乡村推广员下单,12.无效-自己推广自己下单,13.无效-违规订单,14.无效-来源与备案网址不符,15.待付款,16.已付款,17.已完成,18.已结算',
  `sub_union_id` varchar(100) NOT NULL DEFAULT '' COMMENT '子联盟ID(需要联系运营开放白名单才能拿到数据)',
  `img` varchar(500) NOT NULL DEFAULT '' COMMENT '图片',
  `rebate_price` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '返利金额',
  `jd_order_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'xilewang_jd_order表id',
  `sku_index` int(11) unsigned NOT NULL COMMENT '商品的index',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '-1 无返利 0 已存入skuInfo表 待计算佣金  1已计算佣金  待写入incomereport表 2已写入incomereport表 ',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_jdOrderId_skuIndex` (`jd_order_id`,`sku_index`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_order
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_order`;
CREATE TABLE `xilewang_order` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键ID',
  `openid` varchar(50) NOT NULL DEFAULT '' COMMENT 'xilewang_user表openid',
  `sku_id` bigint(20) unsigned NOT NULL COMMENT '京东商品skuid',
  `initial_ratio` decimal(4,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '初始分成比例',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '推广链接',
  `assistance_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'xilewang_assistance表主键',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xilewang_user
-- ----------------------------
DROP TABLE IF EXISTS `xilewang_user`;
CREATE TABLE `xilewang_user` (
  `openid` varchar(50) NOT NULL COMMENT '小程序用户ID',
  `nick_name` varchar(20) NOT NULL DEFAULT '' COMMENT '微信昵称',
  `avatar_url` varchar(500) NOT NULL DEFAULT '' COMMENT '微信头像',
  `gender` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '性别',
  `language` varchar(50) NOT NULL DEFAULT '' COMMENT '语言',
  `province` varchar(50) NOT NULL DEFAULT '' COMMENT '省份',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  `country` varchar(50) NOT NULL DEFAULT '' COMMENT '国家',
  `sessionkey` varchar(50) NOT NULL DEFAULT '' COMMENT 'sessionkey',
  `master_openid` varchar(50) NOT NULL DEFAULT '' COMMENT '师傅openid',
  `money` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `rebate_money` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '返利金额',
  `assistance_money` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '助力获得奖金',
  `master_money` decimal(18,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '师徒奖励',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
