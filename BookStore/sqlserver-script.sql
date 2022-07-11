create database BookStore
go

use BookStore;
go

create table category
(
    id   int identity(1,1)
        primary key,
    name nvarchar(255) null
)
    go

create table book
(
    id          int identity(1,1)
        primary key,
    title       nvarchar(255) null,
    price       float                     null,
    author      nvarchar(255) null,
    status      int                       null,
    description nvarchar(max)     null,
    category_id int                       null,
    quantity    int                       null,
    name        nvarchar(255)              null,
    image       varchar(max)                      null,
    constraint book_category_id_fk
    foreign key (category_id) references category (id)
    )
    go

create table discount
(
    id          int identity (1,1)
        primary key,
    code        varchar(255) null,
    expire_date date         null,
    status      int          null,
    "percent"     int          null,
    constraint code
        unique (code)
)
    go

create table "user"
(
    id        int identity (1,1)
        primary key,
    user_name varchar(255) null,
    password  varchar(255) null,
    role      int          null,
    constraint user_name
        unique (user_name)
)
    go

create table bill
(
    id            int identity (1,1)
        primary key,
    user_name     varchar(255) null,
    created_date  date         null,
    total         float        null,
    discount_code varchar(255) null,
    constraint bill_discount_code_fk
        foreign key (discount_code) references discount (code),
    constraint bill_user_user_name_fk
        foreign key (user_name) references "user" (user_name)
)
    go

create table bill_detail
(
    id        int identity (1,1)
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
)
    go

create table cart
(
    id        int identity (1,1)
        primary key,
    book_id   int          null,
    quantity  int          null,
    user_name varchar(255) null,
    constraint cart_book_id_fk
        foreign key (book_id) references book (id),
    constraint cart_user_user_name_fk
        foreign key (user_name) references "user" (user_name)
)

