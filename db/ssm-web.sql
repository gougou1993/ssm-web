/*
Navicat PGSQL Data Transfer

Source Server         : 172.16.156.82
Source Server Version : 90602
Source Host           : 172.16.156.82:5432
Source Database       : ssm
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90602
File Encoding         : 65001

Date: 2017-07-23 15:19:29
*/


-- ----------------------------
-- Sequence structure for "seq_id"
-- ----------------------------
DROP SEQUENCE "seq_id";
CREATE SEQUENCE "seq_id"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 495
 CACHE 1;

-- ----------------------------
-- Table structure for "se_menu"
-- ----------------------------
DROP TABLE "se_menu";
CREATE TABLE "se_menu" (
"id" int8 NOT NULL,
"menucode" varchar(32),
"parentcode" varchar(32),
"captionname" varchar(50),
"menuorder" int4,
"sourceurl" varchar(500),
"classname" varchar(100),
"visible" int2,
"entrytime" date,
"updatetime" date,
"updateid" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of se_menu
-- ----------------------------
BEGIN;
INSERT INTO "se_menu" VALUES ('1', '527BB36A61E0119D006E7C7FE7A668B5', null, '根', '0', '#', null, '1', '2017-07-15', '2017-07-22', '17');
INSERT INTO "se_menu" VALUES ('2', '662F0CA65E7DE6F58BF06750D506C2B3', '527BB36A61E0119D006E7C7FE7A668B5', '测试模块', '1', '#', 'fa fa-gears', '1', '2017-07-15', '2017-07-22', '11');
INSERT INTO "se_menu" VALUES ('3', 'CCADA8C1279FCE5CFABE8332F267FBB2', '662F0CA65E7DE6F58BF06750D506C2B3', '测试页面A', '1010', 'se_user_list.do', 'fa fa-gears', '1', '2017-07-15', '2017-07-22', '13');
INSERT INTO "se_menu" VALUES ('4', '556075CB171D39F8B7852CC3F96566C6', '662F0CA65E7DE6F58BF06750D506C2B3', '测试页面B', '1020', 'se_user_list.do', 'fa fa-gears', '1', '2017-07-15', '2017-07-22', '12');
INSERT INTO "se_menu" VALUES ('5', '830E66A506D9E136C7CCE8E7F3DE8F05', '527BB36A61E0119D006E7C7FE7A668B5', '系统管理', '9', '#', 'fa fa-gears', '1', '2017-07-15', '2017-07-22', '10');
INSERT INTO "se_menu" VALUES ('6', '8B3159FFD0272DE30CD282592CE27388', '830E66A506D9E136C7CCE8E7F3DE8F05', '用户管理', '9010', 'se_user_list.do', 'fa fa-gears', '1', '2017-07-15', '2017-07-22', '16');
INSERT INTO "se_menu" VALUES ('7', 'A26558297F6E6D8CAACB2FCB2FFCB8AC', '830E66A506D9E136C7CCE8E7F3DE8F05', '角色管理', '9020', 'se_role_list.do', 'fa fa-gears', '1', '2017-07-15', '2017-07-22', '15');
INSERT INTO "se_menu" VALUES ('8', 'DE2766179662D99C2F1D670DDA6FF192', '830E66A506D9E136C7CCE8E7F3DE8F05', '菜单管理', '9030', 'se_menu_list.do', 'fa fa-gears', '1', '2017-07-15', '2017-07-22', '14');
COMMIT;

-- ----------------------------
-- Table structure for "se_role"
-- ----------------------------
DROP TABLE "se_role";
CREATE TABLE "se_role" (
"id" int8 NOT NULL,
"rolecode" varchar(32),
"roledesc" varchar(50),
"content" varchar(500),
"visible" int2,
"entrytime" date,
"updatetime" date,
"updateid" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of se_role
-- ----------------------------
BEGIN;
INSERT INTO "se_role" VALUES ('1', '69DDD5608B0652BF986B1769D5AA2385', '系统管理员', null, '1', '2017-07-15', '2017-07-22', '1');
INSERT INTO "se_role" VALUES ('2', 'AC389567687639F0A3D0E3A7F2E5D863', '普通用户', null, '1', '2017-07-15', '2017-07-22', '1');
COMMIT;

-- ----------------------------
-- Table structure for "se_rolemenu"
-- ----------------------------
DROP TABLE "se_rolemenu";
CREATE TABLE "se_rolemenu" (
"id" int8 NOT NULL,
"rolecode" varchar(32),
"menucode" varchar(32),
"entrytime" date,
"updatetime" date,
"updateid" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of se_rolemenu
-- ----------------------------
BEGIN;
INSERT INTO "se_rolemenu" VALUES ('1', '69DDD5608B0652BF986B1769D5AA2385', '830E66A506D9E136C7CCE8E7F3DE8F05', '2017-07-15', '2017-07-22', '1');
INSERT INTO "se_rolemenu" VALUES ('2', '69DDD5608B0652BF986B1769D5AA2385', '662F0CA65E7DE6F58BF06750D506C2B3', '2017-07-15', '2017-07-22', '2');
INSERT INTO "se_rolemenu" VALUES ('3', '69DDD5608B0652BF986B1769D5AA2385', '556075CB171D39F8B7852CC3F96566C6', '2017-07-15', '2017-07-22', '3');
INSERT INTO "se_rolemenu" VALUES ('4', '69DDD5608B0652BF986B1769D5AA2385', 'CCADA8C1279FCE5CFABE8332F267FBB2', '2017-07-15', '2017-07-22', '4');
INSERT INTO "se_rolemenu" VALUES ('5', '69DDD5608B0652BF986B1769D5AA2385', 'DE2766179662D99C2F1D670DDA6FF192', '2017-07-15', '2017-07-22', '5');
INSERT INTO "se_rolemenu" VALUES ('6', '69DDD5608B0652BF986B1769D5AA2385', 'A26558297F6E6D8CAACB2FCB2FFCB8AC', '2017-07-15', '2017-07-22', '6');
INSERT INTO "se_rolemenu" VALUES ('7', '69DDD5608B0652BF986B1769D5AA2385', '8B3159FFD0272DE30CD282592CE27388', '2017-07-15', '2017-07-22', '7');
INSERT INTO "se_rolemenu" VALUES ('8', '69DDD5608B0652BF986B1769D5AA2385', '527BB36A61E0119D006E7C7FE7A668B5', '2017-07-15', '2017-07-22', '8');
INSERT INTO "se_rolemenu" VALUES ('9', 'AC389567687639F0A3D0E3A7F2E5D863', '527BB36A61E0119D006E7C7FE7A668B5', '2017-07-15', '2017-07-15', '9');
INSERT INTO "se_rolemenu" VALUES ('10', 'AC389567687639F0A3D0E3A7F2E5D863', '662F0CA65E7DE6F58BF06750D506C2B3', '2017-07-15', '2017-07-15', '10');
INSERT INTO "se_rolemenu" VALUES ('11', 'AC389567687639F0A3D0E3A7F2E5D863', '556075CB171D39F8B7852CC3F96566C6', '2017-07-15', '2017-07-15', '11');
INSERT INTO "se_rolemenu" VALUES ('12', 'AC389567687639F0A3D0E3A7F2E5D863', 'CCADA8C1279FCE5CFABE8332F267FBB2', '2017-07-15', '2017-07-15', '12');
COMMIT;

-- ----------------------------
-- Table structure for "se_roleuser"
-- ----------------------------
DROP TABLE "se_roleuser";
CREATE TABLE "se_roleuser" (
"id" int8 NOT NULL,
"rolecode" varchar(32),
"usercode" varchar(32),
"entrytime" date,
"updatetime" date,
"updateid" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of se_roleuser
-- ----------------------------
BEGIN;
INSERT INTO "se_roleuser" VALUES ('1', '69DDD5608B0652BF986B1769D5AA2385', 'AE9E5F30E652979A85316B850C57BCCC', '2017-07-15', '2017-07-22', '1');
COMMIT;

-- ----------------------------
-- Table structure for "se_user"
-- ----------------------------
DROP TABLE "se_user";
CREATE TABLE "se_user" (
"id" int8 NOT NULL,
"usercode" varchar(32),
"pname" varchar(100),
"password" varchar(32),
"uname" varchar(100),
"birthym" date,
"pic" bytea,
"telcode" varchar(50),
"email" varchar(50),
"visible" int2,
"entrytime" date,
"updatetime" date,
"updateid" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of se_user
-- ----------------------------
BEGIN;
INSERT INTO "se_user" VALUES ('1', 'AE9E5F30E652979A85316B850C57BCCC', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin', '1991-01-01', null, null, null, '1', '2017-07-15', '2017-07-22', '1');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table "se_menu"
-- ----------------------------
ALTER TABLE "se_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table "se_role"
-- ----------------------------
ALTER TABLE "se_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table "se_rolemenu"
-- ----------------------------
ALTER TABLE "se_rolemenu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table "se_roleuser"
-- ----------------------------
ALTER TABLE "se_roleuser" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table "se_user"
-- ----------------------------
ALTER TABLE "se_user" ADD PRIMARY KEY ("id");
