package com.wcc.hanems.service;

import com.wcc.hanems.domain.*; // for static metamodels
import com.wcc.hanems.domain.EqItem;
import com.wcc.hanems.repository.EqItemRepository;
import com.wcc.hanems.service.criteria.EqItemCriteria;
import com.wcc.hanems.service.dto.EqItemDTO;
import com.wcc.hanems.service.mapper.EqItemMapper;
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
 * Service for executing complex queries for {@link EqItem} entities in the database.
 * The main input is a {@link EqItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EqItemDTO} or a {@link Page} of {@link EqItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EqItemQueryService extends QueryService<EqItem> {

    private final Logger log = LoggerFactory.getLogger(EqItemQueryService.class);

    private final EqItemRepository eqItemRepository;

    private final EqItemMapper eqItemMapper;

    public EqItemQueryService(EqItemRepository eqItemRepository, EqItemMapper eqItemMapper) {
        this.eqItemRepository = eqItemRepository;
        this.eqItemMapper = eqItemMapper;
    }

    /**
     * Return a {@link List} of {@link EqItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EqItemDTO> findByCriteria(EqItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EqItem> specification = createSpecification(criteria);
        return eqItemMapper.toDto(eqItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EqItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EqItemDTO> findByCriteria(EqItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EqItem> specification = createSpecification(criteria);
        return eqItemRepository.findAll(specification, page).map(eqItemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EqItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EqItem> specification = createSpecification(criteria);
        return eqItemRepository.count(specification);
    }

    /**
     * Function to convert {@link EqItemCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EqItem> createSpecification(EqItemCriteria criteria) {
        Specification<EqItem> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EqItem_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), EqItem_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EqItem_.description));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EqItem_.createDate));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EqItem_.modifyDate));
            }
        }
        return specification;
    }
}
