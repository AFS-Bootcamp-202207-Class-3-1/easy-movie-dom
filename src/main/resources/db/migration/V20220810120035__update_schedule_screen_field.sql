drop table schedule;

create table if not exists schedule
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6) null,
    end_time    datetime(6) null,
    movie_id    bigint      null,
    price       double      null,
    start_time  datetime(6) null,
    theater_id  bigint      null,
    update_time datetime(6) null,
    screen_text  varchar(255) null
);


INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (1, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 55, '2022-08-08 20:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (2, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 2, 100, '2022-08-08 20:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (3, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 50, '2022-08-08 22:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (4, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 51, '2022-08-09 20:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (5, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 52, '2022-08-09 22:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (6, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 53, '2022-08-10 20:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (7, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 54, '2022-08-10 22:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (8, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 59, '2022-08-11 20:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (9, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 65, '2022-08-11 22:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time, screen_text) VALUES (10, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 63, '2022-08-12 20:48:08', 1, '2022-08-08 20:48:12','英语 2D 贵宾厅2');
