package com.wcc.hanems.customized.service;

import com.wcc.hanems.domain.Person;
import com.wcc.hanems.service.PersonQueryService;
import com.wcc.hanems.service.criteria.PersonCriteria;
import com.wcc.hanems.service.dto.PersonDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.StringFilter;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonCtService {

    private final Logger log = LoggerFactory.getLogger(PersonCtService.class);

    @Autowired
    private PersonQueryService personQueryService;

    public PersonDTO findIsExist(String name) {
        PersonDTO result = null;
        PersonCriteria criteria = new PersonCriteria();
        StringFilter stringFilter = new StringFilter();
        stringFilter.setEquals(name);
        criteria.setName(stringFilter);
        List<PersonDTO> byCriteria = personQueryService.findByCriteria(criteria);
        if (byCriteria.size() > 0) result = byCriteria.get(0);
        return result;
    }
}