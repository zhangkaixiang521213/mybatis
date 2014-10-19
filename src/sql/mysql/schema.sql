#增加到期天数
ALTER TABLE `activity` ADD COLUMN `expire_days` INT(5) NULL DEFAULT NULL COMMENT '到期天数';

#红包金额
CREATE TABLE `coupon_red_amount` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `red_amount` float(10,2) DEFAULT NULL COMMENT '红包金额',
  `quantity` int(5) DEFAULT NULL COMMENT '数量',
  `received_quantity` int(5) DEFAULT '0' COMMENT '已领数量',
  `total_amount` float(10,2) DEFAULT NULL COMMENT '总金额',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1492 DEFAULT CHARSET=utf8 COMMENT='红包金额';

#领红包记录表
CREATE TABLE `coupon_red_envelop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_content_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `red_amount` float(10,2) DEFAULT NULL COMMENT '红包金额',
  `status` int(2) DEFAULT '1' COMMENT '记录状态:1未领取,2已领取',
  `description` varchar(400) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='领红包记录表';

#表coupon与表usercontent关联表
CREATE TABLE `coupon_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` int(11) NOT NULL COMMENT '表coupon外键',
  `user_content_uid` bigint(20) NOT NULL COMMENT '表usercontent外键',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='表coupon与表usercontent关联表';

#券支付日志
CREATE TABLE `coupon_pay_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `coupon_id` int(11) DEFAULT NULL COMMENT '券id',
  `pay_value` float(5,2) DEFAULT NULL COMMENT '支付金额',
  `description` varchar(400) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='券支付日志';

#抠电影账号(手机号)与微信号关联表
CREATE TABLE `user_wechat_relate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `wechat_uid` varchar(50) NOT NULL COMMENT '微信号',
  `description` varchar(400) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='抠电影账号(手机号)与微信号关联表';

ALTER TABLE `coupon_red_envelop` 
ADD COLUMN `wechat_uid` VARCHAR(200) NULL COMMENT '微信ID' AFTER `create_time`,
ADD COLUMN `nick_name` VARCHAR(200) NULL COMMENT '微信昵称' AFTER `wechat_uid`,
ADD COLUMN `head_img_url` VARCHAR(500) NULL COMMENT '微信头像' AFTER `nick_name`,
ADD COLUMN `sex` VARCHAR(45) NULL COMMENT '微信性别' AFTER `head_img_url`;

ALTER TABLE `movie`.`coupon_red_envelop` 
ADD COLUMN `expire_date` DATETIME NULL COMMENT '到期时间' AFTER `sex`;

ALTER TABLE `movie`.`coupon_pay_log` 
CHANGE COLUMN `order_id` `order_no` VARCHAR(50) NULL DEFAULT NULL COMMENT '订单id' ;

ALTER TABLE `movie`.`coupon_pay_log` 
ADD COLUMN `status` INT(2) NULL DEFAULT 0 COMMENT '状态：1为支付，2为退款' AFTER `create_time`;

ALTER TABLE `movie`.`coupon_red_envelop` 
ADD COLUMN `source_user_id` BIGINT(20) NULL COMMENT '源用户id（转发用户）' AFTER `expire_date`;

#aid需以当前最大aid加1
INSERT INTO `activity` (`aid`, `activity_content`, `description`, `content`, `pic_small`, `pic_mid`, `pic_big`, `activity_url`, `activity_type`, `order_type`, `need_register`, `platforms`, `city_ids`, `cinema_ids`, `film_ids`, `quote_ids`, `screen_type`, `coupon_ids`, `activity_price`, `show_type`, `start_time`, `end_time`, `top_begin_time`, `top_end_time`, `activity_count`, `activity_remain`, `cnt_per_order`, `gift_per_good`, `order_per_user`, `total_per_user`, `can_buy_more`, `fake_count`, `channel_groups`, `channel_ids`, `uid`, `visible`, `create_time`, `del`, `cnt_per_user`, `isvip`, `expire_days`) VALUES (617,'领红包活动','领红包活动',NULL,NULL,NULL,NULL,NULL,1,NULL,1,'all','all','all',NULL,NULL,NULL,NULL,NULL,1,'0000-00-00 00:00:00','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,1,0,1,0,1,0,'all','all',NULL,1,NULL,NULL,1,0,7)


ALTER TABLE `movie`.`coupon_red_envelop` 
ADD COLUMN `open_id` VARCHAR(200) NULL COMMENT '微信openId' AFTER `source_user_id`;


ALTER TABLE `movie`.`coupon` 
ADD COLUMN `red_envelop_id` INT(11) NULL COMMENT '领红包券id' AFTER `del`;

ALTER TABLE `movie`.`coupon_pay_log` 
ADD COLUMN `red_envelop_id` INT(11) NULL DEFAULT NULL COMMENT '领红包券id' AFTER `status`;


ALTER TABLE `movie`.`coupon_red_envelop` 
ADD COLUMN `is_pay` INT(2) NULL COMMENT '是否已支付：0是未支付，1是已支付' AFTER `open_id`;

ALTER TABLE `movie`.`coupon_pay_log` 
ADD COLUMN `pay_time` DATETIME NULL COMMENT '支付时间' AFTER `red_envelop_id`;


ALTER TABLE `movie`.`coupon_pay_log` 
ADD COLUMN `remain_value` FLOAT(5,2) NULL COMMENT '券可用金额' AFTER `pay_time`;


