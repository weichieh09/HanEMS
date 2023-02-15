package com.wcc.hanems.customized.controller;

import com.wcc.hanems.customized.service.LendReturnRecordCtService;
import com.wcc.hanems.repository.LendReturnRecordRepository;
import com.wcc.hanems.service.LendReturnRecordQueryService;
import com.wcc.hanems.service.LendReturnRecordService;
import com.wcc.hanems.service.dto.LendReturnRecordDTO;
import com.wcc.hanems.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link com.wcc.hanems.domain.LendReturnRecord}.
 */
@RestController
@RequestMapping("/api")
public class LendReturnRecordCtController {

    private final Logger log = LoggerFactory.getLogger(LendReturnRecordCtController.class);

    private static final String ENTITY_NAME = "lendReturnRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LendReturnRecordService lendReturnRecordService;

    private final LendReturnRecordRepository lendReturnRecordRepository;

    private final LendReturnRecordQueryService lendReturnRecordQueryService;

    @Autowired
    private LendReturnRecordCtService lendReturnRecordCtService;

    public LendReturnRecordCtController(
        LendReturnRecordService lendReturnRecordService,
        LendReturnRecordRepository lendReturnRecordRepository,
        LendReturnRecordQueryService lendReturnRecordQueryService
    ) {
        this.lendReturnRecordService = lendReturnRecordService;
        this.lendReturnRecordRepository = lendReturnRecordRepository;
        this.lendReturnRecordQueryService = lendReturnRecordQueryService;
    }

    @PostMapping("/wcc105Rent")
    public ResponseEntity<LendReturnRecordDTO> createLendReturnRecord(@Valid @RequestBody LendReturnRecordDTO lendReturnRecordDTO)
        throws URISyntaxException {
        log.debug("REST request to save LendReturnRecord : {}", lendReturnRecordDTO);
        if (lendReturnRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new lendReturnRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        lendReturnRecordCtService.setEqStatus2(lendReturnRecordDTO.getEquipment().getId());
        lendReturnRecordDTO.setCreateDate(Instant.now());
        lendReturnRecordDTO.setModifyDate(Instant.now());
        LendReturnRecordDTO result = lendReturnRecordService.save(lendReturnRecordDTO);
        return ResponseEntity
            .created(new URI("/api/lend-return-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/wcc105Return")
    public ResponseEntity<LendReturnRecordDTO> updateLendReturnRecord(@Valid @RequestBody LendReturnRecordDTO lendReturnRecordDTO)
        throws URISyntaxException {
        LendReturnRecordDTO dto = lendReturnRecordCtService.findByEqIdAndPersonId(
            lendReturnRecordDTO.getEquipment().getId(),
            lendReturnRecordDTO.getPerson().getId()
        );
        dto.setReturnDate(lendReturnRecordDTO.getReturnDate());
        dto.setModifyDate(Instant.now());
        LendReturnRecordDTO result = lendReturnRecordService.update(dto);
        lendReturnRecordCtService.setEqStatus1(lendReturnRecordDTO.getEquipment().getId());

        return ResponseEntity
            .created(new URI("/api/lend-return-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
