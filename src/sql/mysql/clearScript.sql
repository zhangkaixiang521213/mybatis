#清库脚本
SET SQL_SAFE_UPDATES = 0;
delete from coupon where exists(select * from coupon_user cu where cu.coupon_id=_id);
delete  from coupon_red_envelop;
delete  from coupon_user;
delete  from coupon_pay_log;
delete  from user_wechat_relate;