drop table if exists user;
create table user(
    `id` int primary key auto_increment,
    `name` varchar(255) not null,
    `age` int not null,
    `gender` int not null
);
