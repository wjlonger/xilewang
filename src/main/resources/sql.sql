/*
Navicat MySQL Data Transfer

Source Server         : localhost-root
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : xilewang

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-06 11:04:59
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
