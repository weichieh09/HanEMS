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

    private StringFilter name;

    private IntegerFilter totalEle;

    private IntegerFilter rentedEle;

    private IntegerFilter currentEle;

    private Boolean distinct;

    public EquipmentCriteria() {}

    public EquipmentCriteria(EquipmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.totalEle = other.totalEle == null ? null : other.totalEle.copy();
        this.rentedEle = other.rentedEle == null ? null : other.rentedEle.copy();
        this.currentEle = other.currentEle == null ? null : other.currentEle.copy();
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

    public IntegerFilter getTotalEle() {
        return totalEle;
    }

    public IntegerFilter totalEle() {
        if (totalEle == null) {
            totalEle = new IntegerFilter();
        }
        return totalEle;
    }

    public void setTotalEle(IntegerFilter totalEle) {
        this.totalEle = totalEle;
    }

    public IntegerFilter getRentedEle() {
        return rentedEle;
    }

    public IntegerFilter rentedEle() {
        if (rentedEle == null) {
            rentedEle = new IntegerFilter();
        }
        return rentedEle;
    }

    public void setRentedEle(IntegerFilter rentedEle) {
        this.rentedEle = rentedEle;
    }

    public IntegerFilter getCurrentEle() {
        return currentEle;
    }

    public IntegerFilter currentEle() {
        if (currentEle == null) {
            currentEle = new IntegerFilter();
        }
        return currentEle;
    }

    public void setCurrentEle(IntegerFilter currentEle) {
        this.currentEle = currentEle;
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
            Objects.equals(name, that.name) &&
            Objects.equals(totalEle, that.totalEle) &&
            Objects.equals(rentedEle, that.rentedEle) &&
            Objects.equals(currentEle, that.currentEle) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalEle, rentedEle, currentEle, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (totalEle != null ? "totalEle=" + totalEle + ", " : "") +
            (rentedEle != null ? "rentedEle=" + rentedEle + ", " : "") +
            (currentEle != null ? "currentEle=" + currentEle + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
