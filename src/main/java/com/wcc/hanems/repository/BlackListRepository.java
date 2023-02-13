package com.wcc.hanems.repository;

import com.wcc.hanems.domain.BlackList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BlackList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long>, JpaSpecificationExecutor<BlackList> {}
