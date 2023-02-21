package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.BlackList;
import com.wcc.hanems.domain.Person;
import com.wcc.hanems.service.dto.BlackListDTO;
import com.wcc.hanems.service.dto.PersonDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BlackList} and its DTO {@link BlackListDTO}.
 */
@Mapper(componentModel = "spring")
public interface BlackListMapper extends EntityMapper<BlackListDTO, BlackList> {
    @Mapping(target = "person", source = "person", qualifiedByName = "personId")
    BlackListDTO toDto(BlackList s);

    @Named("personId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "idno", source = "idno")
    @Mapping(target = "name", source = "name")
    PersonDTO toDtoPersonId(Person person);
}
