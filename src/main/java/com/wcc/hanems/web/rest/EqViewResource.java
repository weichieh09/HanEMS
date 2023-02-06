package com.wcc.hanems.web.rest;

import com.wcc.hanems.repository.EqViewRepository;
import com.wcc.hanems.service.EqViewQueryService;
import com.wcc.hanems.service.EqViewService;
import com.wcc.hanems.service.criteria.EqViewCriteria;
import com.wcc.hanems.service.dto.EqViewDTO;
import com.wcc.hanems.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.wcc.hanems.domain.EqView}.
 */
@RestController
@RequestMapping("/api")
public class EqViewResource {

    private final Logger log = LoggerFactory.getLogger(EqViewResource.class);

    private final EqViewService eqViewService;

    private final EqViewRepository eqViewRepository;

    private final EqViewQueryService eqViewQueryService;

    public EqViewResource(EqViewService eqViewService, EqViewRepository eqViewRepository, EqViewQueryService eqViewQueryService) {
        this.eqViewService = eqViewService;
        this.eqViewRepository = eqViewRepository;
        this.eqViewQueryService = eqViewQueryService;
    }

    /**
     * {@code GET  /eq-views} : get all the eqViews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eqViews in body.
     */
    @GetMapping("/eq-views")
    public ResponseEntity<List<EqViewDTO>> getAllEqViews(
        EqViewCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get EqViews by criteria: {}", criteria);
        Page<EqViewDTO> page = eqViewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /eq-views/count} : count all the eqViews.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/eq-views/count")
    public ResponseEntity<Long> countEqViews(EqViewCriteria criteria) {
        log.debug("REST request to count EqViews by criteria: {}", criteria);
        return ResponseEntity.ok().body(eqViewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /eq-views/:id} : get the "id" eqView.
     *
     * @param id the id of the eqViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eqViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eq-views/{id}")
    public ResponseEntity<EqViewDTO> getEqView(@PathVariable Long id) {
        log.debug("REST request to get EqView : {}", id);
        Optional<EqViewDTO> eqViewDTO = eqViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eqViewDTO);
    }
}
