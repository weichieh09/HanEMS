package com.wcc.hanems.service;

import com.wcc.hanems.domain.Reason;
import com.wcc.hanems.repository.ReasonRepository;
import com.wcc.hanems.service.dto.ReasonDTO;
import com.wcc.hanems.service.mapper.ReasonMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Reason}.
 */
@Service
@Transactional
public class ReasonService {

    private final Logger log = LoggerFactory.getLogger(ReasonService.class);

    private final ReasonRepository reasonRepository;

    private final ReasonMapper reasonMapper;

    public ReasonService(ReasonRepository reasonRepository, ReasonMapper reasonMapper) {
        this.reasonRepository = reasonRepository;
        this.reasonMapper = reasonMapper;
    }

    /**
     * Save a reason.
     *
     * @param reasonDTO the entity to save.
     * @return the persisted entity.
     */
    public ReasonDTO save(ReasonDTO reasonDTO) {
        log.debug("Request to save Reason : {}", reasonDTO);
        Reason reason = reasonMapper.toEntity(reasonDTO);
        reason = reasonRepository.save(reason);
        return reasonMapper.toDto(reason);
    }

    /**
     * Update a reason.
     *
     * @param reasonDTO the entity to save.
     * @return the persisted entity.
     */
    public ReasonDTO update(ReasonDTO reasonDTO) {
        log.debug("Request to update Reason : {}", reasonDTO);
        Reason reason = reasonMapper.toEntity(reasonDTO);
        reason = reasonRepository.save(reason);
        return reasonMapper.toDto(reason);
    }

    /**
     * Partially update a reason.
     *
     * @param reasonDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ReasonDTO> partialUpdate(ReasonDTO reasonDTO) {
        log.debug("Request to partially update Reason : {}", reasonDTO);

        return reasonRepository
            .findById(reasonDTO.getId())
            .map(existingReason -> {
                reasonMapper.partialUpdate(existingReason, reasonDTO);

                return existingReason;
            })
            .map(reasonRepository::save)
            .map(reasonMapper::toDto);
    }

    /**
     * Get all the reasons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReasonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reasons");
        return reasonRepository.findAll(pageable).map(reasonMapper::toDto);
    }

    /**
     * Get one reason by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReasonDTO> findOne(Long id) {
        log.debug("Request to get Reason : {}", id);
        return reasonRepository.findById(id).map(reasonMapper::toDto);
    }

    /**
     * Delete the reason by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Reason : {}", id);
        reasonRepository.deleteById(id);
    }
}
