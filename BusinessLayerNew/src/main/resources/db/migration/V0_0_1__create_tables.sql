create table IF NOT EXISTS airport
(
    id                          bigserial       primary key                  not null,
    name                        varchar(50)                                  not null,
    deleted                     timestamp
);

-- TODO: зачем таблица?
-- в ней хранятся authorities
-- create table role
-- (
--     id                          bigserial        primary key                 not null,
--     role                        varchar(25)
-- );

create table IF NOT EXISTS "user"
(
    id                          bigserial        primary key                 not null,
    user_name                   varchar(50)                                    not null,
    first_name                  varchar(50)                                  not null,
    last_name                   varchar(50)                                  not null,
    date_of_birth               date                                         not null,
    email                       varchar(50)                                  not null        unique,
    password                    varchar(50)                                  not null,
    deleted                     timestamp,
    role                        varchar(25)                                  not null
);

create table IF NOT EXISTS flight
(
    id                          bigserial        primary key                 not null,
    airport_from_id             bigint           references  airport (id)    not null,
    airport_to_id               bigint           references  airport (id)    not null,
    departure_time              timestamp                                    not null,
    arrival_time                timestamp                                    not null,
    number_of_free_seats        int                                          not null,
    deleted                     timestamp
);

create table IF NOT EXISTS ticket
(
    id                          bigserial        primary key                 not null,
    flight_id                   bigint           references flight(id)       not null,
    user_id                     bigint           references "user"(id)       not null,
    deleted                     timestamp
);





