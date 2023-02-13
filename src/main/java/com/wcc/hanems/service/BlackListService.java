package com.wcc.hanems.service;

import com.wcc.hanems.domain.BlackList;
import com.wcc.hanems.repository.BlackListRepository;
import com.wcc.hanems.service.dto.BlackListDTO;
import com.wcc.hanems.service.mapper.BlackListMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BlackList}.
 */
@Service
@Transactional
public class BlackListService {

    private final Logger log = LoggerFactory.getLogger(BlackListService.class);

    private final BlackListRepository blackListRepository;

    private final BlackListMapper blackListMapper;

    public BlackListService(BlackListRepository blackListRepository, BlackListMapper blackListMapper) {
        this.blackListRepository = blackListRepository;
        this.blackListMapper = blackListMapper;
    }

    /**
     * Save a blackList.
     *
     * @param blackListDTO the entity to save.
     * @return the persisted entity.
     */
    public BlackListDTO save(BlackListDTO blackListDTO) {
        log.debug("Request to save BlackList : {}", blackListDTO);
        BlackList blackList = blackListMapper.toEntity(blackListDTO);
        blackList = blackListRepository.save(blackList);
        return blackListMapper.toDto(blackList);
    }

    /**
     * Update a blackList.
     *
     * @param blackListDTO the entity to save.
     * @return the persisted entity.
     */
    public BlackListDTO update(BlackListDTO blackListDTO) {
        log.debug("Request to update BlackList : {}", blackListDTO);
        BlackList blackList = blackListMapper.toEntity(blackListDTO);
        blackList = blackListRepository.save(blackList);
        return blackListMapper.toDto(blackList);
    }

    /**
     * Partially update a blackList.
     *
     * @param blackListDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BlackListDTO> partialUpdate(BlackListDTO blackListDTO) {
        log.debug("Request to partially update BlackList : {}", blackListDTO);

        return blackListRepository
            .findById(blackListDTO.getId())
            .map(existingBlackList -> {
                blackListMapper.partialUpdate(existingBlackList, blackListDTO);

                return existingBlackList;
            })
            .map(blackListRepository::save)
            .map(blackListMapper::toDto);
    }

    /**
     * Get all the blackLists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BlackListDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BlackLists");
        return blackListRepository.findAll(pageable).map(blackListMapper::toDto);
    }

    /**
     * Get one blackList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BlackListDTO> findOne(Long id) {
        log.debug("Request to get BlackList : {}", id);
        return blackListRepository.findById(id).map(blackListMapper::toDto);
    }

    /**
     * Delete the blackList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BlackList : {}", id);
        blackListRepository.deleteById(id);
    }
}
