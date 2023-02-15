package com.wcc.hanems.repository;

import com.wcc.hanems.domain.LendReturnRecord;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LendReturnRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LendReturnRecordRepository extends JpaRepository<LendReturnRecord, Long>, JpaSpecificationExecutor<LendReturnRecord> {
    @Query(
        value = "SELECT *\n" +
        "FROM hanems.lend_return_record\n" +
        "where equipment_id = :eqId and person_id = :personId and return_date is null \n",
        nativeQuery = true
    )
    List<LendReturnRecord> findByEqIdAndPersonId(@Param("eqId") Long eqId, @Param("personId") Long personId);
}
