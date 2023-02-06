package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.EqItem;
import com.wcc.hanems.service.dto.EqItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EqItem} and its DTO {@link EqItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface EqItemMapper extends EntityMapper<EqItemDTO, EqItem> {}
