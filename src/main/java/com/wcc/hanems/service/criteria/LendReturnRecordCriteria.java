package com.wcc.hanems.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

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
public class LendReturnRecordCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter lendDate;

    private InstantFilter returnDate;

    private InstantFilter createDate;

    private InstantFilter modifyDate;

    private LongFilter equipmentId;

    private LongFilter personId;

    private LongFilter reasonId;

    private Boolean distinct;

    public LendReturnRecordCriteria() {}

    public LendReturnRecordCriteria(LendReturnRecordCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.lendDate = other.lendDate == null ? null : other.lendDate.copy();
        this.returnDate = other.returnDate == null ? null : other.returnDate.copy();
        this.createDate = other.createDate == null ? null : other.createDate.copy();
        this.modifyDate = other.modifyDate == null ? null : other.modifyDate.copy();
        this.equipmentId = other.equipmentId == null ? null : other.equipmentId.copy();
        this.personId = other.personId == null ? null : other.personId.copy();
        this.reasonId = other.reasonId == null ? null : other.reasonId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public LendReturnRecordCriteria copy() {
        return new LendReturnRecordCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getLendDate() {
        return lendDate;
    }

    public InstantFilter lendDate() {
        if (lendDate == null) {
            lendDate = new InstantFilter();
        }
        return lendDate;
    }

    public void setLendDate(InstantFilter lendDate) {
        this.lendDate = lendDate;
    }

    public InstantFilter getReturnDate() {
        return returnDate;
    }

    public InstantFilter returnDate() {
        if (returnDate == null) {
            returnDate = new InstantFilter();
        }
        return returnDate;
    }

    public void setReturnDate(InstantFilter returnDate) {
        this.returnDate = returnDate;
    }

    public InstantFilter getCreateDate() {
        return createDate;
    }

    public InstantFilter createDate() {
        if (createDate == null) {
            createDate = new InstantFilter();
        }
        return createDate;
    }

    public void setCreateDate(InstantFilter createDate) {
        this.createDate = createDate;
    }

    public InstantFilter getModifyDate() {
        return modifyDate;
    }

    public InstantFilter modifyDate() {
        if (modifyDate == null) {
            modifyDate = new InstantFilter();
        }
        return modifyDate;
    }

    public void setModifyDate(InstantFilter modifyDate) {
        this.modifyDate = modifyDate;
    }

    public LongFilter getEquipmentId() {
        return equipmentId;
    }

    public LongFilter equipmentId() {
        if (equipmentId == null) {
            equipmentId = new LongFilter();
        }
        return equipmentId;
    }

    public void setEquipmentId(LongFilter equipmentId) {
        this.equipmentId = equipmentId;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public LongFilter personId() {
        if (personId == null) {
            personId = new LongFilter();
        }
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getReasonId() {
        return reasonId;
    }

    public LongFilter reasonId() {
        if (reasonId == null) {
            reasonId = new LongFilter();
        }
        return reasonId;
    }

    public void setReasonId(LongFilter reasonId) {
        this.reasonId = reasonId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LendReturnRecordCriteria that = (LendReturnRecordCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(lendDate, that.lendDate) &&
            Objects.equals(returnDate, that.returnDate) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(equipmentId, that.equipmentId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(reasonId, that.reasonId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lendDate, returnDate, createDate, modifyDate, equipmentId, personId, reasonId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LendReturnRecordCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (lendDate != null ? "lendDate=" + lendDate + ", " : "") +
            (returnDate != null ? "returnDate=" + returnDate + ", " : "") +
            (createDate != null ? "createDate=" + createDate + ", " : "") +
            (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
            (equipmentId != null ? "equipmentId=" + equipmentId + ", " : "") +
            (personId != null ? "personId=" + personId + ", " : "") +
            (reasonId != null ? "reasonId=" + reasonId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
