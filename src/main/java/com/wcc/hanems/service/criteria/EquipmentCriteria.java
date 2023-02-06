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

    private IntegerFilter eqItemId;

    private StringFilter name;

    private StringFilter description;

    private InstantFilter createDate;

    private InstantFilter modifyDate;

    private Boolean distinct;

    public EquipmentCriteria() {}

    public EquipmentCriteria(EquipmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.eqItemId = other.eqItemId == null ? null : other.eqItemId.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.createDate = other.createDate == null ? null : other.createDate.copy();
        this.modifyDate = other.modifyDate == null ? null : other.modifyDate.copy();
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

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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
        final EquipmentCriteria that = (EquipmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(eqItemId, that.eqItemId) &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eqItemId, name, description, createDate, modifyDate, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (eqItemId != null ? "eqItemId=" + eqItemId + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (createDate != null ? "createDate=" + createDate + ", " : "") +
            (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
