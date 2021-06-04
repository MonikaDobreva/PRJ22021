create role joel with login password 'password';
alter database postgres owner to joel;

drop role joel;

alter database postgres owner to postgres;

create role readonly;

grant all privileges on schema public to readonly;

create group ais;

grant all privileges on schema public to ais;

grant all privileges on table flightsview to ais;

grant all privileges on schema public to joel;

create user joel with login password 'password';

alter group ais add user joel;