package com.wcc.hanems.customized.controller;

import com.wcc.hanems.repository.ReasonRepository;
import com.wcc.hanems.service.ReasonQueryService;
import com.wcc.hanems.service.ReasonService;
import com.wcc.hanems.service.criteria.ReasonCriteria;
import com.wcc.hanems.service.dto.ReasonDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

/**
 * REST controller for managing {@link com.wcc.hanems.domain.Reason}.
 */
@RestController
@RequestMapping("/api/customized/")
public class ReasonController {

    private final Logger log = LoggerFactory.getLogger(ReasonController.class);

    private static final String ENTITY_NAME = "reason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReasonService reasonService;

    private final ReasonRepository reasonRepository;

    private final ReasonQueryService reasonQueryService;

    public ReasonController(ReasonService reasonService, ReasonRepository reasonRepository, ReasonQueryService reasonQueryService) {
        this.reasonService = reasonService;
        this.reasonRepository = reasonRepository;
        this.reasonQueryService = reasonQueryService;
    }

    /**
     * {@code GET  /reasons} : get all the reasons.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reasons in body.
     */
    @GetMapping("/reasons")
    public ResponseEntity<List<ReasonDTO>> getAllReasons(
        ReasonCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Reasons by criteria: {}", criteria);
        Page<ReasonDTO> page = reasonQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
