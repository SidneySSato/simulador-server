package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.PerguntaObg;
import br.com.jumplabel.simulador.domain.Prod;
import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.domain.ProdutoArvore;
import br.com.jumplabel.simulador.service.dto.PerguntaObgDTO;
import br.com.jumplabel.simulador.service.dto.ProdutoArvoreDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-23T15:42:40-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class ProdutoArvoreLazyMapperImpl implements ProdutoArvoreLazyMapper {

    @Autowired
    private PerguntaObgMapper perguntaObgMapper;

    @Override
    public ProdutoArvoreDTO produtoArvoreToProdutoArvoreDTO(ProdutoArvore produtoArvore) {
        if ( produtoArvore == null ) {
            return null;
        }

        ProdutoArvoreDTO produtoArvoreDTO = new ProdutoArvoreDTO();

        produtoArvoreDTO.setCdProcSusep( produtoArvoreProdCdProcSusep( produtoArvore ) );
        produtoArvoreDTO.setDsProdSubpVc( produtoArvoreProdDsProdSubpVc( produtoArvore ) );
        produtoArvoreDTO.setSubpId( produtoArvorePkSubpId( produtoArvore ) );
        produtoArvoreDTO.setCntdDomiDsCntdDomiLgdo( produtoArvoreProdCntdDomiDsCntdDomiLgdo( produtoArvore ) );
        produtoArvoreDTO.setProdId( produtoArvorePkProdId( produtoArvore ) );
        produtoArvoreDTO.setDsProdCorp( produtoArvoreProdDsProdCorp( produtoArvore ) );
        Long prod = produtoArvoreProdCntdDomiId( produtoArvore );
        if ( prod != null ) {
            produtoArvoreDTO.setCntdDomiCdCntdDomiLgdo( String.valueOf( prod ) );
        }
        produtoArvoreDTO.setCdSituProdArvore( produtoArvore.getCdSituProdArvore() );
        produtoArvoreDTO.setInProdutoSemOferta( produtoArvore.getInProdutoSemOferta() );
        Set<PerguntaObgDTO> set = perguntaObgSetToPerguntaObgDTOSet( produtoArvore.getPerguntaObgs() );
        if ( set != null ) {
            produtoArvoreDTO.setPerguntaObgs( set );
        }

        return produtoArvoreDTO;
    }

    @Override
    public List<ProdutoArvoreDTO> produtoArvoresToProdutoArvoreDTOs(List<ProdutoArvore> produtoArvores) {
        if ( produtoArvores == null ) {
            return null;
        }

        List<ProdutoArvoreDTO> list = new ArrayList<ProdutoArvoreDTO>();
        for ( ProdutoArvore produtoArvore : produtoArvores ) {
            list.add( produtoArvoreToProdutoArvoreDTO( produtoArvore ) );
        }

        return list;
    }

    @Override
    public ProdutoArvore produtoArvoreDTOToProdutoArvore(ProdutoArvoreDTO produtoArvoreDTO) {
        if ( produtoArvoreDTO == null ) {
            return null;
        }

        ProdutoArvore produtoArvore = new ProdutoArvore();

        produtoArvore.setCdSituProdArvore( produtoArvoreDTO.getCdSituProdArvore() );
        produtoArvore.setInProdutoSemOferta( produtoArvoreDTO.getInProdutoSemOferta() );

        return produtoArvore;
    }

    @Override
    public List<ProdutoArvore> produtoArvoreDTOsToProdutoArvores(List<ProdutoArvoreDTO> produtoArvoreDTOs) {
        if ( produtoArvoreDTOs == null ) {
            return null;
        }

        List<ProdutoArvore> list = new ArrayList<ProdutoArvore>();
        for ( ProdutoArvoreDTO produtoArvoreDTO : produtoArvoreDTOs ) {
            list.add( produtoArvoreDTOToProdutoArvore( produtoArvoreDTO ) );
        }

        return list;
    }

    private String produtoArvoreProdCdProcSusep(ProdutoArvore produtoArvore) {

        if ( produtoArvore == null ) {
            return null;
        }
        Prod prod = produtoArvore.getProd();
        if ( prod == null ) {
            return null;
        }
        String cdProcSusep = prod.getCdProcSusep();
        if ( cdProcSusep == null ) {
            return null;
        }
        return cdProcSusep;
    }

    private String produtoArvoreProdDsProdSubpVc(ProdutoArvore produtoArvore) {

        if ( produtoArvore == null ) {
            return null;
        }
        Prod prod = produtoArvore.getProd();
        if ( prod == null ) {
            return null;
        }
        String dsProdSubpVc = prod.getDsProdSubpVc();
        if ( dsProdSubpVc == null ) {
            return null;
        }
        return dsProdSubpVc;
    }

    private String produtoArvorePkSubpId(ProdutoArvore produtoArvore) {

        if ( produtoArvore == null ) {
            return null;
        }
        ProdId pk = produtoArvore.getPk();
        if ( pk == null ) {
            return null;
        }
        String subpId = pk.getSubpId();
        if ( subpId == null ) {
            return null;
        }
        return subpId;
    }

    private String produtoArvoreProdCntdDomiDsCntdDomiLgdo(ProdutoArvore produtoArvore) {

        if ( produtoArvore == null ) {
            return null;
        }
        Prod prod = produtoArvore.getProd();
        if ( prod == null ) {
            return null;
        }
        CntdDomi cntdDomi = prod.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String dsCntdDomiLgdo = cntdDomi.getDsCntdDomiLgdo();
        if ( dsCntdDomiLgdo == null ) {
            return null;
        }
        return dsCntdDomiLgdo;
    }

    private String produtoArvorePkProdId(ProdutoArvore produtoArvore) {

        if ( produtoArvore == null ) {
            return null;
        }
        ProdId pk = produtoArvore.getPk();
        if ( pk == null ) {
            return null;
        }
        String prodId = pk.getProdId();
        if ( prodId == null ) {
            return null;
        }
        return prodId;
    }

    private String produtoArvoreProdDsProdCorp(ProdutoArvore produtoArvore) {

        if ( produtoArvore == null ) {
            return null;
        }
        Prod prod = produtoArvore.getProd();
        if ( prod == null ) {
            return null;
        }
        String dsProdCorp = prod.getDsProdCorp();
        if ( dsProdCorp == null ) {
            return null;
        }
        return dsProdCorp;
    }

    private Long produtoArvoreProdCntdDomiId(ProdutoArvore produtoArvore) {

        if ( produtoArvore == null ) {
            return null;
        }
        Prod prod = produtoArvore.getProd();
        if ( prod == null ) {
            return null;
        }
        CntdDomi cntdDomi = prod.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        Long id = cntdDomi.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<PerguntaObgDTO> perguntaObgSetToPerguntaObgDTOSet(Set<PerguntaObg> set) {
        if ( set == null ) {
            return null;
        }

        Set<PerguntaObgDTO> set_ = new HashSet<PerguntaObgDTO>();
        for ( PerguntaObg perguntaObg : set ) {
            set_.add( perguntaObgMapper.perguntaObgToPerguntaObgDTO( perguntaObg ) );
        }

        return set_;
    }
}
