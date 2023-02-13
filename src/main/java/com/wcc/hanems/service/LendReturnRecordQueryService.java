package com.wcc.hanems.service;

import com.wcc.hanems.domain.*; // for static metamodels
import com.wcc.hanems.domain.LendReturnRecord;
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
public class LendReturnRecordQueryService extends QueryService<LendReturnRecord> {

    private final Logger log = LoggerFactory.getLogger(LendReturnRecordQueryService.class);

    private final LendReturnRecordRepository lendReturnRecordRepository;

    private final LendReturnRecordMapper lendReturnRecordMapper;

    public LendReturnRecordQueryService(
        LendReturnRecordRepository lendReturnRecordRepository,
        LendReturnRecordMapper lendReturnRecordMapper
    ) {
        this.lendReturnRecordRepository = lendReturnRecordRepository;
        this.lendReturnRecordMapper = lendReturnRecordMapper;
    }

    /**
     * Return a {@link List} of {@link LendReturnRecordDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LendReturnRecordDTO> findByCriteria(LendReturnRecordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LendReturnRecord> specification = createSpecification(criteria);
        return lendReturnRecordMapper.toDto(lendReturnRecordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LendReturnRecordDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LendReturnRecordDTO> findByCriteria(LendReturnRecordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LendReturnRecord> specification = createSpecification(criteria);
        return lendReturnRecordRepository.findAll(specification, page).map(lendReturnRecordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LendReturnRecordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LendReturnRecord> specification = createSpecification(criteria);
        return lendReturnRecordRepository.count(specification);
    }

    /**
     * Function to convert {@link LendReturnRecordCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LendReturnRecord> createSpecification(LendReturnRecordCriteria criteria) {
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
            if (criteria.getPersonId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPersonId(),
                            root -> root.join(LendReturnRecord_.person, JoinType.LEFT).get(Person_.id)
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
