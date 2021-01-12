create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(item_id int primary key auto_increment, item_name varchar(40), price decimal(10,2));
