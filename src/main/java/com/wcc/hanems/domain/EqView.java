package com.wcc.hanems.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

/**
 * A EqView.
 */
@Entity
@Table(name = "eq_view")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EqView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "eq_item_id")
    private Integer eqItemId;

    @Column(name = "eq_item_name")
    private String eqItemName;

    @Column(name = "eq_name")
    private String eqName;

    @Column(name = "eq_description")
    private String eqDescription;

    @Column(name = "eq_create_date")
    private Instant eqCreateDate;

    @Column(name = "eq_modify_date")
    private Instant eqModifyDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EqView id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEqItemId() {
        return this.eqItemId;
    }

    public EqView eqItemId(Integer eqItemId) {
        this.setEqItemId(eqItemId);
        return this;
    }

    public void setEqItemId(Integer eqItemId) {
        this.eqItemId = eqItemId;
    }

    public String getEqItemName() {
        return this.eqItemName;
    }

    public EqView eqItemName(String eqItemName) {
        this.setEqItemName(eqItemName);
        return this;
    }

    public void setEqItemName(String eqItemName) {
        this.eqItemName = eqItemName;
    }

    public String getEqName() {
        return this.eqName;
    }

    public EqView eqName(String eqName) {
        this.setEqName(eqName);
        return this;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getEqDescription() {
        return this.eqDescription;
    }

    public EqView eqDescription(String eqDescription) {
        this.setEqDescription(eqDescription);
        return this;
    }

    public void setEqDescription(String eqDescription) {
        this.eqDescription = eqDescription;
    }

    public Instant getEqCreateDate() {
        return this.eqCreateDate;
    }

    public EqView eqCreateDate(Instant eqCreateDate) {
        this.setEqCreateDate(eqCreateDate);
        return this;
    }

    public void setEqCreateDate(Instant eqCreateDate) {
        this.eqCreateDate = eqCreateDate;
    }

    public Instant getEqModifyDate() {
        return this.eqModifyDate;
    }

    public EqView eqModifyDate(Instant eqModifyDate) {
        this.setEqModifyDate(eqModifyDate);
        return this;
    }

    public void setEqModifyDate(Instant eqModifyDate) {
        this.eqModifyDate = eqModifyDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EqView)) {
            return false;
        }
        return id != null && id.equals(((EqView) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EqView{" +
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
