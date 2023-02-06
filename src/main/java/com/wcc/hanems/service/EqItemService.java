package com.wcc.hanems.service;

import com.wcc.hanems.domain.EqItem;
import com.wcc.hanems.repository.EqItemRepository;
import com.wcc.hanems.service.dto.EqItemDTO;
import com.wcc.hanems.service.mapper.EqItemMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EqItem}.
 */
@Service
@Transactional
public class EqItemService {

    private final Logger log = LoggerFactory.getLogger(EqItemService.class);

    private final EqItemRepository eqItemRepository;

    private final EqItemMapper eqItemMapper;

    public EqItemService(EqItemRepository eqItemRepository, EqItemMapper eqItemMapper) {
        this.eqItemRepository = eqItemRepository;
        this.eqItemMapper = eqItemMapper;
    }

    /**
     * Save a eqItem.
     *
     * @param eqItemDTO the entity to save.
     * @return the persisted entity.
     */
    public EqItemDTO save(EqItemDTO eqItemDTO) {
        log.debug("Request to save EqItem : {}", eqItemDTO);
        EqItem eqItem = eqItemMapper.toEntity(eqItemDTO);
        eqItem = eqItemRepository.save(eqItem);
        return eqItemMapper.toDto(eqItem);
    }

    /**
     * Update a eqItem.
     *
     * @param eqItemDTO the entity to save.
     * @return the persisted entity.
     */
    public EqItemDTO update(EqItemDTO eqItemDTO) {
        log.debug("Request to update EqItem : {}", eqItemDTO);
        EqItem eqItem = eqItemMapper.toEntity(eqItemDTO);
        eqItem = eqItemRepository.save(eqItem);
        return eqItemMapper.toDto(eqItem);
    }

    /**
     * Partially update a eqItem.
     *
     * @param eqItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EqItemDTO> partialUpdate(EqItemDTO eqItemDTO) {
        log.debug("Request to partially update EqItem : {}", eqItemDTO);

        return eqItemRepository
            .findById(eqItemDTO.getId())
            .map(existingEqItem -> {
                eqItemMapper.partialUpdate(existingEqItem, eqItemDTO);

                return existingEqItem;
            })
            .map(eqItemRepository::save)
            .map(eqItemMapper::toDto);
    }

    /**
     * Get all the eqItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EqItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EqItems");
        return eqItemRepository.findAll(pageable).map(eqItemMapper::toDto);
    }

    /**
     * Get one eqItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EqItemDTO> findOne(Long id) {
        log.debug("Request to get EqItem : {}", id);
        return eqItemRepository.findById(id).map(eqItemMapper::toDto);
    }

    /**
     * Delete the eqItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EqItem : {}", id);
        eqItemRepository.deleteById(id);
    }
}
