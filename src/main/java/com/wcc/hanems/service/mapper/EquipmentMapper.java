package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.Equipment;
import com.wcc.hanems.service.dto.EquipmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Equipment} and its DTO {@link EquipmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface EquipmentMapper extends EntityMapper<EquipmentDTO, Equipment> {}
