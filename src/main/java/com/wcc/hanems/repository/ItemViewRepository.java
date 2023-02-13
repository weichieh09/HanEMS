package com.wcc.hanems.repository;

import com.wcc.hanems.domain.ItemView;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemViewRepository extends JpaRepository<ItemView, Long>, JpaSpecificationExecutor<ItemView> {}
