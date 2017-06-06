package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.Tag;
import br.com.jumplabel.simulador.service.dto.TagDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-16T11:45:00-0300",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDTO tagToTagDTO(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDTO tagDTO = new TagDTO();

        tagDTO.setId( tag.getId() );
        tagDTO.setDsTag( tag.getDsTag() );

        return tagDTO;
    }

    @Override
    public List<TagDTO> tagsToTagDTOs(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagDTO> list = new ArrayList<TagDTO>();
        for ( Tag tag : tags ) {
            list.add( tagToTagDTO( tag ) );
        }

        return list;
    }

    @Override
    public Tag tagDTOToTag(TagDTO tagDTO) {
        if ( tagDTO == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDTO.getId() );
        tag.setDsTag( tagDTO.getDsTag() );

        return tag;
    }

    @Override
    public List<Tag> tagDTOsToTags(List<TagDTO> tagDTOs) {
        if ( tagDTOs == null ) {
            return null;
        }

        List<Tag> list = new ArrayList<Tag>();
        for ( TagDTO tagDTO : tagDTOs ) {
            list.add( tagDTOToTag( tagDTO ) );
        }

        return list;
    }
}
