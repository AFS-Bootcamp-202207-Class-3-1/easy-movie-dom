UPDATE `user` SET avatar = 'https://www.meishujixun.com/uploads/fc6c00a6231b61cbf4f7282352c24b4d.jpg', birthday = '2002-08-09 15:57:29', create_time = '2022-08-10 14:28:52', email = 'coffeestudio@oocl.com', gender = 'male', phone_number = '19089896767', update_time = '2022-08-10 14:16:33.522000', username = 'Carlos' WHERE id = 1;

INSERT INTO purchase_point (balance, create_time, history_total, update_time, user_id) VALUES (999, '2022-08-10 14:24:38', 99999, '2022-08-10 14:24:41', 1);


UPDATE schedule SET screen_text = '国语 2D 豪华厅1' WHERE id = 1;
UPDATE schedule SET screen_text = '国语 3D 贵宾厅2' WHERE id = 2;
UPDATE schedule SET screen_text = '国语 2D 豪华厅3' WHERE id = 3;
UPDATE schedule SET screen_text = '国语 3D 贵宾厅4' WHERE id = 4;
UPDATE schedule SET screen_text = '国语 2D 超级厅5' WHERE id = 5;
UPDATE schedule SET screen_text = '国语 3D 贵宾厅1' WHERE id = 6;
UPDATE schedule SET screen_text = '国语 2D 贵宾厅2' WHERE id = 7;
UPDATE schedule SET screen_text = '国语 3D 豪华厅3' WHERE id = 8;
UPDATE schedule SET screen_text = '国语 2D 贵宾厅4' WHERE id = 9;
UPDATE schedule SET screen_text = '国语 3D 私人厅5' WHERE id = 10;

create table seating
(
    `id`           bigint auto_increment primary key,
    `remaining_seats`       int null,
    `total_seat`     int  null,
    `seats`  varchar(255)  null,
    `update_time`  datetime(6)  null,
    `username`     varchar(255) null
);




