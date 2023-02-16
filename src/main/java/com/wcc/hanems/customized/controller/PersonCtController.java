package com.wcc.hanems.customized.controller;

import com.wcc.hanems.customized.dto.Wcc103ReqDTO;
import com.wcc.hanems.customized.service.PersonCtService;
import com.wcc.hanems.repository.PersonRepository;
import com.wcc.hanems.service.PersonQueryService;
import com.wcc.hanems.service.PersonService;
import com.wcc.hanems.service.dto.PersonDTO;
import com.wcc.hanems.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.wcc.hanems.domain.Person}.
 */
@RestController
@RequestMapping("/api")
public class PersonCtController {

    private final Logger log = LoggerFactory.getLogger(PersonCtController.class);

    private static final String ENTITY_NAME = "person";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonService personService;

    private final PersonRepository personRepository;

    private final PersonQueryService personQueryService;

    @Autowired
    private PersonCtService personCtService;

    public PersonCtController(PersonService personService, PersonRepository personRepository, PersonQueryService personQueryService) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.personQueryService = personQueryService;
    }

    @PostMapping("/customized/people")
    public ResponseEntity<PersonDTO> partialUpdatePerson(@Valid @RequestBody Wcc103ReqDTO reqDTO) throws URISyntaxException {
        log.debug("REST request to save Person : {}", reqDTO);
        if (reqDTO.getId() != null) {
            throw new BadRequestAlertException("A new person cannot already have an ID", ENTITY_NAME, "idexists");
        }

        //        查有無存在
        PersonDTO personDTO = personCtService.findIsExist(reqDTO.getName());
        if (personDTO != null) {
            reqDTO.setId(personDTO.getId());
            reqDTO.setCreateDate(personDTO.getCreateDate());
        } else {
            reqDTO.setCreateDate(Instant.now());
        }
        reqDTO.setModifyDate(Instant.now());

        PersonDTO result = personService.update(reqDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/wcc105/{id}")
    public ResponseEntity<PersonDTO> updatePerson(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PersonDTO reqDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Person : {}, {}", id, reqDTO);
        if (reqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PersonDTO personDTO = personService.findOne(reqDTO.getId()).get();
        personDTO.setPending(reqDTO.getPending());
        personDTO.setModifyDate(Instant.now());

        PersonDTO result = personService.update(personDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personDTO.getId().toString()))
            .body(result);
    }
}
