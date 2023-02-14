package com.wcc.hanems.customized.controller;

import com.wcc.hanems.repository.ItemViewRepository;
import com.wcc.hanems.service.ItemViewQueryService;
import com.wcc.hanems.service.ItemViewService;
import com.wcc.hanems.service.criteria.ItemViewCriteria;
import com.wcc.hanems.service.dto.ItemViewDTO;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.wcc.hanems.domain.ItemView}.
 */
@RestController
@RequestMapping("/api/customized/")
public class ItemViewCtController {

    private final Logger log = LoggerFactory.getLogger(ItemViewCtController.class);

    private final ItemViewService itemViewService;

    private final ItemViewRepository itemViewRepository;

    private final ItemViewQueryService itemViewQueryService;

    public ItemViewCtController(
        ItemViewService itemViewService,
        ItemViewRepository itemViewRepository,
        ItemViewQueryService itemViewQueryService
    ) {
        this.itemViewService = itemViewService;
        this.itemViewRepository = itemViewRepository;
        this.itemViewQueryService = itemViewQueryService;
    }

    /**
     * {@code GET  /item-views} : get all the itemViews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemViews in body.
     */
    @GetMapping("/item-views")
    public ResponseEntity<List<ItemViewDTO>> getAllItemViews(
        ItemViewCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ItemViews by criteria: {}", criteria);
        Page<ItemViewDTO> page = itemViewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-views/count} : count all the itemViews.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/item-views/count")
    public ResponseEntity<Long> countItemViews(ItemViewCriteria criteria) {
        log.debug("REST request to count ItemViews by criteria: {}", criteria);
        return ResponseEntity.ok().body(itemViewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /item-views/:id} : get the "id" itemView.
     *
     * @param id the id of the itemViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-views/{id}")
    public ResponseEntity<ItemViewDTO> getItemView(@PathVariable Long id) {
        log.debug("REST request to get ItemView : {}", id);
        Optional<ItemViewDTO> itemViewDTO = itemViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemViewDTO);
    }
}
