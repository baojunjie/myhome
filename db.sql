drop database if exists my_home;

create database if not exists my_home;

use my_home;


drop table if exists t_page;
create table if not exists t_page (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    name varchar(200),
    file_path varchar(200),
    url varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_region;
create table if not exists t_region (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    level int default 1,
    region_id bigint,
    name varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_tag;
create table if not exists t_tag (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    name varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_user;
create table if not exists t_user (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_user_info;
create table if not exists t_user_info (
    id bigint primary key,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    male tinyint default 1,
    birthday date,
    region_id bigint,
    name varchar(200),
    constellation varchar(200),
    zodiac varchar(200),
    id_code varchar(200),
    parent_mobile varchar(200),
    school varchar(200),
    teacher varchar(200),
    teacher_mobile varchar(200),
    nation varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_role;
create table if not exists t_role (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    code varchar(200),
    name varchar(200),
    description varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_permission;
create table if not exists t_permission (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    code varchar(200),
    name varchar(200),
    description varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_role_permission_item;
create table if not exists t_role_permission_item (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    role_id bigint,
    permission_id bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_user_role_item;
create table if not exists t_user_role_item (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    user_id bigint,
    role_id bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_user_permission_item;
create table if not exists t_user_permission_item (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    user_id bigint,
    permission_id bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_authentication;
create table if not exists t_authentication (
    id bigint primary key auto_increment,
    type varchar(200) not null,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    user_id bigint,
    login varchar(200),
    password varchar(200),
    salt varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_artist;
create table if not exists t_artist (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_user_info;
create table if not exists t_user_info (
    id bigint primary key,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    male tinyint default 1,
    birthday date,
    region_id bigint,
    user_id bigint,
    name varchar(200),
    nation varchar(200),
    constellation varchar(200),
    zodiac varchar(200),
    id_code varchar(200),
    parent_mobile varchar(200),
    school varchar(200),
    school_mobile varchar(200),
    instructor varchar(200),
    instructor_mobile varchar(200),
    teacher varchar(200),
    teacher_mobile varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_works;
create table if not exists t_works (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    artist_id bigint,
    votenum int,
    praise int,
    male tinyint default 1,
    region_id bigint,
    age int,
    name varchar(200),
    author varchar(200),
    school varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_voting;
create table if not exists t_voting (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    user_id bigint,
    point int default 1
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_praise;
create table if not exists t_praise (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    user_id bigint,
    point int default 1
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_game;
create table if not exists t_game (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    title varchar(200),
    begin_datetime datetime,
    end_datetime datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_game_works_item;
create table if not exists t_game_works_item (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    game_id bigint,
    works_id bigint,
    applicant_id bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_works_picture_item;
create table if not exists t_works_picture_item (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    path varchar(200),
    description varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_works_tag_item;
create table if not exists t_works_tag_item (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    works_id bigint,
    tag_id bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists t_comment;
create table if not exists t_comment (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    works_id bigint,
    user_id bigint,
    artist_id bigint,
    words varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE t_praise
ADD COLUMN type VARCHAR(45) NULL COMMENT '' AFTER point,
ADD COLUMN entity_id VARCHAR(45) NULL COMMENT '' AFTER type;


drop table if exists t_verificationcode;
create table if not exists t_verificationcode (
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    mobile varchar(50),
    code varchar(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_works add column description varchar(1000);

drop table if exists t_picture;
create TABLE if not exists t_picture
(
    id bigint primary key auto_increment,
    invalid tinyint default 0,
    status int default 1,
    created_datetime timestamp,
    updated_datetime datetime,
    order_tag int default 1,
    path varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE t_works_picture_item
DROP COLUMN description,
DROP COLUMN path;

ALTER TABLE t_works_picture_item
add column works_id bigint,
add column picture_id bigint;

DROP TABLE IF EXISTS t_artist_info;
CREATE TABLE t_artist_info (
  id bigint(20) NOT NULL,
  invalid tinyint(4) DEFAULT NULL,
  status int(11) DEFAULT NULL,
  created_datetime timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  updated_datetime timestamp NULL DEFAULT NULL,
  order_tag int default 1,
  artist_id bigint(20) DEFAULT NULL,
  male int(1) DEFAULT NULL,
  birthday date DEFAULT NULL,
  region_id bigint(20) DEFAULT NULL,
  name varchar(64) DEFAULT NULL,
  nation varbinary(64) DEFAULT NULL,
  constellation varchar(200) DEFAULT NULL,
  zodiac varchar(200) DEFAULT NULL,
  id_code varchar(200) DEFAULT NULL,
  parent_mobile varchar(200) DEFAULT NULL,
  school varchar(200) DEFAULT NULL,
  school_mobile varchar(200) DEFAULT NULL,
  instructor varchar(200) DEFAULT NULL,
  instructor_mobile varchar(200) DEFAULT NULL,
  teacher varchar(200) DEFAULT NULL,
  teacher_mobile varchar(200) DEFAULT NULL,
  img varchar(200) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE t_voting 
add column works_id bigint;

ALTER TABLE t_artist_info   
  ADD COLUMN referrer_school VARCHAR(200) NULL  COMMENT '推荐人学校' ,
  ADD COLUMN referrer_mobile VARCHAR(16) NULL  COMMENT '推荐人联系方式';
  
-- 2015/09/14 lqf
ALTER TABLE t_artist_info
  ADD COLUMN age int ; 

 
--2015/09/20 小彪
 ALTER TABLE t_works   
  ADD COLUMN commentNum INT(11)  DEFAULT 0   COMMENT '评论总数';


--2015/09/24 lqf
ALTER TABLE t_artist_info
  ADD COLUMN orginimg VARCHAR(200) NULL  COMMENT '原图路径';
ALTER TABLE  t_picture   
  ADD COLUMN orginpath VARCHAR(200) NULL  COMMENT '原图路径';
  ALTER TABLE t_artist_info   
  ADD COLUMN origin VARCHAR(200) NULL  COMMENT '籍贯';

  -- 2015/09/30 小彪
 DROP TABLE IF EXISTS t_opinion;
CREATE TABLE IF NOT EXISTS t_version (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    invalid TINYINT DEFAULT 0,
    STATUS INT DEFAULT 1,
    created_datetime TIMESTAMP,
    updated_datetime DATETIME,
    order_tag INT DEFAULT 1,
    content VARCHAR(200),
    user_id BIGINT
)ENGINE=INNODB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS t_version;
CREATE TABLE IF NOT EXISTS t_version (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    invalid TINYINT DEFAULT 0,
    STATUS INT DEFAULT 1,
    created_datetime TIMESTAMP,
    updated_datetime DATETIME,
    order_tag INT DEFAULT 1,
    content VARCHAR(200),
    versions VARCHAR(20),
    serviceAddress VARCHAR(200),
    size VARCHAR(20)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


ALTER TABLE t_Page 
  ADD COLUMN TYPE INT NULL  COMMENT '1:action 2:轮播图' ;
  
  -- gwb 051008
  ALTER TABLE  t_user_info   
   ADD COLUMN img VARCHAR(200) NULL  COMMENT '用户头像' ,
   ADD COLUMN origin_img VARCHAR(200) NULL  COMMENT '用户头像原图' ,
   ADD COLUMN account DOUBLE(10,2) NULL  COMMENT '账户金额',
   ADD COLUMN profession VARCHAR(64) NULL  COMMENT '职业' ,
   ADD COLUMN mobile VARCHAR(16) NULL  COMMENT '联系方式' ,
   ADD COLUMN nick_name VARCHAR(32) NULL  COMMENT '昵称' ,
   ADD COLUMN TYPE INT(2) DEFAULT 1 NULL  COMMENT '角色类型 1普通用（默认）,2画家，3受捐者，4老师，5赞助商,6同时是画家和受捐者' ;

-- gwb 20151019
ALTER TABLE t_artist_info  
  ADD COLUMN `cartoon` VARBINARY(200) NULL  COMMENT '最喜欢的动漫' ;

