package com.wcc.hanems.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.wcc.hanems.domain.Person} entity. This class is used
 * in {@link com.wcc.hanems.web.rest.PersonResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /people?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PersonCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter idno;

    private StringFilter name;

    private StringFilter phone;

    private InstantFilter createDate;

    private InstantFilter modifyDate;

    private Boolean distinct;

    public PersonCriteria() {}

    public PersonCriteria(PersonCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.idno = other.idno == null ? null : other.idno.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.createDate = other.createDate == null ? null : other.createDate.copy();
        this.modifyDate = other.modifyDate == null ? null : other.modifyDate.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PersonCriteria copy() {
        return new PersonCriteria(this);
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

    public StringFilter getIdno() {
        return idno;
    }

    public StringFilter idno() {
        if (idno == null) {
            idno = new StringFilter();
        }
        return idno;
    }

    public void setIdno(StringFilter idno) {
        this.idno = idno;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public StringFilter phone() {
        if (phone == null) {
            phone = new StringFilter();
        }
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
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
        final PersonCriteria that = (PersonCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idno, that.idno) &&
            Objects.equals(name, that.name) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idno, name, phone, createDate, modifyDate, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (idno != null ? "idno=" + idno + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (phone != null ? "phone=" + phone + ", " : "") +
            (createDate != null ? "createDate=" + createDate + ", " : "") +
            (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
