package com.wcc.hanems.service;

import com.wcc.hanems.domain.*; // for static metamodels
import com.wcc.hanems.domain.ItemView;
import com.wcc.hanems.repository.ItemViewRepository;
import com.wcc.hanems.service.criteria.ItemViewCriteria;
import com.wcc.hanems.service.dto.ItemViewDTO;
import com.wcc.hanems.service.mapper.ItemViewMapper;
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
 * Service for executing complex queries for {@link ItemView} entities in the database.
 * The main input is a {@link ItemViewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ItemViewDTO} or a {@link Page} of {@link ItemViewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ItemViewQueryService extends QueryService<ItemView> {

    private final Logger log = LoggerFactory.getLogger(ItemViewQueryService.class);

    private final ItemViewRepository itemViewRepository;

    private final ItemViewMapper itemViewMapper;

    public ItemViewQueryService(ItemViewRepository itemViewRepository, ItemViewMapper itemViewMapper) {
        this.itemViewRepository = itemViewRepository;
        this.itemViewMapper = itemViewMapper;
    }

    /**
     * Return a {@link List} of {@link ItemViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ItemViewDTO> findByCriteria(ItemViewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ItemView> specification = createSpecification(criteria);
        return itemViewMapper.toDto(itemViewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ItemViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ItemViewDTO> findByCriteria(ItemViewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ItemView> specification = createSpecification(criteria);
        return itemViewRepository.findAll(specification, page).map(itemViewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ItemViewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ItemView> specification = createSpecification(criteria);
        return itemViewRepository.count(specification);
    }

    /**
     * Function to convert {@link ItemViewCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ItemView> createSpecification(ItemViewCriteria criteria) {
        Specification<ItemView> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ItemView_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ItemView_.name));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTotal(), ItemView_.total));
            }
            if (criteria.getReady() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReady(), ItemView_.ready));
            }
            if (criteria.getWorking() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWorking(), ItemView_.working));
            }
            if (criteria.getCanNot() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCanNot(), ItemView_.canNot));
            }
        }
        return specification;
    }
}
