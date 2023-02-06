package com.wcc.hanems.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.wcc.hanems.domain.EqView} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EqViewDTO implements Serializable {

    private Long id;

    private Integer eqItemId;

    private String eqItemName;

    private String eqName;

    private String eqDescription;

    private Instant eqCreateDate;

    private Instant eqModifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEqItemId() {
        return eqItemId;
    }

    public void setEqItemId(Integer eqItemId) {
        this.eqItemId = eqItemId;
    }

    public String getEqItemName() {
        return eqItemName;
    }

    public void setEqItemName(String eqItemName) {
        this.eqItemName = eqItemName;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getEqDescription() {
        return eqDescription;
    }

    public void setEqDescription(String eqDescription) {
        this.eqDescription = eqDescription;
    }

    public Instant getEqCreateDate() {
        return eqCreateDate;
    }

    public void setEqCreateDate(Instant eqCreateDate) {
        this.eqCreateDate = eqCreateDate;
    }

    public Instant getEqModifyDate() {
        return eqModifyDate;
    }

    public void setEqModifyDate(Instant eqModifyDate) {
        this.eqModifyDate = eqModifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EqViewDTO)) {
            return false;
        }

        EqViewDTO eqViewDTO = (EqViewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, eqViewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EqViewDTO{" +
            "id=" + getId() +
            ", eqItemId=" + getEqItemId() +
            ", eqItemName='" + getEqItemName() + "'" +
            ", eqName='" + getEqName() + "'" +
            ", eqDescription='" + getEqDescription() + "'" +
            ", eqCreateDate='" + getEqCreateDate() + "'" +
            ", eqModifyDate='" + getEqModifyDate() + "'" +
            "}";
    }
}
