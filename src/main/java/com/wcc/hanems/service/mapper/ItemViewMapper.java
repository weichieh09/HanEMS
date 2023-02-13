package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.ItemView;
import com.wcc.hanems.service.dto.ItemViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemView} and its DTO {@link ItemViewDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemViewMapper extends EntityMapper<ItemViewDTO, ItemView> {}
