package com.wcc.hanems.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.wcc.hanems.domain.EqView} entity. This class is used
 * in {@link com.wcc.hanems.web.rest.EqViewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /eq-views?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EqViewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eqItemId;

    private StringFilter eqItemName;

    private StringFilter eqName;

    private StringFilter eqDescription;

    private InstantFilter eqCreateDate;

    private InstantFilter eqModifyDate;

    private Boolean distinct;

    public EqViewCriteria() {}

    public EqViewCriteria(EqViewCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.eqItemId = other.eqItemId == null ? null : other.eqItemId.copy();
        this.eqItemName = other.eqItemName == null ? null : other.eqItemName.copy();
        this.eqName = other.eqName == null ? null : other.eqName.copy();
        this.eqDescription = other.eqDescription == null ? null : other.eqDescription.copy();
        this.eqCreateDate = other.eqCreateDate == null ? null : other.eqCreateDate.copy();
        this.eqModifyDate = other.eqModifyDate == null ? null : other.eqModifyDate.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EqViewCriteria copy() {
        return new EqViewCriteria(this);
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

    public IntegerFilter getEqItemId() {
        return eqItemId;
    }

    public IntegerFilter eqItemId() {
        if (eqItemId == null) {
            eqItemId = new IntegerFilter();
        }
        return eqItemId;
    }

    public void setEqItemId(IntegerFilter eqItemId) {
        this.eqItemId = eqItemId;
    }

    public StringFilter getEqItemName() {
        return eqItemName;
    }

    public StringFilter eqItemName() {
        if (eqItemName == null) {
            eqItemName = new StringFilter();
        }
        return eqItemName;
    }

    public void setEqItemName(StringFilter eqItemName) {
        this.eqItemName = eqItemName;
    }

    public StringFilter getEqName() {
        return eqName;
    }

    public StringFilter eqName() {
        if (eqName == null) {
            eqName = new StringFilter();
        }
        return eqName;
    }

    public void setEqName(StringFilter eqName) {
        this.eqName = eqName;
    }

    public StringFilter getEqDescription() {
        return eqDescription;
    }

    public StringFilter eqDescription() {
        if (eqDescription == null) {
            eqDescription = new StringFilter();
        }
        return eqDescription;
    }

    public void setEqDescription(StringFilter eqDescription) {
        this.eqDescription = eqDescription;
    }

    public InstantFilter getEqCreateDate() {
        return eqCreateDate;
    }

    public InstantFilter eqCreateDate() {
        if (eqCreateDate == null) {
            eqCreateDate = new InstantFilter();
        }
        return eqCreateDate;
    }

    public void setEqCreateDate(InstantFilter eqCreateDate) {
        this.eqCreateDate = eqCreateDate;
    }

    public InstantFilter getEqModifyDate() {
        return eqModifyDate;
    }

    public InstantFilter eqModifyDate() {
        if (eqModifyDate == null) {
            eqModifyDate = new InstantFilter();
        }
        return eqModifyDate;
    }

    public void setEqModifyDate(InstantFilter eqModifyDate) {
        this.eqModifyDate = eqModifyDate;
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
        final EqViewCriteria that = (EqViewCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(eqItemId, that.eqItemId) &&
            Objects.equals(eqItemName, that.eqItemName) &&
            Objects.equals(eqName, that.eqName) &&
            Objects.equals(eqDescription, that.eqDescription) &&
            Objects.equals(eqCreateDate, that.eqCreateDate) &&
            Objects.equals(eqModifyDate, that.eqModifyDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eqItemId, eqItemName, eqName, eqDescription, eqCreateDate, eqModifyDate, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EqViewCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (eqItemId != null ? "eqItemId=" + eqItemId + ", " : "") +
            (eqItemName != null ? "eqItemName=" + eqItemName + ", " : "") +
            (eqName != null ? "eqName=" + eqName + ", " : "") +
            (eqDescription != null ? "eqDescription=" + eqDescription + ", " : "") +
            (eqCreateDate != null ? "eqCreateDate=" + eqCreateDate + ", " : "") +
            (eqModifyDate != null ? "eqModifyDate=" + eqModifyDate + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
