package com.wcc.hanems.web.rest;

import com.wcc.hanems.repository.LendReturnRecordRepository;
import com.wcc.hanems.service.LendReturnRecordQueryService;
import com.wcc.hanems.service.LendReturnRecordService;
import com.wcc.hanems.service.criteria.LendReturnRecordCriteria;
import com.wcc.hanems.service.dto.LendReturnRecordDTO;
import com.wcc.hanems.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.wcc.hanems.domain.LendReturnRecord}.
 */
@RestController
@RequestMapping("/api")
public class LendReturnRecordResource {

    private final Logger log = LoggerFactory.getLogger(LendReturnRecordResource.class);

    private static final String ENTITY_NAME = "lendReturnRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LendReturnRecordService lendReturnRecordService;

    private final LendReturnRecordRepository lendReturnRecordRepository;

    private final LendReturnRecordQueryService lendReturnRecordQueryService;

    public LendReturnRecordResource(
        LendReturnRecordService lendReturnRecordService,
        LendReturnRecordRepository lendReturnRecordRepository,
        LendReturnRecordQueryService lendReturnRecordQueryService
    ) {
        this.lendReturnRecordService = lendReturnRecordService;
        this.lendReturnRecordRepository = lendReturnRecordRepository;
        this.lendReturnRecordQueryService = lendReturnRecordQueryService;
    }

    /**
     * {@code POST  /lend-return-records} : Create a new lendReturnRecord.
     *
     * @param lendReturnRecordDTO the lendReturnRecordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lendReturnRecordDTO, or with status {@code 400 (Bad Request)} if the lendReturnRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lend-return-records")
    public ResponseEntity<LendReturnRecordDTO> createLendReturnRecord(@Valid @RequestBody LendReturnRecordDTO lendReturnRecordDTO)
        throws URISyntaxException {
        log.debug("REST request to save LendReturnRecord : {}", lendReturnRecordDTO);
        if (lendReturnRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new lendReturnRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LendReturnRecordDTO result = lendReturnRecordService.save(lendReturnRecordDTO);
        return ResponseEntity
            .created(new URI("/api/lend-return-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lend-return-records/:id} : Updates an existing lendReturnRecord.
     *
     * @param id the id of the lendReturnRecordDTO to save.
     * @param lendReturnRecordDTO the lendReturnRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lendReturnRecordDTO,
     * or with status {@code 400 (Bad Request)} if the lendReturnRecordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lendReturnRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lend-return-records/{id}")
    public ResponseEntity<LendReturnRecordDTO> updateLendReturnRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LendReturnRecordDTO lendReturnRecordDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LendReturnRecord : {}, {}", id, lendReturnRecordDTO);
        if (lendReturnRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lendReturnRecordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lendReturnRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LendReturnRecordDTO result = lendReturnRecordService.update(lendReturnRecordDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lendReturnRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /lend-return-records/:id} : Partial updates given fields of an existing lendReturnRecord, field will ignore if it is null
     *
     * @param id the id of the lendReturnRecordDTO to save.
     * @param lendReturnRecordDTO the lendReturnRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lendReturnRecordDTO,
     * or with status {@code 400 (Bad Request)} if the lendReturnRecordDTO is not valid,
     * or with status {@code 404 (Not Found)} if the lendReturnRecordDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the lendReturnRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/lend-return-records/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LendReturnRecordDTO> partialUpdateLendReturnRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LendReturnRecordDTO lendReturnRecordDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LendReturnRecord partially : {}, {}", id, lendReturnRecordDTO);
        if (lendReturnRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lendReturnRecordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lendReturnRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LendReturnRecordDTO> result = lendReturnRecordService.partialUpdate(lendReturnRecordDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lendReturnRecordDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /lend-return-records} : get all the lendReturnRecords.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lendReturnRecords in body.
     */
    @GetMapping("/lend-return-records")
    public ResponseEntity<List<LendReturnRecordDTO>> getAllLendReturnRecords(
        LendReturnRecordCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get LendReturnRecords by criteria: {}", criteria);
        Page<LendReturnRecordDTO> page = lendReturnRecordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lend-return-records/count} : count all the lendReturnRecords.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/lend-return-records/count")
    public ResponseEntity<Long> countLendReturnRecords(LendReturnRecordCriteria criteria) {
        log.debug("REST request to count LendReturnRecords by criteria: {}", criteria);
        return ResponseEntity.ok().body(lendReturnRecordQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /lend-return-records/:id} : get the "id" lendReturnRecord.
     *
     * @param id the id of the lendReturnRecordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lendReturnRecordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lend-return-records/{id}")
    public ResponseEntity<LendReturnRecordDTO> getLendReturnRecord(@PathVariable Long id) {
        log.debug("REST request to get LendReturnRecord : {}", id);
        Optional<LendReturnRecordDTO> lendReturnRecordDTO = lendReturnRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lendReturnRecordDTO);
    }

    /**
     * {@code DELETE  /lend-return-records/:id} : delete the "id" lendReturnRecord.
     *
     * @param id the id of the lendReturnRecordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lend-return-records/{id}")
    public ResponseEntity<Void> deleteLendReturnRecord(@PathVariable Long id) {
        log.debug("REST request to delete LendReturnRecord : {}", id);
        lendReturnRecordService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
