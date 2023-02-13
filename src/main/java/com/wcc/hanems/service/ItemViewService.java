package com.wcc.hanems.service;

import com.wcc.hanems.domain.ItemView;
import com.wcc.hanems.repository.ItemViewRepository;
import com.wcc.hanems.service.dto.ItemViewDTO;
import com.wcc.hanems.service.mapper.ItemViewMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemView}.
 */
@Service
@Transactional
public class ItemViewService {

    private final Logger log = LoggerFactory.getLogger(ItemViewService.class);

    private final ItemViewRepository itemViewRepository;

    private final ItemViewMapper itemViewMapper;

    public ItemViewService(ItemViewRepository itemViewRepository, ItemViewMapper itemViewMapper) {
        this.itemViewRepository = itemViewRepository;
        this.itemViewMapper = itemViewMapper;
    }

    /**
     * Save a itemView.
     *
     * @param itemViewDTO the entity to save.
     * @return the persisted entity.
     */
    public ItemViewDTO save(ItemViewDTO itemViewDTO) {
        log.debug("Request to save ItemView : {}", itemViewDTO);
        ItemView itemView = itemViewMapper.toEntity(itemViewDTO);
        itemView = itemViewRepository.save(itemView);
        return itemViewMapper.toDto(itemView);
    }

    /**
     * Update a itemView.
     *
     * @param itemViewDTO the entity to save.
     * @return the persisted entity.
     */
    public ItemViewDTO update(ItemViewDTO itemViewDTO) {
        log.debug("Request to update ItemView : {}", itemViewDTO);
        ItemView itemView = itemViewMapper.toEntity(itemViewDTO);
        itemView = itemViewRepository.save(itemView);
        return itemViewMapper.toDto(itemView);
    }

    /**
     * Partially update a itemView.
     *
     * @param itemViewDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ItemViewDTO> partialUpdate(ItemViewDTO itemViewDTO) {
        log.debug("Request to partially update ItemView : {}", itemViewDTO);

        return itemViewRepository
            .findById(itemViewDTO.getId())
            .map(existingItemView -> {
                itemViewMapper.partialUpdate(existingItemView, itemViewDTO);

                return existingItemView;
            })
            .map(itemViewRepository::save)
            .map(itemViewMapper::toDto);
    }

    /**
     * Get all the itemViews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ItemViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemViews");
        return itemViewRepository.findAll(pageable).map(itemViewMapper::toDto);
    }

    /**
     * Get one itemView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ItemViewDTO> findOne(Long id) {
        log.debug("Request to get ItemView : {}", id);
        return itemViewRepository.findById(id).map(itemViewMapper::toDto);
    }

    /**
     * Delete the itemView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ItemView : {}", id);
        itemViewRepository.deleteById(id);
    }
}
