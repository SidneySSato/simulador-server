sqlplus -s JL/JL2017 << EOF
whenever sqlerror exit sql.sqlcode;
set echo off 
set heading off

@0301TB_DOMI_full.sql
@0302TB_CNTD_DOMI_full.sql

select count(*) from tb_domi;
select count(*) from tb_cntd_domi;

exit;
EOF
