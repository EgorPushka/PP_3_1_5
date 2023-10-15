drop table Users;

create table if not exists users
(
    id        int PRIMARY KEY AUTO_INCREMENT,
    username  varchar(50)  not null,
    userlastname  varchar(50)  not null,
    userage   int          not null default 0,
    useremail varchar(50)  not null default 'mail',
    password  varchar(100) not null,
    enabled   boolean      not null default 1
);

create table if not exists users
(
     username varchar(255) primary key ,
     password varchar(255) not null ,
     enabled boolean not null
);


create table if not exists authorities
(
    username varchar(255) not null ,
    authority varchar(50) not null,
    foreign key (username) references users (username),
    unique (username, authority)
);

# create table if not exists People
# (
#     id        int PRIMARY KEY AUTO_INCREMENT,
#     username  varchar(50)  not null,
#     userage   int          not null,
#     useremail varchar(50)  not null,
#     password  varchar(100) not null,
#     enabled   boolean      not null
# );


insert ignore into users (username,userage, useremail, password, enabled)
values ("user", 23, "luke.skywalker@gmail.com", "{bcrypt}$2a$12$npwjlEUtsgRx4PLxJXIOI.tMD8178g0kAE2QJPuW0ulKSeA54JhBy", 1),
       ("admin", 35, "ellie.arroway@gmail.com", "{bcrypt}$2a$12$vsoeAETi5OoVk81nWhYoI.PQqD3m1zl.0wYX.A7F1JJbQT8CTcrAi", 1),
       ("manager", 30, "neo.anderson@gmail.com", "{bcrypt}$2a$12$Nuh5uUZjcLTifZf5GNND5uabPgiSalMBZ3y7miZF5/CxAaJzd07Ni", 1),
       ("user4", 32, "arthur.dent@gmail.com", "user4", 1),
       ("user5", 28, "ellie.arroway@gmail.com", "user5", 1),
       ("user6", 900, "yoda@gmail.com", "user6", 1),
       ("user7", 2019, "gandalf@gmail.com", "user7", 1),
       ("user8", 10000, "optimus.prime@gmail.com", "user8", 1);
