/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.17 : Database - zuoben
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zuoben` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `zuoben`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(18) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `text` varchar(150) DEFAULT NULL COMMENT '名称',
  `href` varchar(150) DEFAULT NULL COMMENT 'url地址',
  `parent_id` int(18) DEFAULT NULL COMMENT '父等级',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型  1菜单 2按钮  3接口',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单上下级关系',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `state` int(1) DEFAULT '0' COMMENT '权限状态 0未知  1启用  2禁用  3删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` int(18) DEFAULT NULL COMMENT '创建者id',
  `update_id` int(18) DEFAULT NULL COMMENT '修改者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台用户权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`text`,`href`,`parent_id`,`icon`,`type`,`path`,`sort`,`state`,`create_time`,`update_time`,`create_id`,`update_id`) values (1,'后台管理系统','#',-1,'glyphicon glyphicon-home',1,NULL,0,1,'2018-04-16 09:44:43','2018-04-16 09:44:43',NULL,NULL),(44,'运维','#',1,'glyphicon glyphicon-star',1,NULL,1,1,NULL,NULL,NULL,NULL),(45,'WEB-API接口','http://localhost:8762/api/web/swagger-ui.html#/',44,'glyphicon glyphicon-certificate',1,NULL,11,1,'2018-07-02 11:06:16','2018-07-02 11:06:16',NULL,NULL),(46,'SYS-数据库监控','http://localhost:8764/druid/webapp.html',44,'glyphicon glyphicon-blackboard',1,NULL,12,1,'2018-07-02 11:06:26','2018-07-02 11:06:26',NULL,NULL),(53,'系统','#',1,'glyphicon glyphicon-th-large',1,NULL,1123,1,NULL,NULL,NULL,NULL),(178,'用户管理','html/user/userList.html',53,'glyphicon glyphicon-user',1,NULL,111,1,NULL,NULL,NULL,NULL),(179,'菜单管理','html/menu/menuList.html',53,'glyphicon glyphicon-align-justify',1,NULL,111,1,NULL,NULL,NULL,NULL),(180,'角色管理','html/role/roleList.html',53,'glyphicon glyphicon-star',1,NULL,111,1,NULL,NULL,NULL,NULL),(182,'WEB-数据库监控','http://localhost:8765/druid/webapp.html',44,'glyphicon glyphicon-blackboard',1,NULL,111,1,NULL,NULL,NULL,NULL),(183,'SYS-API接口','http://localhost:8762/api/sys/swagger-ui.html#/',44,'glyphicon glyphicon-certificate',1,NULL,111,1,NULL,NULL,NULL,NULL),(184,'AUTH-API接口','http://localhost:8762/api/auth/swagger-ui.html#/',44,'glyphicon glyphicon-certificate',1,NULL,111,1,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `state` int(1) DEFAULT '0' COMMENT '角色状态0正常 1禁用 2删除',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_id` int(18) DEFAULT NULL,
  `update_id` int(18) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`state`,`create_time`,`update_time`,`create_id`,`update_id`) values (36,'系统管理员',1,'2018-12-28 15:19:15','2018-12-28 15:19:15',215,215);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `role_id` int(18) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(18) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2853 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values (2657,36,1),(2658,36,53),(2659,36,180),(2660,36,179),(2661,36,178),(2662,36,188),(2663,36,186),(2664,36,187),(2665,36,182),(2666,36,44),(2667,36,183),(2668,36,184),(2669,36,46),(2670,36,45),(2671,36,189),(2672,36,190),(2673,36,191),(2674,36,192),(2675,36,193),(2676,36,197),(2677,36,196),(2678,36,195),(2679,36,194),(2680,36,198),(2681,36,201),(2682,36,200),(2683,36,199),(2684,36,203),(2685,36,202),(2788,32,44),(2789,32,1),(2790,32,182),(2791,32,183),(2792,32,184),(2793,32,46),(2794,32,45),(2795,32,186),(2796,32,187),(2797,32,188),(2798,32,189),(2799,32,191),(2800,32,199),(2801,32,190),(2802,32,201),(2803,32,200),(2804,32,198),(2805,32,192),(2806,32,194),(2807,32,197),(2808,32,193),(2809,32,196),(2810,32,195),(2811,38,204),(2812,38,222),(2813,38,223),(2814,38,225),(2829,39,226),(2830,39,204),(2831,39,224),(2832,39,227),(2833,40,204),(2834,40,205),(2835,40,206),(2836,40,207),(2837,40,208),(2838,40,213),(2839,40,214),(2840,40,215),(2841,40,216),(2842,40,217),(2843,40,218),(2844,40,222),(2845,40,227),(2846,40,223),(2847,40,224),(2848,40,225),(2849,40,226),(2850,40,219),(2851,40,220),(2852,40,221);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `headImgUrl` varchar(100) DEFAULT NULL COMMENT '头像',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `state` int(1) DEFAULT NULL COMMENT '0正常  1启用  2禁用  3删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` int(18) DEFAULT NULL COMMENT '创建者id',
  `update_id` int(18) DEFAULT NULL COMMENT '修改者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`nickname`,`user_name`,`password`,`headImgUrl`,`phone`,`state`,`create_time`,`update_time`,`create_id`,`update_id`) values (215,'zuoben','admin','ec3a6e2ad266f7229af912f7434d1b7e','http://upload.guotaowang.cn/f3c66e76-845f-454a-8854-b19b7be562c3','15829342473',1,'2018-12-01 21:02:22','2018-12-01 21:02:22',NULL,NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `user_id` int(18) DEFAULT NULL COMMENT '用户id',
  `role_id` int(18) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=289 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (265,215,36);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
