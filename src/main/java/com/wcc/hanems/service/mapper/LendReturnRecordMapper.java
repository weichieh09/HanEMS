package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.Equipment;
import com.wcc.hanems.domain.LendReturnRecord;
import com.wcc.hanems.domain.Person;
import com.wcc.hanems.domain.Reason;
import com.wcc.hanems.service.dto.EquipmentDTO;
import com.wcc.hanems.service.dto.LendReturnRecordDTO;
import com.wcc.hanems.service.dto.PersonDTO;
import com.wcc.hanems.service.dto.ReasonDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LendReturnRecord} and its DTO {@link LendReturnRecordDTO}.
 */
@Mapper(componentModel = "spring")
public interface LendReturnRecordMapper extends EntityMapper<LendReturnRecordDTO, LendReturnRecord> {
    @Mapping(target = "equipment", source = "equipment", qualifiedByName = "equipmentId")
    @Mapping(target = "person", source = "person", qualifiedByName = "personId")
    @Mapping(target = "reason", source = "reason", qualifiedByName = "reasonId")
    LendReturnRecordDTO toDto(LendReturnRecord s);

    @Named("equipmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EquipmentDTO toDtoEquipmentId(Equipment equipment);

    @Named("personId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PersonDTO toDtoPersonId(Person person);

    @Named("reasonId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReasonDTO toDtoReasonId(Reason reason);
}
