package com.wcc.hanems.service;

import com.wcc.hanems.domain.EqView;
import com.wcc.hanems.repository.EqViewRepository;
import com.wcc.hanems.service.dto.EqViewDTO;
import com.wcc.hanems.service.mapper.EqViewMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EqView}.
 */
@Service
@Transactional
public class EqViewService {

    private final Logger log = LoggerFactory.getLogger(EqViewService.class);

    private final EqViewRepository eqViewRepository;

    private final EqViewMapper eqViewMapper;

    public EqViewService(EqViewRepository eqViewRepository, EqViewMapper eqViewMapper) {
        this.eqViewRepository = eqViewRepository;
        this.eqViewMapper = eqViewMapper;
    }

    /**
     * Save a eqView.
     *
     * @param eqViewDTO the entity to save.
     * @return the persisted entity.
     */
    public EqViewDTO save(EqViewDTO eqViewDTO) {
        log.debug("Request to save EqView : {}", eqViewDTO);
        EqView eqView = eqViewMapper.toEntity(eqViewDTO);
        eqView = eqViewRepository.save(eqView);
        return eqViewMapper.toDto(eqView);
    }

    /**
     * Update a eqView.
     *
     * @param eqViewDTO the entity to save.
     * @return the persisted entity.
     */
    public EqViewDTO update(EqViewDTO eqViewDTO) {
        log.debug("Request to update EqView : {}", eqViewDTO);
        EqView eqView = eqViewMapper.toEntity(eqViewDTO);
        eqView = eqViewRepository.save(eqView);
        return eqViewMapper.toDto(eqView);
    }

    /**
     * Partially update a eqView.
     *
     * @param eqViewDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EqViewDTO> partialUpdate(EqViewDTO eqViewDTO) {
        log.debug("Request to partially update EqView : {}", eqViewDTO);

        return eqViewRepository
            .findById(eqViewDTO.getId())
            .map(existingEqView -> {
                eqViewMapper.partialUpdate(existingEqView, eqViewDTO);

                return existingEqView;
            })
            .map(eqViewRepository::save)
            .map(eqViewMapper::toDto);
    }

    /**
     * Get all the eqViews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EqViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EqViews");
        return eqViewRepository.findAll(pageable).map(eqViewMapper::toDto);
    }

    /**
     * Get one eqView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EqViewDTO> findOne(Long id) {
        log.debug("Request to get EqView : {}", id);
        return eqViewRepository.findById(id).map(eqViewMapper::toDto);
    }

    /**
     * Delete the eqView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EqView : {}", id);
        eqViewRepository.deleteById(id);
    }
}
