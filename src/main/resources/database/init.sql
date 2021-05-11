create table if not exists supported_cars (
    id serial primary key,
    brand varchar(100),
    model varchar(100),
    mileage varchar(100),
    release_year varchar(100),
    body varchar(100),
    color varchar(100),
    engine varchar(100),
    drive varchar(100),
    wheel varchar(100),
    number_of_owners varchar(100),
    price varchar(100),
    order_support_car_id int,
    picture varchar(100)
);
create table if not exists new_cars (
    id serial primary key,
    brand varchar(100),
    model varchar(100),
    release_year varchar(100),
    body varchar(100),
    color varchar(100),
    engine varchar(100),
    drive varchar(100),
    wheel varchar(100),
    price varchar(100),
    order_new_car_id int,
    picture varchar(100)
);

create table if not exists orders_new (
    id serial primary key,
    user1_id int,
    new_car_id int,
    creation_date varchar(100),
    status varchar(100)
);
create table if not exists orders_support (
    id serial primary key,
    user2_id int,
    support_car_id int,
    creation_date varchar(100),
    status varchar(100)
);
CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    password VARCHAR(200) NOT NULL,
    type VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL
);