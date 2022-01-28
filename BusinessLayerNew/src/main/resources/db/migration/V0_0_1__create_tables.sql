create table IF NOT EXISTS airport
(
    id                          bigserial       primary key                  not null,
    name                        varchar                                      not null,
    deleted                     timestamp
);

create table IF NOT EXISTS "user"
(
    id                          bigserial        primary key                 not null,
    first_name                  varchar                                      not null,
    last_name                   varchar                                      not null,
    date_of_birth               date                                         not null,
    email                       varchar(50)                                  not null        unique,
    password                    varchar(50)                                  not null,
    deleted                     timestamp,
    role                        varchar(20)     references role(role)        not null
);
-- TODO: зачем таблица?
-- в ней хранятся authorities
create table role
(
    role                        varchar(20)      primary key                 not null
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





