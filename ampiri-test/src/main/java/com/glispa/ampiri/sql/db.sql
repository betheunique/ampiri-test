CREATE TABLE `t_ad_dimension` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `height` int(10) DEFAULT '0',
  `width` int(10) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `t_app` (
  `id` varchar(50) NOT NULL,
  `banned` tinyint(1) DEFAULT '1',
  `min_height` int(10) DEFAULT '0',
  `max_height` int(10) DEFAULT '0',
  `min_width` int(10) DEFAULT '0',
  `max_width` int(10) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_ad_place` (
  `ad_id` varchar(50) NOT NULL,
  `f_app_id` varchar(50) DEFAULT NULL,
  `ad_type` varchar(50) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ad_id`),
  KEY `t_app_id_FK` (`f_app_id`),
  CONSTRAINT `t_app_id_FK` FOREIGN KEY (`f_app_id`) REFERENCES `t_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_ad_dimension` (`id`, `height`, `width`, `created_at`, `updated_at`)
VALUES
	(1, 320, 240, '2017-05-09 21:22:38', '2017-05-09 21:22:38'),
	(2, 640, 480, '2017-05-09 21:23:08', '2017-05-09 21:23:08'),
	(3, 1280, 720, '2017-05-09 21:23:24', '2017-05-09 21:23:24'),
	(4, 1440, 900, '2017-05-09 21:23:37', '2017-05-09 21:23:37'),
	(5, 1200, 700, '2017-05-10 01:14:10', '2017-05-10 01:14:10');

INSERT INTO `t_ad_place` (`ad_id`, `f_app_id`, `ad_type`, `created_at`, `updated_at`)
VALUES
	('1f855d85-6b3b-4113-af6b-c87b1b39e185', '2e95de7a-12c3-421f-b6dd-fe19623a3763', 'video', '2017-05-09 21:27:08', '2017-05-09 22:41:04'),
	('9f855d85-6b3b-5223-af6b-c87b1b39e295', '3e95de7a-12c3-431f-b6dd-fe19623a3893', 'image', '2017-05-10 01:34:24', '2017-05-10 01:34:24');

INSERT INTO `t_app` (`id`, `banned`, `min_height`, `max_height`, `min_width`, `max_width`, `created_at`, `updated_at`)
VALUES
	('2e95de7a-12c3-421f-b6dd-fe19623a3763', 0, 640, 1280, 240, 900, '2017-05-09 21:26:10', '2017-05-09 22:26:50'),
	('3e95de7a-12c3-431f-b6dd-fe19623a3893', 0, 400, 600, 240, 320, '2017-05-10 01:32:14', '2017-05-10 01:32:14');
