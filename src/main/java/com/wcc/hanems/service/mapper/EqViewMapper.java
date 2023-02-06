package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.EqView;
import com.wcc.hanems.service.dto.EqViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EqView} and its DTO {@link EqViewDTO}.
 */
@Mapper(componentModel = "spring")
public interface EqViewMapper extends EntityMapper<EqViewDTO, EqView> {}
