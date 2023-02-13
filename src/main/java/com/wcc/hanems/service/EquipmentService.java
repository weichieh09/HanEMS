package com.wcc.hanems.service;

import com.wcc.hanems.domain.Equipment;
import com.wcc.hanems.repository.EquipmentRepository;
import com.wcc.hanems.service.dto.EquipmentDTO;
import com.wcc.hanems.service.mapper.EquipmentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Equipment}.
 */
@Service
@Transactional
public class EquipmentService {

    private final Logger log = LoggerFactory.getLogger(EquipmentService.class);

    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    public EquipmentService(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    /**
     * Save a equipment.
     *
     * @param equipmentDTO the entity to save.
     * @return the persisted entity.
     */
    public EquipmentDTO save(EquipmentDTO equipmentDTO) {
        log.debug("Request to save Equipment : {}", equipmentDTO);
        Equipment equipment = equipmentMapper.toEntity(equipmentDTO);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(equipment);
    }

    /**
     * Update a equipment.
     *
     * @param equipmentDTO the entity to save.
     * @return the persisted entity.
     */
    public EquipmentDTO update(EquipmentDTO equipmentDTO) {
        log.debug("Request to update Equipment : {}", equipmentDTO);
        Equipment equipment = equipmentMapper.toEntity(equipmentDTO);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(equipment);
    }

    /**
     * Partially update a equipment.
     *
     * @param equipmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EquipmentDTO> partialUpdate(EquipmentDTO equipmentDTO) {
        log.debug("Request to partially update Equipment : {}", equipmentDTO);

        return equipmentRepository
            .findById(equipmentDTO.getId())
            .map(existingEquipment -> {
                equipmentMapper.partialUpdate(existingEquipment, equipmentDTO);

                return existingEquipment;
            })
            .map(equipmentRepository::save)
            .map(equipmentMapper::toDto);
    }

    /**
     * Get all the equipment.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EquipmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Equipment");
        return equipmentRepository.findAll(pageable).map(equipmentMapper::toDto);
    }

    /**
     * Get one equipment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EquipmentDTO> findOne(Long id) {
        log.debug("Request to get Equipment : {}", id);
        return equipmentRepository.findById(id).map(equipmentMapper::toDto);
    }

    /**
     * Delete the equipment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Equipment : {}", id);
        equipmentRepository.deleteById(id);
    }
}
