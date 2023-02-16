package com.wcc.hanems.repository;

import com.wcc.hanems.domain.EqReturnView;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EqReturnView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EqReturnViewRepository extends JpaRepository<EqReturnView, Long>, JpaSpecificationExecutor<EqReturnView> {}
