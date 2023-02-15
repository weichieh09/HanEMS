package com.wcc.hanems.customized.service;

import com.wcc.hanems.domain.Equipment;
import com.wcc.hanems.domain.LendReturnRecord;
import com.wcc.hanems.domain.Person;
import com.wcc.hanems.repository.LendReturnRecordRepository;
import com.wcc.hanems.service.EquipmentService;
import com.wcc.hanems.service.LendReturnRecordQueryService;
import com.wcc.hanems.service.criteria.LendReturnRecordCriteria;
import com.wcc.hanems.service.dto.EquipmentDTO;
import com.wcc.hanems.service.dto.LendReturnRecordDTO;
import com.wcc.hanems.service.dto.PersonDTO;
import com.wcc.hanems.service.dto.StatusDTO;
import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.LongFilter;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class LendReturnRecordCtService {

    private final Logger log = LoggerFactory.getLogger(LendReturnRecordCtService.class);

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private LendReturnRecordQueryService lendReturnRecordQueryService;

    @Autowired
    private LendReturnRecordRepository lendReturnRecordRepository;

    public void setEqStatus2(Long id) {
        EquipmentDTO equipmentDTO = equipmentService.findOne(id).get();
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setId(2L);
        equipmentDTO.setStatus(statusDTO);
        equipmentDTO.setModifyDate(Instant.now());
        equipmentService.save(equipmentDTO);
    }

    public void setEqStatus1(Long id) {
        EquipmentDTO equipmentDTO = equipmentService.findOne(id).get();
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setId(1L);
        equipmentDTO.setStatus(statusDTO);
        equipmentDTO.setModifyDate(Instant.now());
        equipmentService.save(equipmentDTO);
    }

    public LendReturnRecordDTO findByEqIdAndPersonId(Long eqId, Long personId) {
        List<LendReturnRecord> result = lendReturnRecordRepository.findByEqIdAndPersonId(eqId, personId);
        LendReturnRecord lendReturnRecord = result.get(0);

        Person person = lendReturnRecord.getPerson();
        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(person, personDTO);

        Equipment equipment = lendReturnRecord.getEquipment();
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        BeanUtils.copyProperties(equipment, equipmentDTO);

        LendReturnRecordDTO lendReturnRecordDTO = new LendReturnRecordDTO();
        BeanUtils.copyProperties(lendReturnRecord, lendReturnRecordDTO);
        lendReturnRecordDTO.setPerson(personDTO);
        lendReturnRecordDTO.setEquipment(equipmentDTO);

        return lendReturnRecordDTO;
    }
}
