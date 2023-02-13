package com.wcc.hanems.service;

import com.wcc.hanems.domain.LendReturnRecord;
import com.wcc.hanems.repository.LendReturnRecordRepository;
import com.wcc.hanems.service.dto.LendReturnRecordDTO;
import com.wcc.hanems.service.mapper.LendReturnRecordMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LendReturnRecord}.
 */
@Service
@Transactional
public class LendReturnRecordService {

    private final Logger log = LoggerFactory.getLogger(LendReturnRecordService.class);

    private final LendReturnRecordRepository lendReturnRecordRepository;

    private final LendReturnRecordMapper lendReturnRecordMapper;

    public LendReturnRecordService(LendReturnRecordRepository lendReturnRecordRepository, LendReturnRecordMapper lendReturnRecordMapper) {
        this.lendReturnRecordRepository = lendReturnRecordRepository;
        this.lendReturnRecordMapper = lendReturnRecordMapper;
    }

    /**
     * Save a lendReturnRecord.
     *
     * @param lendReturnRecordDTO the entity to save.
     * @return the persisted entity.
     */
    public LendReturnRecordDTO save(LendReturnRecordDTO lendReturnRecordDTO) {
        log.debug("Request to save LendReturnRecord : {}", lendReturnRecordDTO);
        LendReturnRecord lendReturnRecord = lendReturnRecordMapper.toEntity(lendReturnRecordDTO);
        lendReturnRecord = lendReturnRecordRepository.save(lendReturnRecord);
        return lendReturnRecordMapper.toDto(lendReturnRecord);
    }

    /**
     * Update a lendReturnRecord.
     *
     * @param lendReturnRecordDTO the entity to save.
     * @return the persisted entity.
     */
    public LendReturnRecordDTO update(LendReturnRecordDTO lendReturnRecordDTO) {
        log.debug("Request to update LendReturnRecord : {}", lendReturnRecordDTO);
        LendReturnRecord lendReturnRecord = lendReturnRecordMapper.toEntity(lendReturnRecordDTO);
        lendReturnRecord = lendReturnRecordRepository.save(lendReturnRecord);
        return lendReturnRecordMapper.toDto(lendReturnRecord);
    }

    /**
     * Partially update a lendReturnRecord.
     *
     * @param lendReturnRecordDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LendReturnRecordDTO> partialUpdate(LendReturnRecordDTO lendReturnRecordDTO) {
        log.debug("Request to partially update LendReturnRecord : {}", lendReturnRecordDTO);

        return lendReturnRecordRepository
            .findById(lendReturnRecordDTO.getId())
            .map(existingLendReturnRecord -> {
                lendReturnRecordMapper.partialUpdate(existingLendReturnRecord, lendReturnRecordDTO);

                return existingLendReturnRecord;
            })
            .map(lendReturnRecordRepository::save)
            .map(lendReturnRecordMapper::toDto);
    }

    /**
     * Get all the lendReturnRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LendReturnRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LendReturnRecords");
        return lendReturnRecordRepository.findAll(pageable).map(lendReturnRecordMapper::toDto);
    }

    /**
     * Get one lendReturnRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LendReturnRecordDTO> findOne(Long id) {
        log.debug("Request to get LendReturnRecord : {}", id);
        return lendReturnRecordRepository.findById(id).map(lendReturnRecordMapper::toDto);
    }

    /**
     * Delete the lendReturnRecord by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LendReturnRecord : {}", id);
        lendReturnRecordRepository.deleteById(id);
    }
}
