package com.wcc.hanems.customized.dto;

import com.wcc.hanems.service.criteria.BlackListCriteria;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.wcc.hanems.domain.BlackList} entity. This class is used
 * in {@link com.wcc.hanems.web.rest.BlackListResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /black-lists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BlackListCtCriteria extends BlackListCriteria {

    private StringFilter personIdno;

    private StringFilter personName;

    public BlackListCtCriteria() {
        super();
    }

    public BlackListCtCriteria(BlackListCriteria other, StringFilter personIdno, StringFilter personName) {
        super(other);
        this.personIdno = personIdno;
        this.personName = personName;
    }

    public StringFilter getPersonIdno() {
        return personIdno;
    }

    public void setPersonIdno(StringFilter personIdno) {
        this.personIdno = personIdno;
    }

    public StringFilter getPersonName() {
        return personName;
    }

    public void setPersonName(StringFilter personName) {
        this.personName = personName;
    }
}
