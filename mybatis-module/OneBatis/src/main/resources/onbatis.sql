-- onebatis.t_user definition

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO onebatis.t_user (user_name,password,address,phone) VALUES
('张三','12346','北京','1008611');
INSERT INTO onebatis.t_user (user_name,password,address,phone) VALUES
('李四','12346','上海','1008612');
INSERT INTO onebatis.t_user (user_name,password,address,phone) VALUES
('王五','12346','广东','1008613');
INSERT INTO onebatis.t_user (user_name,password,address,phone) VALUES
('赵六','12346','杭州','1008614');