package com.wcc.hanems.customized.dto;

import com.wcc.hanems.service.criteria.LendReturnRecordCriteria;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.wcc.hanems.domain.LendReturnRecord} entity. This class is used
 * in {@link com.wcc.hanems.web.rest.LendReturnRecordResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /lend-return-records?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LendReturnRecordCtCriteria extends LendReturnRecordCriteria {

    private StringFilter equipmentName;

    private StringFilter personIdno;

    private StringFilter personName;

    public LendReturnRecordCtCriteria() {
        super();
    }

    public LendReturnRecordCtCriteria(
        LendReturnRecordCriteria other,
        StringFilter equipmentName,
        StringFilter personIdno,
        StringFilter personName
    ) {
        super(other);
        this.equipmentName = equipmentName;
        this.personIdno = personIdno;
        this.personName = personName;
    }

    public StringFilter getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(StringFilter equipmentName) {
        this.equipmentName = equipmentName;
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
