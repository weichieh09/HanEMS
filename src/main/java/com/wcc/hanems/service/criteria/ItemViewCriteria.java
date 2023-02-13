package com.wcc.hanems.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.wcc.hanems.domain.ItemView} entity. This class is used
 * in {@link com.wcc.hanems.web.rest.ItemViewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /item-views?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemViewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter total;

    private StringFilter ready;

    private StringFilter working;

    private StringFilter canNot;

    private Boolean distinct;

    public ItemViewCriteria() {}

    public ItemViewCriteria(ItemViewCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.ready = other.ready == null ? null : other.ready.copy();
        this.working = other.working == null ? null : other.working.copy();
        this.canNot = other.canNot == null ? null : other.canNot.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ItemViewCriteria copy() {
        return new ItemViewCriteria(this);
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

    public StringFilter getTotal() {
        return total;
    }

    public StringFilter total() {
        if (total == null) {
            total = new StringFilter();
        }
        return total;
    }

    public void setTotal(StringFilter total) {
        this.total = total;
    }

    public StringFilter getReady() {
        return ready;
    }

    public StringFilter ready() {
        if (ready == null) {
            ready = new StringFilter();
        }
        return ready;
    }

    public void setReady(StringFilter ready) {
        this.ready = ready;
    }

    public StringFilter getWorking() {
        return working;
    }

    public StringFilter working() {
        if (working == null) {
            working = new StringFilter();
        }
        return working;
    }

    public void setWorking(StringFilter working) {
        this.working = working;
    }

    public StringFilter getCanNot() {
        return canNot;
    }

    public StringFilter canNot() {
        if (canNot == null) {
            canNot = new StringFilter();
        }
        return canNot;
    }

    public void setCanNot(StringFilter canNot) {
        this.canNot = canNot;
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
        final ItemViewCriteria that = (ItemViewCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(total, that.total) &&
            Objects.equals(ready, that.ready) &&
            Objects.equals(working, that.working) &&
            Objects.equals(canNot, that.canNot) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, total, ready, working, canNot, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemViewCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (ready != null ? "ready=" + ready + ", " : "") +
            (working != null ? "working=" + working + ", " : "") +
            (canNot != null ? "canNot=" + canNot + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
