--  2.1
-- 推荐--受帮助的小朋友   /我打赏的/打赏我的/我帮助的/我推荐的
DROP TABLE IF EXISTS `t_recommend_help_playtour`;
CREATE TABLE `t_recommend_help_playtour` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invalid` tinyint(4) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT NULL,
  `order_tag` int(11) DEFAULT '1',
  `user_id_from` int(11) DEFAULT NULL COMMENT '推荐/打赏/帮助人',
  `user_id_to` int(11) DEFAULT NULL COMMENT '被推荐/打赏/帮助人',
  `money` double(11,2) DEFAULT NULL COMMENT '打赏的钱',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  `message` varchar(200) DEFAULT NULL COMMENT '寄语',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='我推荐的我帮助的我打赏的';

--  2.1
-- 新增培训机构
ALTER TABLE t_works
  ADD COLUMN `instructor` VARCHAR(200) NULL  COMMENT '指导老师',
  ADD COLUMN `instructor_mobile` VARCHAR(200) NULL  COMMENT '指导老师电话';

ALTER TABLE t_artist_info
  ADD COLUMN `training_institution` VARCHAR(32) NULL  COMMENT '培训机构';

