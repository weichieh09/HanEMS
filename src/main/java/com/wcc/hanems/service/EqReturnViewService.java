package com.wcc.hanems.service;

import com.wcc.hanems.domain.EqReturnView;
import com.wcc.hanems.repository.EqReturnViewRepository;
import com.wcc.hanems.service.dto.EqReturnViewDTO;
import com.wcc.hanems.service.mapper.EqReturnViewMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EqReturnView}.
 */
@Service
@Transactional
public class EqReturnViewService {

    private final Logger log = LoggerFactory.getLogger(EqReturnViewService.class);

    private final EqReturnViewRepository eqReturnViewRepository;

    private final EqReturnViewMapper eqReturnViewMapper;

    public EqReturnViewService(EqReturnViewRepository eqReturnViewRepository, EqReturnViewMapper eqReturnViewMapper) {
        this.eqReturnViewRepository = eqReturnViewRepository;
        this.eqReturnViewMapper = eqReturnViewMapper;
    }

    /**
     * Save a eqReturnView.
     *
     * @param eqReturnViewDTO the entity to save.
     * @return the persisted entity.
     */
    public EqReturnViewDTO save(EqReturnViewDTO eqReturnViewDTO) {
        log.debug("Request to save EqReturnView : {}", eqReturnViewDTO);
        EqReturnView eqReturnView = eqReturnViewMapper.toEntity(eqReturnViewDTO);
        eqReturnView = eqReturnViewRepository.save(eqReturnView);
        return eqReturnViewMapper.toDto(eqReturnView);
    }

    /**
     * Update a eqReturnView.
     *
     * @param eqReturnViewDTO the entity to save.
     * @return the persisted entity.
     */
    public EqReturnViewDTO update(EqReturnViewDTO eqReturnViewDTO) {
        log.debug("Request to update EqReturnView : {}", eqReturnViewDTO);
        EqReturnView eqReturnView = eqReturnViewMapper.toEntity(eqReturnViewDTO);
        eqReturnView = eqReturnViewRepository.save(eqReturnView);
        return eqReturnViewMapper.toDto(eqReturnView);
    }

    /**
     * Partially update a eqReturnView.
     *
     * @param eqReturnViewDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EqReturnViewDTO> partialUpdate(EqReturnViewDTO eqReturnViewDTO) {
        log.debug("Request to partially update EqReturnView : {}", eqReturnViewDTO);

        return eqReturnViewRepository
            .findById(eqReturnViewDTO.getId())
            .map(existingEqReturnView -> {
                eqReturnViewMapper.partialUpdate(existingEqReturnView, eqReturnViewDTO);

                return existingEqReturnView;
            })
            .map(eqReturnViewRepository::save)
            .map(eqReturnViewMapper::toDto);
    }

    /**
     * Get all the eqReturnViews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EqReturnViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EqReturnViews");
        return eqReturnViewRepository.findAll(pageable).map(eqReturnViewMapper::toDto);
    }

    /**
     * Get one eqReturnView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EqReturnViewDTO> findOne(Long id) {
        log.debug("Request to get EqReturnView : {}", id);
        return eqReturnViewRepository.findById(id).map(eqReturnViewMapper::toDto);
    }

    /**
     * Delete the eqReturnView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EqReturnView : {}", id);
        eqReturnViewRepository.deleteById(id);
    }
}
