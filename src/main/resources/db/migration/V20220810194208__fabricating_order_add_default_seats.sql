drop table `order`;

create table if not exists `order`
(
    id                 bigint auto_increment primary key,
    user_id            bigint         null,
    movie_id           bigint          null,
    theater_id         bigint          null,
    schedule_id        bigint          null,
    is_ticket_used     boolean      null,
    votes              int          null,
    quick_mark_key     varchar(255) null,
    snacks_total_price int          null,
    snacks_id          bigint         null,
    total_price        double       null,
    is_paid            boolean      null,
    is_rebook          boolean      null,
    is_refund          boolean      null,
    seats               varchar(255) null,
    expiration_time    datetime(6)  null,
    create_time        datetime(6)  null,
    update_time        datetime(6)  null
);


INSERT INTO `order` (id, user_id, movie_id, theater_id, schedule_id, is_ticket_used, votes, quick_mark_key, snacks_total_price, snacks_id, total_price, is_paid, is_rebook, is_refund, seats,expiration_time, create_time, update_time) VALUES (1, 1, 1, 1, 1, 1, 1, '1', 1, 1, 1, 1, 1, 1, '00000000000000000000000000000000000','2022-08-10 10:21:49', '2022-08-10 10:21:51', '2022-08-10 10:21:55');

