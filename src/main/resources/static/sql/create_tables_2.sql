create table users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    useremail VARCHAR(255) UNIQUE,
    userage   INT         not null default 0
);

create table roles
(
    id   int primary key auto_increment,
    name varchar(30)
);

create table users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_MANAGER');

insert into users (username, password, useremail, userage)
    value
    ('user', '$2a$12$npwjlEUtsgRx4PLxJXIOI.tMD8178g0kAE2QJPuW0ulKSeA54JhBy', 'luke.skywalker@gmail.com', 23);

insert into users_roles (user_id, role_id) values (1, 2);