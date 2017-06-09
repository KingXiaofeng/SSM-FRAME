-- 创建图书表

CREATE TABLE book (
  book_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  name VARCHAR(120) NOT NULL COMMENT '图书名称',
  number INT NOT NULL COMMENT '库存数量',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (book_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '图书存表';

INSERT INTO book (name, number, start_time, end_time)
VALUES ('Java程序设计',100,'2017-05-13 00:00:00','2017-07-14 00:00:00'),
  ('数据结构',200,'2017-02-13 00:00:00','2017-02-14 00:00:00'),
  ('设计模式',300,'2017-02-13 00:00:00','2017-02-14 00:00:00'),
  ('编译原理',400,'2017-02-13 00:00:00','2017-02-14 00:00:00');

-- 创建预约图书表
CREATE TABLE appointment (
  `book_id` bigint(20) NOT NULL COMMENT '图书ID',
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `appoint_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '预约时间' ,
  PRIMARY KEY (`book_id`, `student_id`),
  INDEX `idx_appoint_time` (`appoint_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约图书表';