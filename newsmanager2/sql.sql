/**
表结构
 */

CREATE TABLE `base_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(3) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `hidden` int(1) DEFAULT NULL,
  `cp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;


CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `is_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

INSERT INTO `admin` (`id`, `uname`, `password`, `creat_time`, `is_delete`)
VALUES
	(12, 'admin', '21232f297a57a5a743894a0e4a801fc3', '2017-05-29 15:42:18', NULL);





CREATE TABLE `news_content` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `creat_time` datetime DEFAULT NULL,
  `is_top` int(1) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;





