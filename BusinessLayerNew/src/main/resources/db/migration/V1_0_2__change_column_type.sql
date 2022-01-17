alter table "user" drop column deleted;
-- TODO: почему не timestamp с датой удаления?
alter table "user" add column deleted BOOLEAN default false;
