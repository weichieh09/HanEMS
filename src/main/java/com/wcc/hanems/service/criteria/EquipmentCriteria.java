package com.wcc.hanems.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.wcc.hanems.domain.Equipment} entity. This class is used
 * in {@link com.wcc.hanems.web.rest.EquipmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /equipment?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EquipmentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter idno;

    private StringFilter name;

    private InstantFilter createDate;

    private InstantFilter modifyDate;

    private LongFilter itemId;

    private LongFilter statusId;

    private Boolean distinct;

    public EquipmentCriteria() {}

    public EquipmentCriteria(EquipmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.idno = other.idno == null ? null : other.idno.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.createDate = other.createDate == null ? null : other.createDate.copy();
        this.modifyDate = other.modifyDate == null ? null : other.modifyDate.copy();
        this.itemId = other.itemId == null ? null : other.itemId.copy();
        this.statusId = other.statusId == null ? null : other.statusId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EquipmentCriteria copy() {
        return new EquipmentCriteria(this);
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

    public LongFilter getItemId() {
        return itemId;
    }

    public LongFilter itemId() {
        if (itemId == null) {
            itemId = new LongFilter();
        }
        return itemId;
    }

    public void setItemId(LongFilter itemId) {
        this.itemId = itemId;
    }

    public LongFilter getStatusId() {
        return statusId;
    }

    public LongFilter statusId() {
        if (statusId == null) {
            statusId = new LongFilter();
        }
        return statusId;
    }

    public void setStatusId(LongFilter statusId) {
        this.statusId = statusId;
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
        final EquipmentCriteria that = (EquipmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idno, that.idno) &&
            Objects.equals(name, that.name) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(itemId, that.itemId) &&
            Objects.equals(statusId, that.statusId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idno, name, createDate, modifyDate, itemId, statusId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (idno != null ? "idno=" + idno + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (createDate != null ? "createDate=" + createDate + ", " : "") +
            (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
            (itemId != null ? "itemId=" + itemId + ", " : "") +
            (statusId != null ? "statusId=" + statusId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
