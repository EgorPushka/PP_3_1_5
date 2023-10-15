create database mydbtest;

insert into users (username, password, useremail, userage)
values ('user', '$2a$12$npwjlEUtsgRx4PLxJXIOI.tMD8178g0kAE2QJPuW0ulKSeA54JhBy', 'luke.skywalker@gmail.com', 23),
       ('admin', '$2a$12$vsoeAETi5OoVk81nWhYoI.PQqD3m1zl.0wYX.A7F1JJbQT8CTcrAi', 'john.snow@winterfall.com', 31),
       ('manager', '$2a$12$Nuh5uUZjcLTifZf5GNND5uabPgiSalMBZ3y7miZF5/CxAaJzd07Ni', 'neo.anderson@gmail.com', 32);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('AUTH_MANAGERS');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);