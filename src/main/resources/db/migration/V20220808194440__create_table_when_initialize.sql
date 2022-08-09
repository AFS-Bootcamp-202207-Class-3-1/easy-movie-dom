create table if not exists actor_movie_relation
(
    id       bigint auto_increment
        primary key,
    actor_id bigint null,
    movie_id bigint null
);

create table if not exists director_movie_relation
(
    id          bigint auto_increment
        primary key,
    director_id bigint null,
    movie_id    bigint null
);

create table if not exists figure
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6)  null,
    img_url     varchar(255) null,
    name        varchar(255) null,
    update_time datetime(6)  null
);

create table if not exists movie
(
    id              bigint auto_increment
        primary key,
    box_office      varchar(255) null,
    create_time     datetime(6)  null,
    description     varchar(255) null,
    duration        int          null,
    hot             int          null,
    image_url       varchar(255) null,
    name            varchar(255) null,
    release_country varchar(255) null,
    release_date    datetime(6)  null,
    release_status  int          null,
    score           double       null,
    types           varchar(255) null,
    update_time     datetime(6)  null,
    english_name    varchar(255) null
);

create table if not exists purchase_point
(
    id            bigint auto_increment
        primary key,
    balance       int         null,
    create_time   datetime(6) null,
    history_total int         null,
    update_time   datetime(6) null,
    user_id       bigint      null
);

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
    update_time datetime(6) null
);

create table if not exists theater
(
    id           bigint auto_increment
        primary key,
    address      varchar(255) null,
    create_time  datetime(6)  null,
    description  varchar(255) null,
    image_url    varchar(255) null,
    name         varchar(255) null,
    phone_number varchar(255) null,
    update_time  datetime(6)  null
);

create table if not exists `theater_movie_relation`
(
    `id`         bigint auto_increment
        primary key,
    `movie_id`   bigint null,
    `theater_id` bigint null
);
