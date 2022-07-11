create table customers (
    id int primary key auto_increment,
    name varchar(100)
);

create table products (
    id int primary key auto_increment,
    description varchar(255),
    unity_value numeric(20,2)
);

create table orders (
    id int primary key auto_increment,
    customer_id int references customers(id),
    order_date timestamp,
    total numeric(20,2)
);

create table order_products (
    id int primary key auto_increment,
    order_id int references orders(id),
    product_id int references products(id),
    quantity int
)