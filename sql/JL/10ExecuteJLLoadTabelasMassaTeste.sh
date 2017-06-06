sqlplus -s JL/JL2017 << EOF
whenever sqlerror exit sql.sqlcode;
set echo off 
set heading off

@1001TB_MassaDadosJump.sql

select count(*) from TB_RESP_CORPORATIVA;

exit;
EOF
