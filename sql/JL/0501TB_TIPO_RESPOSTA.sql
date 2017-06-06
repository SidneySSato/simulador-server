SET DEFINE OFF

delete from TB_TIPO_RESPOSTA;

INSERT INTO TB_TIPO_RESPOSTA (cd_tipo_resposta, ds_tipo_resposta)
VALUES (1,'Lista');

INSERT INTO TB_TIPO_RESPOSTA (cd_tipo_resposta, ds_tipo_resposta)
VALUES (2,'Input de Dados');

INSERT INTO TB_TIPO_RESPOSTA (cd_tipo_resposta, ds_tipo_resposta)
VALUES (3,'Dados Corporativos');
commit;
