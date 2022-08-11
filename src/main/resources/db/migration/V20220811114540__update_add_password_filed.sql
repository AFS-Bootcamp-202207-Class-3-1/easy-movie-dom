drop table `user`;

create table `user`
(
    `id`           bigint auto_increment primary key,
    `avatar`       varchar(255) null,
    `birthday`     datetime(6)  null,
    `create_time`  datetime(6)  null,
    `email`        varchar(255) null,
    `gender`       varchar(255) null,
    `phone_number` varchar(255) null,
    `update_time`  datetime(6)  null,
    `username`     varchar(255) null,
    `password`     varchar(255) null
);

INSERT INTO `user` (id, avatar, birthday, create_time, email, gender, phone_number, update_time, username,password)
VALUES (1, 'https://p0.pipi.cn/mmdb/25bfd6d706d51b7a3567cbc55ff22a4b2686e.jpg?imageView2/1/w/464/h/644', '2002-08-09 15:57:29', '2022-08-09 15:57:33', '1110afs@00cl.com', 'male', '19089896767', '2022-08-09 15:57:51', 'Carlos','1234');

UPDATE `user` SET avatar = 'https://p0.pipi.cn/mmdb/25bfd6d706d51b7a3567cbc55ff22a4b2686e.jpg?imageView2/1/w/464/h/644', birthday = '2002-08-09 15:57:29', create_time = '2022-08-09 19:43:11', email = '110@oocl.com', gender = 'male', phone_number = '19089896767', update_time = '2022-08-09 19:42:41.458000', username = 'Carlos',password = '1234' WHERE id = 1;
