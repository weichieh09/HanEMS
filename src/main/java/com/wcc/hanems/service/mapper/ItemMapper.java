package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.Item;
import com.wcc.hanems.service.dto.ItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Item} and its DTO {@link ItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {}
