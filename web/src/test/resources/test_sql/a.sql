CREATE TABLE `ad_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` varchar(100) DEFAULT NULL COMMENT '游戏',
  `ad_id` varchar(100) DEFAULT NULL COMMENT '广告ID',
  `type` varchar(50) DEFAULT NULL COMMENT '事件类型',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `cookie` varchar(100) DEFAULT NULL COMMENT 'cookie',
  `date` datetime DEFAULT NULL COMMENT '时间',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`, `game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8