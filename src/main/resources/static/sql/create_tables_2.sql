create table users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    useremail VARCHAR(255) UNIQUE,
    userage   INT          not null default 0
);

insert into users (username, password, useremail, userage)
values ('user', '$2a$12$npwjlEUtsgRx4PLxJXIOI.tMD8178g0kAE2QJPuW0ulKSeA54JhBy', 'luke.skywalker@gmail.com', 23),
       ('admin', '$2a$12$vsoeAETi5OoVk81nWhYoI.PQqD3m1zl.0wYX.A7F1JJbQT8CTcrAi', 'john.snow@winterfall.com', 31),
       ('manager', '$2a$12$Nuh5uUZjcLTifZf5GNND5uabPgiSalMBZ3y7miZF5/CxAaJzd07Ni', 'neo.anderson@gmail.com', 32);

create table roles
(
    id   int primary key auto_increment,
    name varchar(30)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('AUTH_MANAGERS');


create table users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id) on delete CASCADE,
    foreign key (role_id) references roles (id)
);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);

alter table users
    drop column user_age,
    drop column user_name,
    drop column user_email;

delete from users;