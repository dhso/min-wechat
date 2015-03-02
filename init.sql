-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.17 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table tps.articles
DROP TABLE IF EXISTS `articles`;
CREATE TABLE IF NOT EXISTS `articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `writer` varchar(20) DEFAULT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.articles: ~20 rows (approximately)
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` (`id`, `category_id`, `title`, `content`, `writer`, `update_dt`) VALUES
	(1, 1, '优惠活动一', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(2, 1, '优惠活动二', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(3, 2, '婚礼百科一', '婚礼百科一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(4, 4, '操作指南一', '操作指南一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(5, 1, '优惠活动三', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(6, 1, '优惠活动四', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(7, 1, '优惠活动五', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(8, 1, '优惠活动六', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(9, 1, '优惠活动七', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(10, 1, '优惠活动八', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(11, 1, '优惠活动九', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(12, 1, '优惠活动十', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(13, 1, '优惠活动十一', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(14, 1, '优惠活动十二', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(15, 1, '优惠活动十三', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(16, 1, '优惠活动十四', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(17, 1, '优惠活动十五', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(18, 1, '优惠活动十六', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(19, 1, '优惠活动十七', '优惠活动一的内容啦啦啦', 'admin', '2015-01-31 20:25:55'),
	(20, 1, '优惠活动十八', '优惠活动二的内容啦啦啦', 'admin', '2015-01-31 20:25:55');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;


-- Dumping structure for table tps.categories
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.categories: ~4 rows (approximately)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `title`, `update_dt`) VALUES
	(1, '优惠活动', '2015-01-31 20:25:54'),
	(2, '婚礼百科', '2015-01-31 20:25:54'),
	(3, '客片展示', '2015-01-31 20:25:54'),
	(4, '操作指南', '2015-01-31 20:25:54');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;


-- Dumping structure for table tps.config
DROP TABLE IF EXISTS `config`;
CREATE TABLE IF NOT EXISTS `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.config: ~3 rows (approximately)
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` (`id`, `key`, `value`) VALUES
	(1, 'wechat_menu', '{ "button": [ { "name": "服务", "sub_button": [ { "type": "click", "name": "幸福商城", "key": "CK_SHOP" } ] }, { "name": "优惠资讯", "sub_button": [ { "type": "view", "name": "优惠活动", "url": "http://www.soso.com/" }, { "type": "view", "name": "婚礼百科", "url": "http://m.baidu.com/" }, { "type": "view", "name": "客片展示", "url": "http://m.baidu.com/" } ] }, { "name": "地图导航", "sub_button": [ { "type": "view", "name": "步行来这", "url": "http://eastbride-wechat.aliapp.com/front/mapByWalk" }, { "type": "view", "name": "公交车来这", "url": "http://eastbride-wechat.aliapp.com/front/mapByBus" }, { "type": "view", "name": "开车来这", "url": "http://eastbride-wechat.aliapp.com/front/mapByCar" } ] } ] }'),
	(2, 'shop_name', '东方新娘微信商城'),
	(3, 'shop_notification', '欢迎光临');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;


-- Dumping structure for table tps.permissions
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.permissions: ~4 rows (approximately)
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` (`id`, `permission`, `description`, `available`) VALUES
	(1, 'editUser', NULL, 1),
	(2, 'showUser', NULL, 1),
	(3, 'addUser', NULL, 1),
	(4, 'deleteUser', NULL, 1);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;


-- Dumping structure for table tps.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `role`, `description`, `available`) VALUES
	(1, 'admin', 'admin user', 1),
	(2, 'user', 'user', 1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Dumping structure for table tps.roles_permissions
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE IF NOT EXISTS `roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tps.roles_permissions: ~5 rows (approximately)
/*!40000 ALTER TABLE `roles_permissions` DISABLE KEYS */;
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(2, 2);
/*!40000 ALTER TABLE `roles_permissions` ENABLE KEYS */;


-- Dumping structure for table tps.shop_good
DROP TABLE IF EXISTS `shop_good`;
CREATE TABLE IF NOT EXISTS `shop_good` (
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

-- Dumping data for table tps.shop_good: ~0 rows (approximately)
/*!40000 ALTER TABLE `shop_good` DISABLE KEYS */;
INSERT INTO `shop_good` (`id`, `menu_id`, `sort`, `name`, `price`, `old_price`, `image`, `detail`, `status`, `update_dt`) VALUES
	(1, 1, 1, '豪华套餐', '8888', '10001', '54ceeec6a17a5.jpg', '<p>豪华套餐介绍</p><p>巴拉巴拉、、、、</p>	', 1, '2015-02-03 10:48:21');
/*!40000 ALTER TABLE `shop_good` ENABLE KEYS */;


-- Dumping structure for table tps.shop_menu
DROP TABLE IF EXISTS `shop_menu`;
CREATE TABLE IF NOT EXISTS `shop_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.shop_menu: ~0 rows (approximately)
/*!40000 ALTER TABLE `shop_menu` DISABLE KEYS */;
INSERT INTO `shop_menu` (`id`, `name`, `pid`) VALUES
	(1, '婚纱摄影套餐', 0);
/*!40000 ALTER TABLE `shop_menu` ENABLE KEYS */;


-- Dumping structure for table tps.shop_order
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE IF NOT EXISTS `shop_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `totalprice` varchar(32) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `pay_style` varchar(32) NOT NULL,
  `pay_status` varchar(32) NOT NULL,
  `order_status` varchar(32) DEFAULT NULL,
  `create_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cartdata` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.shop_order: ~0 rows (approximately)
/*!40000 ALTER TABLE `shop_order` DISABLE KEYS */;
INSERT INTO `shop_order` (`id`, `user_id`, `order_id`, `totalprice`, `note`, `pay_style`, `pay_status`, `order_status`, `create_dt`, `update_dt`, `cartdata`) VALUES
	(1, '1', '1423064213950', '8888.00', 'aa', '0', '0', '0', '2015-02-04 00:00:00', '2015-02-27 15:23:02', '[{"name":"豪华套餐","num":"1","price":"8888"}]'),
	(4, '1', '1425023150925', '8888.00', 'aaaaa', '0', '0', '0', '2015-02-27 15:45:51', '2015-02-27 15:45:50', '[{"name":"????","num":"1","price":"8888"}]'),
	(5, '1', '1425023317656', '17776.00', 'saads', '0', '0', '0', '2015-02-27 15:48:38', '2015-02-27 15:48:37', '[{"name":"????","num":"2","price":"8888"}]'),
	(6, '1', '1425023636284', '8888.00', 'saads', '0', '0', '0', '2015-02-27 15:53:56', '2015-02-27 15:53:56', '[{"name":"????","num":"1","price":"8888"}]'),
	(7, '2', '1425023789499', '8888.00', '???', '0', '0', '0', '2015-02-27 15:56:29', '2015-02-27 15:56:29', '[{"name":"????","num":"1","price":"8888"}]');
/*!40000 ALTER TABLE `shop_order` ENABLE KEYS */;


-- Dumping structure for table tps.shop_user
DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE IF NOT EXISTS `shop_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `address` text,
  `update_dt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.shop_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `shop_user` DISABLE KEYS */;
INSERT INTO `shop_user` (`id`, `uid`, `username`, `phone`, `password`, `address`, `update_dt`) VALUES
	(1, '410000100', 'dong hao', '+8615262731827', NULL, 'nantong university', NULL),
	(2, '410000101', '? ?', '15262731827', NULL, '??????', NULL);
/*!40000 ALTER TABLE `shop_user` ENABLE KEYS */;


-- Dumping structure for table tps.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table tps.users: ~3 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `salt`, `locked`) VALUES
	(1, 'admin', '40ece1b68be64912285e0c54452186b5', 'tps', 0),
	(2, 'adminuser', 'adminuser', NULL, 0),
	(3, 'user', '5916e05ee69eef15c325707608139937', NULL, 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Dumping structure for table tps.user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tps.user_roles: ~4 rows (approximately)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(3, 1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
