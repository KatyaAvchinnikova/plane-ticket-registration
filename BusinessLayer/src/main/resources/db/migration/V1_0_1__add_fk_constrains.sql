alter table flight add constraint fk_airport_from_fk_flight    foreign key (airport_from_id)   references  airport (id);
alter table flight add constraint fk_airport_to_fk_flight      foreign key (airport_to_id)     references  airport (id);

alter table ticket add constraint fk_user_fk_ticket            foreign key (user_id)           references "user"(id);
alter table ticket add constraint fk_flight_fk_ticket          foreign key (flight_id)         references flight(id);



