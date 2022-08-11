drop table purchase_point;

create table if not exists purchase_point
(
    id            bigint auto_increment
        primary key,
    balance       double         null,
    create_time   datetime(6) null,
    history_total double         null,
    update_time   datetime(6) null,
    user_id       bigint      null
);

INSERT INTO purchase_point (balance, create_time, history_total, update_time, user_id) VALUES (999, '2022-08-10 14:24:38', 99999, '2022-08-10 14:24:41', 1);
