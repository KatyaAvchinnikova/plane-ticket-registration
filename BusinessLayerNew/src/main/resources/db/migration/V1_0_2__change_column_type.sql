alter table "user" drop column deleted;
alter table "user" add column deleted BOOLEAN default false;