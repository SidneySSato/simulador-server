sqlplus -s system/password << EOF
whenever sqlerror exit sql.sqlcode;
set echo off 
set heading off

@0101RecreateJLSchema.ddl

exit;
EOF
