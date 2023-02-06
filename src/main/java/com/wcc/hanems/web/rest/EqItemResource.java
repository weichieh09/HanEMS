package com.wcc.hanems.web.rest;

import com.wcc.hanems.repository.EqItemRepository;
import com.wcc.hanems.service.EqItemQueryService;
import com.wcc.hanems.service.EqItemService;
import com.wcc.hanems.service.criteria.EqItemCriteria;
import com.wcc.hanems.service.dto.EqItemDTO;
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
 * REST controller for managing {@link com.wcc.hanems.domain.EqItem}.
 */
@RestController
@RequestMapping("/api")
public class EqItemResource {

    private final Logger log = LoggerFactory.getLogger(EqItemResource.class);

    private static final String ENTITY_NAME = "eqItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EqItemService eqItemService;

    private final EqItemRepository eqItemRepository;

    private final EqItemQueryService eqItemQueryService;

    public EqItemResource(EqItemService eqItemService, EqItemRepository eqItemRepository, EqItemQueryService eqItemQueryService) {
        this.eqItemService = eqItemService;
        this.eqItemRepository = eqItemRepository;
        this.eqItemQueryService = eqItemQueryService;
    }

    /**
     * {@code POST  /eq-items} : Create a new eqItem.
     *
     * @param eqItemDTO the eqItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eqItemDTO, or with status {@code 400 (Bad Request)} if the eqItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eq-items")
    public ResponseEntity<EqItemDTO> createEqItem(@RequestBody EqItemDTO eqItemDTO) throws URISyntaxException {
        log.debug("REST request to save EqItem : {}", eqItemDTO);
        if (eqItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new eqItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EqItemDTO result = eqItemService.save(eqItemDTO);
        return ResponseEntity
            .created(new URI("/api/eq-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /eq-items/:id} : Updates an existing eqItem.
     *
     * @param id the id of the eqItemDTO to save.
     * @param eqItemDTO the eqItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eqItemDTO,
     * or with status {@code 400 (Bad Request)} if the eqItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eqItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eq-items/{id}")
    public ResponseEntity<EqItemDTO> updateEqItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EqItemDTO eqItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EqItem : {}, {}", id, eqItemDTO);
        if (eqItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, eqItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!eqItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EqItemDTO result = eqItemService.update(eqItemDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eqItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /eq-items/:id} : Partial updates given fields of an existing eqItem, field will ignore if it is null
     *
     * @param id the id of the eqItemDTO to save.
     * @param eqItemDTO the eqItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eqItemDTO,
     * or with status {@code 400 (Bad Request)} if the eqItemDTO is not valid,
     * or with status {@code 404 (Not Found)} if the eqItemDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the eqItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eq-items/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EqItemDTO> partialUpdateEqItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EqItemDTO eqItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EqItem partially : {}, {}", id, eqItemDTO);
        if (eqItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, eqItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!eqItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EqItemDTO> result = eqItemService.partialUpdate(eqItemDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eqItemDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /eq-items} : get all the eqItems.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eqItems in body.
     */
    @GetMapping("/eq-items")
    public ResponseEntity<List<EqItemDTO>> getAllEqItems(
        EqItemCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get EqItems by criteria: {}", criteria);
        Page<EqItemDTO> page = eqItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /eq-items/count} : count all the eqItems.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/eq-items/count")
    public ResponseEntity<Long> countEqItems(EqItemCriteria criteria) {
        log.debug("REST request to count EqItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(eqItemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /eq-items/:id} : get the "id" eqItem.
     *
     * @param id the id of the eqItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eqItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eq-items/{id}")
    public ResponseEntity<EqItemDTO> getEqItem(@PathVariable Long id) {
        log.debug("REST request to get EqItem : {}", id);
        Optional<EqItemDTO> eqItemDTO = eqItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eqItemDTO);
    }

    /**
     * {@code DELETE  /eq-items/:id} : delete the "id" eqItem.
     *
     * @param id the id of the eqItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eq-items/{id}")
    public ResponseEntity<Void> deleteEqItem(@PathVariable Long id) {
        log.debug("REST request to delete EqItem : {}", id);
        eqItemService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
