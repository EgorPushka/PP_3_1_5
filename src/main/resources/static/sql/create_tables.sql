create table Users
    (
        id int PRIMARY KEY AUTO_INCREMENT,
        user_name varchar(50) not null ,
        user_age int not null ,
        user_email varchar(50) not null,
        user_pass varchar(100) not null
    );

insert ignore into users (user_name, user_age, user_email, user_pass)
values ("Luke Skywalker", 23, "luke.skywalker@gmail.com", "user"),
       ("Ellis Ripley", 35, "ellie.arroway@gmail.com", "user"),
       ("Neo Anderson", 30, "neo.anderson@gmail.com", "user"),
       ("Arthur Dent", 32, "arthur.dent@gmail.com", "user"),
       ("Ellie Arroway", 28, "ellie.arroway@gmail.com", "user"),
       ("Yoda", 900, "yoda@gmail.com", "user"),
       ("Gandalf", 2019, "gandalf@gmail.com", "user"),
       ("Optimus Prime", 10000, "optimus.prime@gmail.com", "user");