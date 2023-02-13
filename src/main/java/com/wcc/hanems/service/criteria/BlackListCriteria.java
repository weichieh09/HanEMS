package com.wcc.hanems.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

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
public class BlackListCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter blackDate;

    private InstantFilter createDate;

    private InstantFilter modifyDate;

    private LongFilter personId;

    private Boolean distinct;

    public BlackListCriteria() {}

    public BlackListCriteria(BlackListCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.blackDate = other.blackDate == null ? null : other.blackDate.copy();
        this.createDate = other.createDate == null ? null : other.createDate.copy();
        this.modifyDate = other.modifyDate == null ? null : other.modifyDate.copy();
        this.personId = other.personId == null ? null : other.personId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BlackListCriteria copy() {
        return new BlackListCriteria(this);
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

    public InstantFilter getBlackDate() {
        return blackDate;
    }

    public InstantFilter blackDate() {
        if (blackDate == null) {
            blackDate = new InstantFilter();
        }
        return blackDate;
    }

    public void setBlackDate(InstantFilter blackDate) {
        this.blackDate = blackDate;
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
        final BlackListCriteria that = (BlackListCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(blackDate, that.blackDate) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blackDate, createDate, modifyDate, personId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BlackListCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (blackDate != null ? "blackDate=" + blackDate + ", " : "") +
            (createDate != null ? "createDate=" + createDate + ", " : "") +
            (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
            (personId != null ? "personId=" + personId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
