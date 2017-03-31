/*
Navicat MySQL Data Transfer

Source Server         : database
Source Server Version : 50133
Source Host           : localhost:3306
Source Database       : qly_dn

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2017-03-31 10:44:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for content_contract
-- ----------------------------
DROP TABLE IF EXISTS `content_contract`;
CREATE TABLE `content_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content_contract_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gbm1m24rmyyewycjo2kpueifv` (`content_contract_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of content_contract
-- ----------------------------

-- ----------------------------
-- Table structure for continent
-- ----------------------------
DROP TABLE IF EXISTS `continent`;
CREATE TABLE `continent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `continent_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of continent
-- ----------------------------
INSERT INTO `continent` VALUES ('1', 'Chau a');
INSERT INTO `continent` VALUES ('2', 'Chau Au');
INSERT INTO `continent` VALUES ('3', 'Chau dai Duong');

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_contract` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `funding` float NOT NULL,
  `notice` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `number` int(11) NOT NULL,
  `ordinary_number` int(11) NOT NULL,
  `renew` int(11) DEFAULT NULL,
  `result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `partner_id` int(11) DEFAULT NULL,
  `partner_contact_id` int(11) DEFAULT NULL,
  `type_contract_id` int(11) DEFAULT NULL,
  `uet_man_id` int(11) DEFAULT NULL,
  `unit_name_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iq96t4f3c2u8qe2u699n2mwys` (`partner_id`),
  KEY `FK_qaw7j2tcpbvdo1tq0926ml3ie` (`partner_contact_id`),
  KEY `FK_ncoc7y0w4e0kxtwdnyf3bw0kn` (`type_contract_id`),
  KEY `FK_m24ksf4o6aht7so5qs0050kai` (`uet_man_id`),
  KEY `FK_7a1s5hpqosuh2mxfnuleqdgb6` (`unit_name_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('4', 'Thay doi noi dun hop tac bla', null, '234234', 'Không có gì', '0', '0', null, null, null, '1', '1', '1', '2', '2');
INSERT INTO `contract` VALUES ('6', 'chua co', null, '2.34235e+008', '???c r?i này', '0', '0', null, null, null, '1', '2', '2', '2', '4');

-- ----------------------------
-- Table structure for nation
-- ----------------------------
DROP TABLE IF EXISTS `nation`;
CREATE TABLE `nation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nation_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `continent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ecujedp0od4kcyxilcxuia36x` (`continent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of nation
-- ----------------------------
INSERT INTO `nation` VALUES ('1', 'Viet Nam', '1');
INSERT INTO `nation` VALUES ('4', 'Lao', '1');
INSERT INTO `nation` VALUES ('3', 'hoangkhanh', '1');
INSERT INTO `nation` VALUES ('5', 'Duc', '2');
INSERT INTO `nation` VALUES ('6', 'Uc', '3');

-- ----------------------------
-- Table structure for partner
-- ----------------------------
DROP TABLE IF EXISTS `partner`;
CREATE TABLE `partner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_149putj97l28jkvmo6075h7x7` (`nation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of partner
-- ----------------------------
INSERT INTO `partner` VALUES ('1', '1');
INSERT INTO `partner` VALUES ('2', '1');
INSERT INTO `partner` VALUES ('3', '1');
INSERT INTO `partner` VALUES ('4', '1');
INSERT INTO `partner` VALUES ('5', '3');
INSERT INTO `partner` VALUES ('6', '1');
INSERT INTO `partner` VALUES ('7', '1');
INSERT INTO `partner` VALUES ('8', '4');
INSERT INTO `partner` VALUES ('9', '1');
INSERT INTO `partner` VALUES ('10', '4');
INSERT INTO `partner` VALUES ('11', '5');
INSERT INTO `partner` VALUES ('12', '3');
INSERT INTO `partner` VALUES ('13', '1');
INSERT INTO `partner` VALUES ('14', '1');
INSERT INTO `partner` VALUES ('15', '5');
INSERT INTO `partner` VALUES ('16', '4');
INSERT INTO `partner` VALUES ('17', '5');
INSERT INTO `partner` VALUES ('18', '1');

-- ----------------------------
-- Table structure for partner_contact
-- ----------------------------
DROP TABLE IF EXISTS `partner_contact`;
CREATE TABLE `partner_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `about` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contact_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `skype` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `partner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n37ksadhg4cx7g54hqimagewd` (`partner_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of partner_contact
-- ----------------------------
INSERT INTO `partner_contact` VALUES ('1', 'khong co gi', 'Nguyen Hoang Khanh', 'khong co', '324234234234', 'khong co luon', '1');
INSERT INTO `partner_contact` VALUES ('2', 'Nguyen Hoang Khanh', 'Nguyen Hoang Khanh', 'chua co', '345963495', null, '1');

-- ----------------------------
-- Table structure for partner_info
-- ----------------------------
DROP TABLE IF EXISTS `partner_info`;
CREATE TABLE `partner_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `director` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `field_work` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `partner_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tax_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `partner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6fr1y2cd04kuuwtnjh2m8kck2` (`partner_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of partner_info
-- ----------------------------
INSERT INTO `partner_info` VALUES ('1', null, null, 'Sony', 'sony@sony.com', null, 'Bi loi font chu tieng viet nen la chua viet tieng viet', 'Sony', '345345345', null, 'sony.vn', '1');
INSERT INTO `partner_info` VALUES ('2', null, null, null, null, null, null, 'VinGroup', null, null, null, '2');
INSERT INTO `partner_info` VALUES ('3', null, null, null, null, null, null, 'LG', null, null, null, '3');
INSERT INTO `partner_info` VALUES ('4', null, null, null, null, null, null, 'Fpt', null, null, null, '4');
INSERT INTO `partner_info` VALUES ('5', null, null, null, null, null, null, 'Samsung', null, null, null, '5');
INSERT INTO `partner_info` VALUES ('6', null, null, null, null, null, null, 'Khanh', null, null, null, '6');
INSERT INTO `partner_info` VALUES ('7', null, null, null, null, null, null, 'Tau', null, null, null, '7');
INSERT INTO `partner_info` VALUES ('8', null, null, null, null, null, null, 'Tv', null, null, null, '8');
INSERT INTO `partner_info` VALUES ('9', null, null, null, null, null, null, 'Honda', null, null, null, '9');
INSERT INTO `partner_info` VALUES ('10', null, null, null, null, null, null, 'Ma', null, null, null, '10');
INSERT INTO `partner_info` VALUES ('11', null, null, null, null, null, null, 'My', null, null, null, '11');
INSERT INTO `partner_info` VALUES ('12', null, null, null, null, null, null, 'Hoang Khanh', null, null, null, '12');
INSERT INTO `partner_info` VALUES ('13', null, null, null, null, null, null, 'VTC', null, null, null, '13');
INSERT INTO `partner_info` VALUES ('14', null, null, null, null, null, null, 'VinaGame', null, null, null, '14');
INSERT INTO `partner_info` VALUES ('15', null, null, null, null, null, null, 'GameLoft', null, null, null, '15');
INSERT INTO `partner_info` VALUES ('16', null, null, null, null, null, null, 'Cha nghi ra ten gi nua', null, null, null, '16');
INSERT INTO `partner_info` VALUES ('17', null, null, null, null, null, null, 'Value', null, null, null, '17');
INSERT INTO `partner_info` VALUES ('18', null, null, null, null, null, null, 'a', null, null, null, '18');

-- ----------------------------
-- Table structure for partner_man
-- ----------------------------
DROP TABLE IF EXISTS `partner_man`;
CREATE TABLE `partner_man` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partner_man_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of partner_man
-- ----------------------------

-- ----------------------------
-- Table structure for type_contract
-- ----------------------------
DROP TABLE IF EXISTS `type_contract`;
CREATE TABLE `type_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_contract` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of type_contract
-- ----------------------------
INSERT INTO `type_contract` VALUES ('1', 'Nguyen Mai Anh');
INSERT INTO `type_contract` VALUES ('2', 'Nguyen Mai Khanh');
INSERT INTO `type_contract` VALUES ('3', 'Nguyen Hoang Khanh');

-- ----------------------------
-- Table structure for uet_man
-- ----------------------------
DROP TABLE IF EXISTS `uet_man`;
CREATE TABLE `uet_man` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uet_man_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of uet_man
-- ----------------------------
INSERT INTO `uet_man` VALUES ('5', 'Duong Le Minh');
INSERT INTO `uet_man` VALUES ('2', 'Nguyen Hoang Nam');
INSERT INTO `uet_man` VALUES ('3', 'Nguyen Hoang Khanh');

-- ----------------------------
-- Table structure for unit_name
-- ----------------------------
DROP TABLE IF EXISTS `unit_name`;
CREATE TABLE `unit_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of unit_name
-- ----------------------------
INSERT INTO `unit_name` VALUES ('2', 'Nguyen Hoang Khanh');
INSERT INTO `unit_name` VALUES ('3', 'V?n phòng ?oàn');
INSERT INTO `unit_name` VALUES ('4', 'Dinh Vu nam');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '69dc3b10-0f08-4e29-ad0a-b2eb76af1d37', 'hoangkhanh');
INSERT INTO `user` VALUES ('2', 'htptadmin', null, 'htptweb');
