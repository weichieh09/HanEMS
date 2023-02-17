package com.wcc.hanems.customized.controller;

import com.wcc.hanems.customized.service.BlackListCtService;
import com.wcc.hanems.repository.BlackListRepository;
import com.wcc.hanems.service.BlackListQueryService;
import com.wcc.hanems.service.BlackListService;
import com.wcc.hanems.service.criteria.BlackListCriteria;
import com.wcc.hanems.service.dto.BlackListDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing {@link com.wcc.hanems.domain.BlackList}.
 */
@RestController
@RequestMapping("/api")
public class BlackListCtController {

    private final Logger log = LoggerFactory.getLogger(BlackListCtController.class);

    private static final String ENTITY_NAME = "blackList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlackListService blackListService;

    private final BlackListRepository blackListRepository;

    private final BlackListQueryService blackListQueryService;

    @Autowired
    private BlackListCtService blackListCtService;

    public BlackListCtController(
        BlackListService blackListService,
        BlackListRepository blackListRepository,
        BlackListQueryService blackListQueryService
    ) {
        this.blackListService = blackListService;
        this.blackListRepository = blackListRepository;
        this.blackListQueryService = blackListQueryService;
    }

    /**
     * {@code POST  /black-lists} : Create a new blackList.
     *
     * @param blackListDTO the blackListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blackListDTO, or with status {@code 400 (Bad Request)} if the blackList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wcc106")
    public ResponseEntity<BlackListDTO> createBlackList(@Valid @RequestBody BlackListDTO blackListDTO) throws URISyntaxException {
        log.debug("REST request to save BlackList : {}", blackListDTO);
        if (blackListDTO.getId() != null) {
            throw new BadRequestAlertException("A new blackList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlackListDTO result = blackListService.save(blackListDTO);
        blackListCtService.isNeedLock(blackListDTO.getPerson().getId());
        return ResponseEntity
            .created(new URI("/api/black-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /black-lists/:id} : Updates an existing blackList.
     *
     * @param id the id of the blackListDTO to save.
     * @param blackListDTO the blackListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blackListDTO,
     * or with status {@code 400 (Bad Request)} if the blackListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blackListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wcc106/{id}")
    public ResponseEntity<BlackListDTO> updateBlackList(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BlackListDTO blackListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BlackList : {}, {}", id, blackListDTO);
        if (blackListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, blackListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!blackListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BlackListDTO result = blackListService.update(blackListDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blackListDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /black-lists/:id} : Partial updates given fields of an existing blackList, field will ignore if it is null
     *
     * @param id the id of the blackListDTO to save.
     * @param blackListDTO the blackListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blackListDTO,
     * or with status {@code 400 (Bad Request)} if the blackListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the blackListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the blackListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/wcc106/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BlackListDTO> partialUpdateBlackList(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BlackListDTO blackListDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BlackList partially : {}, {}", id, blackListDTO);
        if (blackListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, blackListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!blackListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BlackListDTO> result = blackListService.partialUpdate(blackListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blackListDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /black-lists} : get all the blackLists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blackLists in body.
     */
    @GetMapping("/wcc106")
    public ResponseEntity<List<BlackListDTO>> getAllBlackLists(
        BlackListCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get BlackLists by criteria: {}", criteria);
        Page<BlackListDTO> page = blackListQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /black-lists/count} : count all the blackLists.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/wcc106/count")
    public ResponseEntity<Long> countBlackLists(BlackListCriteria criteria) {
        log.debug("REST request to count BlackLists by criteria: {}", criteria);
        return ResponseEntity.ok().body(blackListQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /black-lists/:id} : get the "id" blackList.
     *
     * @param id the id of the blackListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blackListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wcc106/{id}")
    public ResponseEntity<BlackListDTO> getBlackList(@PathVariable Long id) {
        log.debug("REST request to get BlackList : {}", id);
        Optional<BlackListDTO> blackListDTO = blackListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blackListDTO);
    }

    /**
     * {@code DELETE  /black-lists/:id} : delete the "id" blackList.
     *
     * @param id the id of the blackListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wcc106/{id}")
    public ResponseEntity<Void> deleteBlackList(@PathVariable Long id) {
        log.debug("REST request to delete BlackList : {}", id);
        blackListService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
