sqlplus -s JL/JL2017 << EOF
whenever sqlerror exit sql.sqlcode;
set echo off 
set heading off

@0401TB_PROD.sql
@0402TB_PROD_SEGD.sql
@0403TB_PROD_SEGD_PLAN.sql
@0404TB_PROD_SEGD_PLAN_SEGM.sql
@0405TB_PROD_SEGD_PLAN_IS.sql
@0406TB_CNAL_PROD_SEGD.sql


select count(*) from TB_CNAL_PROD_SEGD;

exit;
EOF
