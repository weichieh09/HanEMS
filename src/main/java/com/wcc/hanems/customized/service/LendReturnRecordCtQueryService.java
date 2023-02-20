package com.wcc.hanems.customized.service;

import com.wcc.hanems.customized.dto.LendReturnRecordCtCriteria;
import com.wcc.hanems.domain.*;
import com.wcc.hanems.repository.LendReturnRecordRepository;
import com.wcc.hanems.service.criteria.LendReturnRecordCriteria;
import com.wcc.hanems.service.dto.LendReturnRecordDTO;
import com.wcc.hanems.service.mapper.LendReturnRecordMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link LendReturnRecord} entities in the database.
 * The main input is a {@link LendReturnRecordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LendReturnRecordDTO} or a {@link Page} of {@link LendReturnRecordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LendReturnRecordCtQueryService extends QueryService<LendReturnRecord> {

    private final Logger log = LoggerFactory.getLogger(LendReturnRecordCtQueryService.class);

    private final LendReturnRecordRepository lendReturnRecordRepository;

    private final LendReturnRecordMapper lendReturnRecordMapper;

    public LendReturnRecordCtQueryService(
        LendReturnRecordRepository lendReturnRecordRepository,
        LendReturnRecordMapper lendReturnRecordMapper
    ) {
        this.lendReturnRecordRepository = lendReturnRecordRepository;
        this.lendReturnRecordMapper = lendReturnRecordMapper;
    }

    /**
     * Return a {@link Page} of {@link LendReturnRecordDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LendReturnRecordDTO> findByCriteria(LendReturnRecordCtCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LendReturnRecord> specification = createSpecification(criteria);
        return lendReturnRecordRepository.findAll(specification, page).map(lendReturnRecordMapper::toDto);
    }

    /**
     * Function to convert {@link LendReturnRecordCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LendReturnRecord> createSpecification(LendReturnRecordCtCriteria criteria) {
        Specification<LendReturnRecord> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), LendReturnRecord_.id));
            }
            if (criteria.getLendDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLendDate(), LendReturnRecord_.lendDate));
            }
            if (criteria.getReturnDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReturnDate(), LendReturnRecord_.returnDate));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LendReturnRecord_.createDate));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LendReturnRecord_.modifyDate));
            }
            if (criteria.getEquipmentId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEquipmentId(),
                            root -> root.join(LendReturnRecord_.equipment, JoinType.LEFT).get(Equipment_.id)
                        )
                    );
            }
            if (criteria.getEquipmentName() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEquipmentName(),
                            root -> root.join(LendReturnRecord_.equipment, JoinType.LEFT).get(Equipment_.name)
                        )
                    );
            }
            if (criteria.getPersonId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPersonId(),
                            root -> root.join(LendReturnRecord_.person, JoinType.LEFT).get(Person_.id)
                        )
                    );
            }
            if (criteria.getPersonIdno() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPersonIdno(),
                            root -> root.join(LendReturnRecord_.person, JoinType.LEFT).get(Person_.idno)
                        )
                    );
            }
            if (criteria.getPersonName() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPersonName(),
                            root -> root.join(LendReturnRecord_.person, JoinType.LEFT).get(Person_.name)
                        )
                    );
            }
            if (criteria.getReasonId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getReasonId(),
                            root -> root.join(LendReturnRecord_.reason, JoinType.LEFT).get(Reason_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
