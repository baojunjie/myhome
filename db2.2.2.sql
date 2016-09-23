--  2.3
-- 新增级别
ALTER TABLE t_works
  ADD COLUMN TYPE INT(2) DEFAULT 0 NULL  COMMENT '1:区初选2:区复选3:区终选4:市初选5:市复选6:市终选7:省初选8:省复选9:省终选10:全国复选' ;
  ADD COLUMN awards INT(2) DEFAULT 0 NULL  COMMENT '全国特等奖11 全国金奖10 全国银奖9 全国铜奖8 全国创作奖7 省金奖6 省银奖5 省铜奖4 市金奖3 市银奖2 市铜奖1 普通0' ;

  -- 新增活动表
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invalid` tinyint(4) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT NULL,
  `order_tag` int(11) DEFAULT '1',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `path` varchar(200) DEFAULT NULL COMMENT '图片',
  `thumbpath` varchar(200) DEFAULT NULL COMMENT '缩略图片',
  `url` varchar(200) DEFAULT NULL COMMENT '超链接',
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 新增广告表
DROP TABLE IF EXISTS `t_advertise`;
CREATE TABLE `t_advertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invalid` tinyint(4) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT NULL,
  `order_tag` int(11) DEFAULT '1',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `path` varchar(200) DEFAULT NULL COMMENT '图片',
  `url` varchar(200) DEFAULT NULL COMMENT '超链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 新增系统参数表
DROP TABLE IF EXISTS `t_parameter`;
CREATE TABLE `t_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invalid` tinyint(4) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT NULL,
  `order_tag` int(11) DEFAULT '1',
  `name` varchar(200) DEFAULT NULL COMMENT '参数名',
  `value` varchar(200) DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO `t_parameter` VALUES ('1', '0', '1', '2016-03-17 10:35:51', null, '1', '比赛阶段', '0');