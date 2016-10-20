/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : jxcdb

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2016-01-15 09:15:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `comId` varchar(4) NOT NULL,
  `comName` varchar(200) NOT NULL,
  `comType` varchar(2) NOT NULL,
  PRIMARY KEY  (`comId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('A001', '鲁东大学', 'B');
INSERT INTO `company` VALUES ('A002', '烟台大学', 'B');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsId` varchar(10) NOT NULL,
  `goodsName` varchar(100) NOT NULL,
  `goodsCom` varchar(4) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY  (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('P004', '液压钳', 'A001', '55');

-- ----------------------------
-- Table structure for `po_details`
-- ----------------------------
DROP TABLE IF EXISTS `po_details`;
CREATE TABLE `po_details` (
  `id` int(11) NOT NULL auto_increment,
  `parts_name` varchar(255) default NULL,
  `parts_brand` varchar(255) default NULL,
  `price` double default NULL,
  `amount` int(11) default NULL,
  `po_no` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of po_details
-- ----------------------------
INSERT INTO `po_details` VALUES ('1', '齿轮', '三一重工', '3000', '2', 'CG20151011001');
INSERT INTO `po_details` VALUES ('2', '轴承', '柳工机械', '4800', '1', 'CG20151011001');
INSERT INTO `po_details` VALUES ('3', '钻头', '柳工机械', '4500', '1', 'CG20151011001');

-- ----------------------------
-- Table structure for `po_head`
-- ----------------------------
DROP TABLE IF EXISTS `po_head`;
CREATE TABLE `po_head` (
  `no` varchar(30) NOT NULL default '',
  `order_time` datetime default NULL,
  `supplier_name` varchar(255) default NULL,
  `linkman` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `operator` varchar(255) default NULL,
  PRIMARY KEY  (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of po_head
-- ----------------------------
INSERT INTO `po_head` VALUES ('CG20151011001', '2015-10-11 15:22:56', '宏达机械', '王平', '13299001121', '李近平');

-- ----------------------------
-- Table structure for `sys_depart`
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart`;
CREATE TABLE `sys_depart` (
  `depart_id` int(11) NOT NULL auto_increment,
  `depart_name` varchar(255) default NULL,
  PRIMARY KEY  (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO `sys_depart` VALUES ('1', '技术部');
INSERT INTO `sys_depart` VALUES ('2', '销售部');
INSERT INTO `sys_depart` VALUES ('3', '研发部');
INSERT INTO `sys_depart` VALUES ('4', '财务部');
INSERT INTO `sys_depart` VALUES ('5', '市场一部');
INSERT INTO `sys_depart` VALUES ('6', '市场二部');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(255) default NULL,
  `user_pwd` varchar(255) default NULL,
  `real_name` varchar(255) default NULL,
  `company_name` varchar(255) default NULL,
  `depart_name` varchar(255) default NULL,
  `enable` int(11) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '李近平', '杰瑞教育', '技术部', '1');
INSERT INTO `sys_user` VALUES ('43', '窗前明月光', '666', '郭德纲', '德云社', '计划部', '1');
INSERT INTO `sys_user` VALUES ('44', '葫芦娃', '666', '于谦', '德云社', '生产部', '1');
INSERT INTO `sys_user` VALUES ('46', '五环之歌', '666', '岳云鹏', '德云社', '逗哏部', '1');
