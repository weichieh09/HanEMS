package com.wcc.hanems.service;

import com.wcc.hanems.domain.*; // for static metamodels
import com.wcc.hanems.domain.Status;
import com.wcc.hanems.repository.StatusRepository;
import com.wcc.hanems.service.criteria.StatusCriteria;
import com.wcc.hanems.service.dto.StatusDTO;
import com.wcc.hanems.service.mapper.StatusMapper;
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
 * Service for executing complex queries for {@link Status} entities in the database.
 * The main input is a {@link StatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link StatusDTO} or a {@link Page} of {@link StatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class StatusQueryService extends QueryService<Status> {

    private final Logger log = LoggerFactory.getLogger(StatusQueryService.class);

    private final StatusRepository statusRepository;

    private final StatusMapper statusMapper;

    public StatusQueryService(StatusRepository statusRepository, StatusMapper statusMapper) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
    }

    /**
     * Return a {@link List} of {@link StatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StatusDTO> findByCriteria(StatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Status> specification = createSpecification(criteria);
        return statusMapper.toDto(statusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link StatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StatusDTO> findByCriteria(StatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Status> specification = createSpecification(criteria);
        return statusRepository.findAll(specification, page).map(statusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Status> specification = createSpecification(criteria);
        return statusRepository.count(specification);
    }

    /**
     * Function to convert {@link StatusCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Status> createSpecification(StatusCriteria criteria) {
        Specification<Status> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Status_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Status_.name));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Status_.createDate));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Status_.modifyDate));
            }
        }
        return specification;
    }
}
