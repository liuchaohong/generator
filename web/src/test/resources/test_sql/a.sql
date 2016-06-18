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
;
CREATE TABLE `wh_metadata_ds` (
  `ds_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库ID',
  `ds_type` varchar(20) DEFAULT NULL COMMENT '数据源类型，如mysql、oracle',
  `host` varchar(50) DEFAULT NULL COMMENT '主机名',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `db_name` varchar(50) DEFAULT NULL COMMENT '数据库名',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `privilege` varchar(20) DEFAULT 'private' COMMENT '数据源权限，如共享、私有',
  `passport` varchar(50) DEFAULT NULL COMMENT '用户通行证',
  `effective` tinyint(4) DEFAULT '1' COMMENT '是否可用，1可用，0不可用',
  `remarks` varchar(300) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ds_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='数据源'