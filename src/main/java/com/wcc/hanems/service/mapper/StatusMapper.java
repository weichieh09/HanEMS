package com.wcc.hanems.service.mapper;

import com.wcc.hanems.domain.Status;
import com.wcc.hanems.service.dto.StatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Status} and its DTO {@link StatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {}
