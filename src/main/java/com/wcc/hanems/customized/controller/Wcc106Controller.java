package com.wcc.hanems.customized.controller;

import com.wcc.hanems.customized.dto.CreateEquipmentReq;
import com.wcc.hanems.customized.dto.UpdateEquipmentReq;
import com.wcc.hanems.customized.service.Wcc106Service;
import com.wcc.hanems.repository.EquipmentRepository;
import com.wcc.hanems.service.EquipmentService;
import com.wcc.hanems.service.dto.EquipmentDTO;
import com.wcc.hanems.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

@Slf4j
@RestController
@RequestMapping("/api")
public class Wcc106Controller {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private Wcc106Service wcc106Service;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping("/wcc106")
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody CreateEquipmentReq reqDTO) throws URISyntaxException {
        log.debug("REST request to save Equipment : {}", reqDTO);
        if (reqDTO.getId() != null) {
            throw new BadRequestAlertException("A new equipment cannot already have an ID", CLASS_NAME, "idexists");
        }
        EquipmentDTO equipmentDTO = wcc106Service.copyProperties(reqDTO);
        EquipmentDTO result = equipmentService.save(equipmentDTO);
        return ResponseEntity
            .created(new URI("/api/wcc106/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, CLASS_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/wcc106/{id}")
    public ResponseEntity<EquipmentDTO> updateEquipment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UpdateEquipmentReq reqDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Equipment : {}, {}", id, reqDTO);
        if (reqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", CLASS_NAME, "idnull");
        }
        if (!Objects.equals(id, reqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", CLASS_NAME, "idinvalid");
        }

        if (!equipmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", CLASS_NAME, "idnotfound");
        }
        EquipmentDTO equipmentDTO = wcc106Service.copyProperties(reqDTO);
        EquipmentDTO result = equipmentService.update(equipmentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, CLASS_NAME, equipmentDTO.getId().toString()))
            .body(result);
    }
}
