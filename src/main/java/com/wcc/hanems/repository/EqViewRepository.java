package com.wcc.hanems.repository;

import com.wcc.hanems.domain.EqView;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EqView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EqViewRepository extends JpaRepository<EqView, Long>, JpaSpecificationExecutor<EqView> {}
