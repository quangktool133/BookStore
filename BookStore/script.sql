create database BookStore;
use BookStore;

create table if not exists category
(
    id   int auto_increment
    primary key,
    name varchar(255) charset utf8 null
    );

create table if not exists book
(
    id          int auto_increment
    primary key,
    title       varchar(255) charset utf8 null,
    price       float                     null,
    author      varchar(255) charset utf8 null,
    status      int                       null,
    description longtext charset utf8     null,
    category_id int                       null,
    quantity    int                       null,
    name        varchar(255)              null,
    image       text                      null,
    constraint book_category_id_fk
    foreign key (category_id) references category (id)
    );

create table if not exists discount
(
    id          int auto_increment
    primary key,
    code        varchar(255) null,
    expire_date date         null,
    status      int          null,
    percent     int          null,
    constraint code
    unique (code)
    );

create table if not exists user
(
    id        int auto_increment
    primary key,
    user_name varchar(255) null,
    password  varchar(255) null,
    role      int          null,
    constraint user_name
    unique (user_name)
    );

create table if not exists bill
(
    id            int auto_increment
    primary key,
    user_name     varchar(255) null,
    created_date  date         null,
    total         float        null,
    discount_code varchar(255) null,
    constraint bill_discount_code_fk
    foreign key (discount_code) references discount (code),
    constraint bill_user_user_name_fk
    foreign key (user_name) references user (user_name)
    );

create table if not exists bill_detail
(
    id        int auto_increment
    primary key,
    bill_id   int   null,
    book_id   int   null,
    price     float null,
    sub_total float null,
    quantity  int   null,
    constraint bill_detail_bill_id_fk
    foreign key (bill_id) references bill (id),
    constraint bill_detail_book_id_fk
    foreign key (book_id) references book (id)
    );

create table if not exists cart
(
    id        int auto_increment
    primary key,
    book_id   int          null,
    quantity  int          null,
    user_name varchar(255) null,
    constraint cart_book_id_fk
    foreign key (book_id) references book (id),
    constraint cart_user_user_name_fk
    foreign key (user_name) references user (user_name)
    );

