package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.EqReturnView;
import com.wcc.hanems.service.dto.EqReturnViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EqReturnView} and its DTO {@link EqReturnViewDTO}.
 */
@Mapper(componentModel = "spring")
public interface EqReturnViewMapper extends EntityMapper<EqReturnViewDTO, EqReturnView> {}
