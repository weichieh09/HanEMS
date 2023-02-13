package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.Reason;
import com.wcc.hanems.service.dto.ReasonDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reason} and its DTO {@link ReasonDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReasonMapper extends EntityMapper<ReasonDTO, Reason> {}
