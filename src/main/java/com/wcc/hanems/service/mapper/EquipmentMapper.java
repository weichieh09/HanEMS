package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.Equipment;
import com.wcc.hanems.domain.Item;
import com.wcc.hanems.domain.Status;
import com.wcc.hanems.service.dto.EquipmentDTO;
import com.wcc.hanems.service.dto.ItemDTO;
import com.wcc.hanems.service.dto.StatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Equipment} and its DTO {@link EquipmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface EquipmentMapper extends EntityMapper<EquipmentDTO, Equipment> {
    @Mapping(target = "item", source = "item", qualifiedByName = "itemId")
    @Mapping(target = "status", source = "status", qualifiedByName = "statusId")
    EquipmentDTO toDto(Equipment s);

    @Named("itemId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ItemDTO toDtoItemId(Item item);

    @Named("statusId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    StatusDTO toDtoStatusId(Status status);
}
