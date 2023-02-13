package com.wcc.hanems.repository;

import com.wcc.hanems.domain.LendReturnRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LendReturnRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LendReturnRecordRepository extends JpaRepository<LendReturnRecord, Long>, JpaSpecificationExecutor<LendReturnRecord> {}
