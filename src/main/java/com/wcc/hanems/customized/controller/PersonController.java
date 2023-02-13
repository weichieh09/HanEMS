package com.wcc.hanems.customized.controller;

import com.wcc.hanems.customized.dto.Wcc103ReqDTO;
import com.wcc.hanems.repository.PersonRepository;
import com.wcc.hanems.service.PersonQueryService;
import com.wcc.hanems.service.PersonService;
import com.wcc.hanems.service.dto.PersonDTO;
import com.wcc.hanems.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link com.wcc.hanems.domain.Person}.
 */
@RestController
@RequestMapping("/api/customized/")
public class PersonController {

    private final Logger log = LoggerFactory.getLogger(PersonController.class);

    private static final String ENTITY_NAME = "person";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonService personService;

    private final PersonRepository personRepository;

    private final PersonQueryService personQueryService;

    public PersonController(PersonService personService, PersonRepository personRepository, PersonQueryService personQueryService) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.personQueryService = personQueryService;
    }

    @PostMapping("/people")
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody Wcc103ReqDTO reqDTO) throws URISyntaxException {
        log.debug("REST request to save Person : {}", reqDTO);
        if (reqDTO.getId() != null) {
            throw new BadRequestAlertException("A new person cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonDTO result = personService.save(reqDTO);
        return ResponseEntity
            .created(new URI("/api/people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
