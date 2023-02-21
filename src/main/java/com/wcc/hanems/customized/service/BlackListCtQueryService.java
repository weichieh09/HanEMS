package com.wcc.hanems.customized.service;

import com.wcc.hanems.customized.dto.BlackListCtCriteria;
import com.wcc.hanems.domain.BlackList;
import com.wcc.hanems.domain.BlackList_;
import com.wcc.hanems.domain.Person_;
import com.wcc.hanems.repository.BlackListRepository;
import com.wcc.hanems.service.criteria.BlackListCriteria;
import com.wcc.hanems.service.dto.BlackListDTO;
import com.wcc.hanems.service.mapper.BlackListMapper;
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
 * Service for executing complex queries for {@link BlackList} entities in the database.
 * The main input is a {@link BlackListCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BlackListDTO} or a {@link Page} of {@link BlackListDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BlackListCtQueryService extends QueryService<BlackList> {

    private final Logger log = LoggerFactory.getLogger(BlackListCtQueryService.class);

    private final BlackListRepository blackListRepository;

    private final BlackListMapper blackListMapper;

    public BlackListCtQueryService(BlackListRepository blackListRepository, BlackListMapper blackListMapper) {
        this.blackListRepository = blackListRepository;
        this.blackListMapper = blackListMapper;
    }

    /**
     * Return a {@link Page} of {@link BlackListDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BlackListDTO> findByCriteria(BlackListCtCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BlackList> specification = createSpecification(criteria);
        return blackListRepository.findAll(specification, page).map(blackListMapper::toDto);
    }

    /**
     * Function to convert {@link BlackListCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BlackList> createSpecification(BlackListCtCriteria criteria) {
        Specification<BlackList> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BlackList_.id));
            }
            if (criteria.getBlackDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBlackDate(), BlackList_.blackDate));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), BlackList_.createDate));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), BlackList_.modifyDate));
            }
            if (criteria.getPersonId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getPersonId(), root -> root.join(BlackList_.person, JoinType.LEFT).get(Person_.id))
                    );
            }
            if (criteria.getPersonIdno() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getPersonIdno(), root -> root.join(BlackList_.person, JoinType.LEFT).get(Person_.idno))
                    );
            }
            if (criteria.getPersonName() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getPersonName(), root -> root.join(BlackList_.person, JoinType.LEFT).get(Person_.name))
                    );
            }
        }
        return specification;
    }
}
