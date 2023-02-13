package com.wcc.hanems.service;

import com.wcc.hanems.domain.*; // for static metamodels
import com.wcc.hanems.domain.Reason;
import com.wcc.hanems.repository.ReasonRepository;
import com.wcc.hanems.service.criteria.ReasonCriteria;
import com.wcc.hanems.service.dto.ReasonDTO;
import com.wcc.hanems.service.mapper.ReasonMapper;
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
 * Service for executing complex queries for {@link Reason} entities in the database.
 * The main input is a {@link ReasonCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReasonDTO} or a {@link Page} of {@link ReasonDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReasonQueryService extends QueryService<Reason> {

    private final Logger log = LoggerFactory.getLogger(ReasonQueryService.class);

    private final ReasonRepository reasonRepository;

    private final ReasonMapper reasonMapper;

    public ReasonQueryService(ReasonRepository reasonRepository, ReasonMapper reasonMapper) {
        this.reasonRepository = reasonRepository;
        this.reasonMapper = reasonMapper;
    }

    /**
     * Return a {@link List} of {@link ReasonDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReasonDTO> findByCriteria(ReasonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Reason> specification = createSpecification(criteria);
        return reasonMapper.toDto(reasonRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReasonDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReasonDTO> findByCriteria(ReasonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Reason> specification = createSpecification(criteria);
        return reasonRepository.findAll(specification, page).map(reasonMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReasonCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Reason> specification = createSpecification(criteria);
        return reasonRepository.count(specification);
    }

    /**
     * Function to convert {@link ReasonCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Reason> createSpecification(ReasonCriteria criteria) {
        Specification<Reason> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Reason_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Reason_.name));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Reason_.createDate));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Reason_.modifyDate));
            }
        }
        return specification;
    }
}
