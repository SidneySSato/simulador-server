sqlplus -s JL/JL2017 << EOF
whenever sqlerror exit sql.sqlcode;
set echo off 
set heading off

@0201JL_SCHEMA.ddl

exit;
EOF
