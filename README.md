## DATABASE
***

create database shoppingdb;
use shoppingdb;

create table product_info
(
	product_id int primary key auto_increment,
    product_name varchar(20),
    product_qty int,
    product_price double check(product_price>=0)
);

insert into product_info (product_name, product_qty, product_price) values ( 'biryani', 5, 190 ),('Fried_Rice', 4, 120),('Paneer', 2, 90),('Tava_Pulaw', 3, 60);

select * from product_info;

***

create table order_info
(
	order_id int primary key auto_increment,
    customer_name varchar(20),
    product_id int,
    product_qty int,
    total_amt double,
    foreign key (product_id) references product_info(product_id)
);

select * from order_info;

***

delimiter //
create procedure placeOrder(in custName varchar(20), in productId int, in productQty int, out status boolean)
begin
	declare qty int;
    select product_qty into qty from product_info where product_id = productId;
    
    set status = false;
    if qty >= productQty then insert into order_info(customer_name, product_id, product_qty) values (custName, productId, productQty);
    update product_info set product_qty = product_qty - productQty where product_id = productId;
    set status = true;
    end if;
end //
delimiter ;

***

	SELECT o.order_id, o.customer_name, p.product_name, o.product_qty, o.product_qty * p.product_price AS Total_Price
	FROM order_info o INNER JOIN product_info p ON o.product_id = p.product_id ORDER BY o.order_id asc;

***
