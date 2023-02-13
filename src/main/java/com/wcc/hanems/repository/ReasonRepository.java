package com.wcc.hanems.repository;

import com.wcc.hanems.domain.Reason;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Reason entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReasonRepository extends JpaRepository<Reason, Long>, JpaSpecificationExecutor<Reason> {}
