SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `articles`;
CREATE TABLE `articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `writer` varchar(20) DEFAULT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

insert into `articles`(`id`,`category_id`,`title`,`content`,`writer`,`update_dt`) values
('1','1','优惠活动一','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('2','1','优惠活动二','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('3','2','婚礼百科一','婚礼百科一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('4','4','操作指南一','操作指南一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('5','1','优惠活动三','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('6','1','优惠活动四','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('7','1','优惠活动五','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('8','1','优惠活动六','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('9','1','优惠活动七','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('10','1','优惠活动八','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('11','1','优惠活动九','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('12','1','优惠活动十','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('13','1','优惠活动十一','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('14','1','优惠活动十二','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('15','1','优惠活动十三','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('16','1','优惠活动十四','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('17','1','优惠活动十五','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('18','1','优惠活动十六','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55'),
('19','1','优惠活动十七','优惠活动一的内容啦啦啦','admin','2015-01-31 20:25:55'),
('20','1','优惠活动十八','优惠活动二的内容啦啦啦','admin','2015-01-31 20:25:55');
DROP TABLE IF EXISTS  `categories`;
CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

insert into `categories`(`id`,`title`,`update_dt`) values
('1','优惠活动','2015-01-31 20:25:54'),
('2','婚礼百科','2015-01-31 20:25:54'),
('3','客片展示','2015-01-31 20:25:54'),
('4','操作指南','2015-01-31 20:25:54');
DROP TABLE IF EXISTS  `config`;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into `config`(`id`,`key`,`value`) values
('1','wechat_menu','{ "button": [ { "name": "服务", "sub_button": [ { "type": "click", "name": "幸福商城", "key": "CK_SHOP" } ] }, { "name": "优惠资讯", "sub_button": [ { "type": "view", "name": "优惠活动", "url": "http://www.soso.com/" }, { "type": "view", "name": "婚礼百科", "url": "http://m.baidu.com/" }, { "type": "view", "name": "客片展示", "url": "http://m.baidu.com/" } ] }, { "name": "地图导航", "sub_button": [ { "type": "view", "name": "步行来这", "url": "http://eastbride-wechat.aliapp.com/front/mapByWalk" }, { "type": "view", "name": "公交车来这", "url": "http://eastbride-wechat.aliapp.com/front/mapByBus" }, { "type": "view", "name": "开车来这", "url": "http://eastbride-wechat.aliapp.com/front/mapByCar" } ] } ] }'),
('2','shop_name','东方新娘微信商城'),
('3','shop_notification','欢迎光临');
DROP TABLE IF EXISTS  `permissions`;
CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

insert into `permissions`(`id`,`permission`,`description`,`available`) values
('1','editUser',null,'1'),
('2','showUser',null,'1'),
('3','addUser',null,'1'),
('4','deleteUser',null,'1');
DROP TABLE IF EXISTS  `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert into `roles`(`id`,`role`,`description`,`available`) values
('1','admin','admin user','1'),
('2','user','user','1');
DROP TABLE IF EXISTS  `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `roles_permissions`(`role_id`,`permission_id`) values
('1','1'),
('1','2'),
('1','3'),
('1','4'),
('2','2');
DROP TABLE IF EXISTS  `shop_good`;
CREATE TABLE `shop_good` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `sort` int(11) NOT NULL,
  `name` text NOT NULL,
  `price` varchar(32) NOT NULL,
  `old_price` varchar(32) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `detail` text NOT NULL,
  `status` int(11) DEFAULT '1',
  `update_dt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into `shop_good`(`id`,`menu_id`,`sort`,`name`,`price`,`old_price`,`image`,`detail`,`status`,`update_dt`) values
('1','1','1','豪华套餐','8888','10001','54ceeec6a17a5.jpg','<p>豪华套餐介绍</p><p>巴拉巴拉、、、、</p>	','1','2015-02-03 10:48:21');
DROP TABLE IF EXISTS  `shop_menu`;
CREATE TABLE `shop_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into `shop_menu`(`id`,`name`,`pid`) values
('1','婚纱摄影套餐','0');
DROP TABLE IF EXISTS  `shop_order`;
CREATE TABLE `shop_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `totalprice` varchar(32) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `pay_style` varchar(32) NOT NULL,
  `pay_status` varchar(32) NOT NULL,
  `order_status` varchar(32) DEFAULT NULL,
  `create_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_dt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `cartdata` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into `shop_order`(`id`,`user_id`,`order_id`,`totalprice`,`note`,`pay_style`,`pay_status`,`order_status`,`create_dt`,`update_dt`,`cartdata`) values
('1','1','1423064213950','8888.00','aa','0','0','0','2015-02-04 23:36:53','0000-00-00 00:00:00','[{"name":"豪华套餐","num":"1","price":"8888"}]');
DROP TABLE IF EXISTS  `shop_user`;
CREATE TABLE `shop_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `address` text,
  `update_dt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into `shop_user`(`id`,`uid`,`username`,`phone`,`password`,`address`,`update_dt`) values
('1','410000100','dong hao','+8615262731827',null,'nantong university',null);
DROP TABLE IF EXISTS  `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `user_roles`(`user_id`,`role_id`) values
('1','1'),
('1','2'),
('2','2'),
('3','1');
DROP TABLE IF EXISTS  `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into `users`(`id`,`username`,`password`,`salt`,`locked`) values
('1','admin','40ece1b68be64912285e0c54452186b5','tps','0'),
('2','adminuser','adminuser',null,'0'),
('3','user','5916e05ee69eef15c325707608139937',null,'0');
SET FOREIGN_KEY_CHECKS = 1;