package com.wcc.hanems.web.rest;

import com.wcc.hanems.repository.EqReturnViewRepository;
import com.wcc.hanems.service.EqReturnViewQueryService;
import com.wcc.hanems.service.EqReturnViewService;
import com.wcc.hanems.service.criteria.EqReturnViewCriteria;
import com.wcc.hanems.service.dto.EqReturnViewDTO;
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
 * REST controller for managing {@link com.wcc.hanems.domain.EqReturnView}.
 */
@RestController
@RequestMapping("/api")
public class EqReturnViewResource {

    private final Logger log = LoggerFactory.getLogger(EqReturnViewResource.class);

    private final EqReturnViewService eqReturnViewService;

    private final EqReturnViewRepository eqReturnViewRepository;

    private final EqReturnViewQueryService eqReturnViewQueryService;

    public EqReturnViewResource(
        EqReturnViewService eqReturnViewService,
        EqReturnViewRepository eqReturnViewRepository,
        EqReturnViewQueryService eqReturnViewQueryService
    ) {
        this.eqReturnViewService = eqReturnViewService;
        this.eqReturnViewRepository = eqReturnViewRepository;
        this.eqReturnViewQueryService = eqReturnViewQueryService;
    }

    /**
     * {@code GET  /eq-return-views} : get all the eqReturnViews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eqReturnViews in body.
     */
    @GetMapping("/eq-return-views")
    public ResponseEntity<List<EqReturnViewDTO>> getAllEqReturnViews(
        EqReturnViewCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get EqReturnViews by criteria: {}", criteria);
        Page<EqReturnViewDTO> page = eqReturnViewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /eq-return-views/count} : count all the eqReturnViews.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/eq-return-views/count")
    public ResponseEntity<Long> countEqReturnViews(EqReturnViewCriteria criteria) {
        log.debug("REST request to count EqReturnViews by criteria: {}", criteria);
        return ResponseEntity.ok().body(eqReturnViewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /eq-return-views/:id} : get the "id" eqReturnView.
     *
     * @param id the id of the eqReturnViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eqReturnViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eq-return-views/{id}")
    public ResponseEntity<EqReturnViewDTO> getEqReturnView(@PathVariable Long id) {
        log.debug("REST request to get EqReturnView : {}", id);
        Optional<EqReturnViewDTO> eqReturnViewDTO = eqReturnViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eqReturnViewDTO);
    }
}
