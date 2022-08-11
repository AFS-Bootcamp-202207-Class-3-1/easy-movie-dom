create table if not exists `comments`
(
    id                 bigint auto_increment primary key,
    user_id            long          null,
    movie_id           long          null,
    comment            varchar(255)  null,
    score              double        null,
    expiration_time    datetime(6)   null,
    create_time        datetime(6)   null,
    update_time        datetime(6)   null
);