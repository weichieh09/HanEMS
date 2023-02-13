package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.Person;
import com.wcc.hanems.service.dto.PersonDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Person} and its DTO {@link PersonDTO}.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {}
