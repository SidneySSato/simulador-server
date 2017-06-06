-- para apagar o schema
drop user JL cascade;

-- para criar o schema e dar os privilegios, logar com system no oracle
create user JL identified by JL2017;
grant create session to JL;
grant create table to JL;
grant unlimited tablespace to JL;
grant select any table to JL;
grant create sequence to JL;
grant select any sequence to JL;

