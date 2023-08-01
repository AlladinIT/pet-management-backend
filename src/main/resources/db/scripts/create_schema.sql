create table color
(
    id    bigint not null,
    color varchar(255),
    primary key (id)
);


create table country
(
    id      bigint not null,
    country varchar(255),
    primary key (id)
);


create table person
(
    id       bigint not null,
    name     varchar(255),
    password varchar(255),
    primary key (id)
);


create table pet
(
    id         bigint not null,
    code       bigint,
    name       varchar(255),
    color_id   bigint not null,
    country_id bigint,
    person_id  bigint not null,
    type_id    bigint not null,
    primary key (id)
);


create table type
(
    id   bigint not null,
    type varchar(255),
    primary key (id)
);



create sequence color_id_seq start with 1 increment by 1;

create sequence country_id_seq start with 1 increment by 1;

create sequence person_id_seq start with 4 increment by 1;

create sequence pet_id_seq start with 4 increment by 1;

create sequence type_id_seq start with 1 increment by 1;

alter table if exists pet
    add constraint FKk3lrac1ks0pgabns6ih1xubdg
    foreign key (color_id)
    references color;

alter table if exists pet
    add constraint FKk52bg9gw5v4mfu4inwqck0a54
    foreign key (country_id)
    references country;

alter table if exists pet
    add constraint FK85lcupld4b6if5e48nahfe378
    foreign key (person_id)
    references person;

alter table if exists pet
    add constraint FKe575pwyhy4o7k8wci2hmoj1a2
    foreign key (type_id)
    references type;