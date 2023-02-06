package com.wcc.hanems.repository;

import com.wcc.hanems.domain.EqItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EqItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EqItemRepository extends JpaRepository<EqItem, Long>, JpaSpecificationExecutor<EqItem> {}
