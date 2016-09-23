--  2.2.3
-- 新增描述
ALTER TABLE t_activity
  ADD COLUMN description varchar(200) DEFAULT NULL   COMMENT '描述' ;
  
INSERT INTO `t_tag` VALUES ('9', '0', '1', '2016-04-07 16:46:26', null, '1', '其他');