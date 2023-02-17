package com.wcc.hanems.customized.service;

import com.wcc.hanems.service.BlackListQueryService;
import com.wcc.hanems.service.PersonService;
import com.wcc.hanems.service.criteria.BlackListCriteria;
import com.wcc.hanems.service.dto.PersonDTO;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.LongFilter;

@Service
@Transactional
public class BlackListCtService {

    private final Logger log = LoggerFactory.getLogger(BlackListCtService.class);

    @Autowired
    private BlackListQueryService blackListQueryService;

    @Autowired
    private PersonService personService;

    public void isNeedLock(Long personId) {
        BlackListCriteria criteria = new BlackListCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(personId);
        criteria.setPersonId(longFilter);
        Long countByCriteria = blackListQueryService.countByCriteria(criteria);
        /**
         * 2次 = 1個月
         * 3次 = 6個月
         * 4次 = 永久
         */

        if (countByCriteria >= 2) {
            PersonDTO personDTO = personService.findOne(personId).get();
            if (countByCriteria == 2) personDTO.setPending(-2);
            if (countByCriteria == 3) personDTO.setPending(-3);
            if (countByCriteria == 4) personDTO.setPending(-4);
            personDTO.setModifyDate(Instant.now());
            personService.save(personDTO);
        }
    }
}
