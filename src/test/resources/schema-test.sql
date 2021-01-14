create table category
(
    id   int auto_increment
        primary key,
    name varchar(100) charset utf8 not null
);

create table country
(
    id   int auto_increment
        primary key,
    name varchar(200) charset utf8 not null
);

create table city
(
    id         int auto_increment
        primary key,
    name       varchar(300) charset utf8 not null,
    country_id int                       not null,
    constraint city___country_fk
        foreign key (country_id) references country (id)
);

create table users
(
    id         int auto_increment
        primary key,
    username   varchar(36) charset utf8 not null,
    date       date                     null,
    gender     tinyint(1)               null,
    country_id int                      not null,
    city_id    int                      not null,
    constraint user_username_uindex
        unique (username),
    constraint user___city_fk
        foreign key (city_id) references city (id),
    constraint user___country_fk
        foreign key (country_id) references country (id)
);

create table topic
(
    id          int auto_increment
        primary key,
    name        varchar(100) charset utf8   not null,
    description varchar(10000) charset utf8 not null,
    user_id     int                         not null,
    category_id int                         not null,
    constraint topic___category_fk
        foreign key (category_id) references category (id),
    constraint topic___user_fk
        foreign key (user_id) references users (id)
);

create table comment
(
    id          int auto_increment
        primary key,
    description varchar(10000) charset utf8 not null,
    topic_id    int                         not null,
    user_id     int                         not null,
    constraint comment___topic_fk
        foreign key (topic_id) references topic (id),
    constraint comment___user_fk
        foreign key (user_id) references users (id)
);

