package com.wcc.hanems.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Equipment.
 */
@Entity
@Table(name = "equipment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_ele")
    private Integer totalEle;

    @Column(name = "rented_ele")
    private Integer rentedEle;

    @Column(name = "current_ele")
    private Integer currentEle;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Equipment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Equipment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalEle() {
        return this.totalEle;
    }

    public Equipment totalEle(Integer totalEle) {
        this.setTotalEle(totalEle);
        return this;
    }

    public void setTotalEle(Integer totalEle) {
        this.totalEle = totalEle;
    }

    public Integer getRentedEle() {
        return this.rentedEle;
    }

    public Equipment rentedEle(Integer rentedEle) {
        this.setRentedEle(rentedEle);
        return this;
    }

    public void setRentedEle(Integer rentedEle) {
        this.rentedEle = rentedEle;
    }

    public Integer getCurrentEle() {
        return this.currentEle;
    }

    public Equipment currentEle(Integer currentEle) {
        this.setCurrentEle(currentEle);
        return this;
    }

    public void setCurrentEle(Integer currentEle) {
        this.currentEle = currentEle;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Equipment)) {
            return false;
        }
        return id != null && id.equals(((Equipment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Equipment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", totalEle=" + getTotalEle() +
            ", rentedEle=" + getRentedEle() +
            ", currentEle=" + getCurrentEle() +
            "}";
    }
}
