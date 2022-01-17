create table "user"
(
    id                  bigserial        primary key        not null,
    first_name          varchar                             not null,
    last_name           varchar                             not null,
    date_of_birth       date                                not null,
--     TODO: ограничение по символам у varchar
    email               varchar                             not null        unique,
    password            varchar                             not null,
    deleted             date                                not null,
    role                varchar                             not null
);
-- TODO: зачем таблица?
create table role
(
    role                varchar         primary key         not null
);

create table flight
(
    id                  bigserial       primary key         not null,
    airport_from_id     bigint                              not null,
    airport_to_id       bigint                              not null,
--     TODO: почему не timestamp?
    flight_date         date                                not null
);

create table airport
(
    id                  bigserial       primary key         not null,
    name                varchar                             not null
);

create table ticket
(
    id                  bigserial       primary key         not null,
    flight_id           bigint                              not null,
    user_id             bigint                              not null
);
