drop database ims_test;
create database if not exists ims_test;
create table ims_test.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table ims_test.items(item_id int primary key auto_increment, item_name varchar(40), price decimal(10,2));
create table ims_test.orders(order_id int primary key auto_increment, customer_id int, order_date varchar(40), total_price decimal(10,2), foreign key(customer_id) references customers(id));
create table ims_test.ordersItems(orditems_id int primary key auto_increment, order_id int, item_id int, quantity int, foreign key(order_id) references orders(order_id), foreign key(item_id) references items(item_id));