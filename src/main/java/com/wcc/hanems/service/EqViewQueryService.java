package com.wcc.hanems.service;

import com.wcc.hanems.domain.*; // for static metamodels
import com.wcc.hanems.domain.EqView;
import com.wcc.hanems.repository.EqViewRepository;
import com.wcc.hanems.service.criteria.EqViewCriteria;
import com.wcc.hanems.service.dto.EqViewDTO;
import com.wcc.hanems.service.mapper.EqViewMapper;
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
 * Service for executing complex queries for {@link EqView} entities in the database.
 * The main input is a {@link EqViewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EqViewDTO} or a {@link Page} of {@link EqViewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EqViewQueryService extends QueryService<EqView> {

    private final Logger log = LoggerFactory.getLogger(EqViewQueryService.class);

    private final EqViewRepository eqViewRepository;

    private final EqViewMapper eqViewMapper;

    public EqViewQueryService(EqViewRepository eqViewRepository, EqViewMapper eqViewMapper) {
        this.eqViewRepository = eqViewRepository;
        this.eqViewMapper = eqViewMapper;
    }

    /**
     * Return a {@link List} of {@link EqViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EqViewDTO> findByCriteria(EqViewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EqView> specification = createSpecification(criteria);
        return eqViewMapper.toDto(eqViewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EqViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EqViewDTO> findByCriteria(EqViewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EqView> specification = createSpecification(criteria);
        return eqViewRepository.findAll(specification, page).map(eqViewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EqViewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EqView> specification = createSpecification(criteria);
        return eqViewRepository.count(specification);
    }

    /**
     * Function to convert {@link EqViewCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EqView> createSpecification(EqViewCriteria criteria) {
        Specification<EqView> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EqView_.id));
            }
            if (criteria.getEqItemId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEqItemId(), EqView_.eqItemId));
            }
            if (criteria.getEqItemName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEqItemName(), EqView_.eqItemName));
            }
            if (criteria.getEqName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEqName(), EqView_.eqName));
            }
            if (criteria.getEqDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEqDescription(), EqView_.eqDescription));
            }
            if (criteria.getEqCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEqCreateDate(), EqView_.eqCreateDate));
            }
            if (criteria.getEqModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEqModifyDate(), EqView_.eqModifyDate));
            }
        }
        return specification;
    }
}
