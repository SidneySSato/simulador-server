sqlplus -s JL/JL2017 << EOF
whenever sqlerror exit sql.sqlcode;
set echo off 
set heading off

@0501TB_TIPO_RESPOSTA.sql

select count(*) from TB_TIPO_RESPOSTA;

exit;
EOF
