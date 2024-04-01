create sequence role_seq start with 1 increment by 50;

create sequence users_seq start with 1 increment by 50;

create table role (
                      id bigint not null,
                      name varchar(255),
                      primary key (id)
);

create table users (
                       blocked boolean not null,
                       id bigint not null,
                       name varchar(255),
                       password varchar(255),
                       username varchar(255),
                       primary key (id)
);

create table users_roles (
                             roles_id bigint not null,
                             user_id bigint not null
);

alter table if exists users_roles
    add constraint FK15d410tj6juko0sq9k4km60xq
        foreign key (roles_id)
            references role;

alter table if exists users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id)
            references users;