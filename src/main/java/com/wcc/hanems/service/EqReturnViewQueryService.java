package com.wcc.hanems.service;

import com.wcc.hanems.domain.*; // for static metamodels
import com.wcc.hanems.domain.EqReturnView;
import com.wcc.hanems.repository.EqReturnViewRepository;
import com.wcc.hanems.service.criteria.EqReturnViewCriteria;
import com.wcc.hanems.service.dto.EqReturnViewDTO;
import com.wcc.hanems.service.mapper.EqReturnViewMapper;
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
 * Service for executing complex queries for {@link EqReturnView} entities in the database.
 * The main input is a {@link EqReturnViewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EqReturnViewDTO} or a {@link Page} of {@link EqReturnViewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EqReturnViewQueryService extends QueryService<EqReturnView> {

    private final Logger log = LoggerFactory.getLogger(EqReturnViewQueryService.class);

    private final EqReturnViewRepository eqReturnViewRepository;

    private final EqReturnViewMapper eqReturnViewMapper;

    public EqReturnViewQueryService(EqReturnViewRepository eqReturnViewRepository, EqReturnViewMapper eqReturnViewMapper) {
        this.eqReturnViewRepository = eqReturnViewRepository;
        this.eqReturnViewMapper = eqReturnViewMapper;
    }

    /**
     * Return a {@link List} of {@link EqReturnViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EqReturnViewDTO> findByCriteria(EqReturnViewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EqReturnView> specification = createSpecification(criteria);
        return eqReturnViewMapper.toDto(eqReturnViewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EqReturnViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EqReturnViewDTO> findByCriteria(EqReturnViewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EqReturnView> specification = createSpecification(criteria);
        return eqReturnViewRepository.findAll(specification, page).map(eqReturnViewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EqReturnViewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EqReturnView> specification = createSpecification(criteria);
        return eqReturnViewRepository.count(specification);
    }

    /**
     * Function to convert {@link EqReturnViewCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EqReturnView> createSpecification(EqReturnViewCriteria criteria) {
        Specification<EqReturnView> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EqReturnView_.id));
            }
            if (criteria.getIdno() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdno(), EqReturnView_.idno));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), EqReturnView_.name));
            }
            if (criteria.getItemId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getItemId(), EqReturnView_.itemId));
            }
            if (criteria.getItemName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getItemName(), EqReturnView_.itemName));
            }
            if (criteria.getStatusId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatusId(), EqReturnView_.statusId));
            }
            if (criteria.getStatusName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusName(), EqReturnView_.statusName));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPersonId(), EqReturnView_.personId));
            }
            if (criteria.getPersonName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonName(), EqReturnView_.personName));
            }
        }
        return specification;
    }
}
