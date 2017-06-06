select * from JL.tb_tipo_resposta;
-- insert da dados para testes
-- tags exemplo

-- para gerar o insert, selecionar o select, ctrl+T selecionar sql2ins, que eh gerado no fim o sql de insert

-- cadastro de arvore dcisao
INSERT INTO JL.TB_arvore_decisao (CD_ARVORE_DECISAO,DS_ARVORE_DECISAO,QT_PRODUTOS,QT_PLANOS,CD_SITU_ARVORE) VALUES (1,'Arvore Teste 1 Ativo',2,3,'A');
-- familia valida
INSERT INTO JL.TB_arvore_familia (CD_ARVORE_DECISAO,CD_RAMO_DOMI) VALUES (1,50474);
-- familia valida
INSERT INTO JL.TB_arvore_canal (CD_ARVORE_DECISAO,CD_CNAL_DOMI) VALUES (1,27121);
commit;


select * from JL.TB_arvore_decisao;
select * from JL.TB_arvore_familia;
select * from JL.TB_arvore_canal;

select * from JL.TB_PRODUTO_ARVORE;

-- busca dos nos ordenado
select
CD_NO_ARVORE as noId,CD_NO_ARVORE_PAI as noPaiId,CD_ARVORE_DECISAO as arvoreId,CD_TIPO_NO,CD_PERGUNTA,
CD_RESPOSTA,CD_PROD,CD_SUBP,CD_SEGD_DOMI,CD_PLAN_DOMI,CD_SEGM_DOMI
from JL.TB_no_arvore 
order by cd_no_arvore;	


delete from JL.TB_no_arvore;
commit;

-- fim massa de dados


-- select de respp corporativas
select pergunta.cd_pergunta, pergunta.ds_pergunta, resposta.cd_resposta, resposta.ds_resposta, pergunta.in_editavel,
tipo_resposta.ds_tipo_resposta,
resp.cd_tc_domi, cntd.ds_cntd_domi_lgdo
from 
JL.tb_resposta resposta, JL.tb_pergunta pergunta,
JL.tb_resp_corporativa resp,
JL.tb_cntd_domi cntd,
JL.tb_tipo_resposta tipo_resposta
where 
resposta.cd_pergunta = pergunta.cd_pergunta
and resposta.cd_resposta = resp.cd_resposta
and pergunta.cd_tipo_resposta = tipo_resposta.cd_tipo_resposta
and cntd.cd_cntd_domi = resp.cd_tc_domi;

-- respostas do tipo range
select pergunta.cd_pergunta, pergunta.ds_pergunta, resposta.cd_resposta, resposta.ds_resposta, resposta.ds_categoria, pergunta.in_editavel,
tipo_resposta.ds_tipo_resposta, rangeresp.range_inicio, rangeresp.range_final
from 
JL.tb_resposta resposta, JL.tb_pergunta pergunta,
JL.tb_range_resposta rangeresp, JL.tb_tipo_resposta tipo_resposta
where 
resposta.cd_pergunta = pergunta.cd_pergunta
and pergunta.cd_tipo_resposta = tipo_resposta.cd_tipo_resposta
and resposta.cd_resposta = rangeresp.cd_range_resposta;


select * from JL.tb_pergunta;
select * from JL.tb_resposta;
select * from JL.tb_pergunta_tag;
select * from JL.tb_tag;
select * from JL.tb_range_resposta;
select * from JL.tb_resp_corporativa;

delete from JL.tb_range_resposta where CD_RANGE_RESPOSTA = 7;
delete from JL.tb_resp_corporativa;
delete from JL.tb_resposta where CD_RESPOSTA in(31,32);

select * from JL.TB_ARVORE_CANAL;
select * from JL.TB_ARVORE_FAMILIA;
select * from JL.TB_ARVORE_DECISAO;
select * from JL.TB_PRODUTO_ARVORE;


UPDATE JL.TB_PRODUTO_ARVORE 
  SET CD_SITU_PROD_ARVORE='I'
  WHERE (CD_PROD = '57' and CD_SUBP = '0650');

commit;

select * from JL.TB_DOMI;

select * from JL.TB_CNTD_DOMI
where cd_domi = 8;

select * from JL.TB_PROD;
select * from JL.TB_PRODUTO_ARVORE;
select * from JL.TB_NO_ARVORE;


-- select geral
select prod.cd_ramo_domi, prod.CD_PROD, prod.CD_SUBP
	from JL.TB_PROD prod,
	JL.TB_PRODUTO_ARVORE prodarv
where prod.CD_PROD = prodarv.CD_PROD
	 and prod.CD_SUBP = prodarv.CD_SUBP;

-- select groupby e in
select cd_cntd_domi, ds_cntd_domi_lgdo from 
JL.tb_cntd_domi where cd_cntd_domi in(
select prod.cd_ramo_domi
	from JL.TB_PROD prod,
	JL.TB_PRODUTO_ARVORE prodarv
where prod.CD_PROD = prodarv.CD_PROD
	 and prod.CD_SUBP = prodarv.CD_SUBP
	 group by prod.cd_ramo_domi);

-- select direto com join
select DISTINCT cntdDomi.cd_cntd_domi, cntdDomi.ds_cntd_domi_lgdo
	from 
	JL.TB_PRODUTO_ARVORE prodarv,
	JL.TB_PROD prod,
	JL.tb_cntd_domi cntdDomi
where prod.CD_PROD = prodarv.CD_PROD
	 and prod.CD_SUBP = prodarv.CD_SUBP
	 and cntdDomi.cd_cntd_domi = prod.CD_RAMO_DOMI;
	 

-- query de canal
select distinct cntddomi.cd_cntd_domi, cntddomi.cd_cntd_domi_lgdo, cntddomi.cd_domi, cntddomi.ds_cntd_domi_lgdo 
from tb_produto_arvore produtoarv 
	cross join tb_prod prod 
	cross join tb_prod_segd prodsegd 
	cross join tb_cnal_prod_segd cnalprodsegd 
	cross join tb_cntd_domi cntddomi 
where 
	produtoarv.cd_prod=prod.cd_prod 
	and produtoarv.cd_subp=prod.cd_subp 
	and prod.cd_prod=prodsegd.cd_prod 
	and prod.cd_subp=prodsegd.cd_subp 
	and prodsegd.cd_prod=cnalprodsegd.cd_prod 
	and prodsegd.cd_subp=cnalprodsegd.cd_subp 
	and prodsegd.cd_segd_domi=cnalprodsegd.cd_segd_domi 
	and cntddomi.cd_cntd_domi=cnalprodsegd.cd_cnal_domi;

select distinct prod1_.cd_ramo_domi, cntddomi4_.cd_cntd_domi as cd_cntd_domi1_4_, cntddomi4_.cd_cntd_domi_lgdo as cd_cntd_domi_lgdo2_4_, cntddomi4_.cd_domi as cd_domi4_4_, 
cntddomi4_.ds_cntd_domi_lgdo as ds_cntd_domi_lgdo3_4_ 
from tb_produto_arvore produtoarv0_ cross join tb_prod prod1_ 
cross join tb_prod_segd prodsegd2_ cross join tb_cnal_prod_segd cnalprodse3_ 
cross join tb_cntd_domi cntddomi4_ 
where 
	produtoarv0_.cd_prod=prod1_.cd_prod 
	and produtoarv0_.cd_subp=prod1_.cd_subp 
	and (prod1_.cd_ramo_domi in (50388, 50466)) 
	and prod1_.cd_prod=prodsegd2_.cd_prod 
	and prod1_.cd_subp=prodsegd2_.cd_subp 
	and prodsegd2_.cd_prod=cnalprodse3_.cd_prod 
	and prodsegd2_.cd_subp=cnalprodse3_.cd_subp 
	and prodsegd2_.cd_segd_domi=cnalprodse3_.cd_segd_domi 
	and cntddomi4_.cd_cntd_domi=cnalprodse3_.cd_cnal_domi;


-- Limpeza da base
delete from JL.tb_range_resposta;
delete from JL.tb_resposta;
delete from JL.tb_resp_corporativa;
delete from JL.tb_pergunta_tag;
delete from JL.tb_pergunta;
delete from JL.tb_resp_corporativa resp;
delete from JL.tb_resposta
commit;



------- testes no arvore

delete from JL.TB_NO_ARVORE;
commit;

select * from JL.TB_PROD_SEGD_PLAN_SEGM plan , TB_CNTD_DOMI cntd
where 
plan.CD_PLAN_DOMI = cntd.CD_CNTD_DOMI
and plan.CD_PROD = '57'
and plan.CD_SUBP = '0650';



select * from JL.TB_PROD_SEGD_PLAN_SEGM segm
where 
segm.CD_PROD ='56'
and segm.CD_SUBP='1775';

select * from jl.TB_PRODUTO_ARVORE;

-- select de canal por produto
select * from TB_CNAL_PROD_SEGD  cnal
where 
cnal.CD_PROD ='57'
and cnal.CD_SUBP='1062';