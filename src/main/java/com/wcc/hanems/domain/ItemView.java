package com.wcc.hanems.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A ItemView.
 */
@Entity
@Table(name = "item_view")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total")
    private String total;

    @Column(name = "ready")
    private String ready;

    @Column(name = "working")
    private String working;

    @Column(name = "can_not")
    private String canNot;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ItemView id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ItemView name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return this.total;
    }

    public ItemView total(String total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getReady() {
        return this.ready;
    }

    public ItemView ready(String ready) {
        this.setReady(ready);
        return this;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }

    public String getWorking() {
        return this.working;
    }

    public ItemView working(String working) {
        this.setWorking(working);
        return this;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getCanNot() {
        return this.canNot;
    }

    public ItemView canNot(String canNot) {
        this.setCanNot(canNot);
        return this;
    }

    public void setCanNot(String canNot) {
        this.canNot = canNot;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemView)) {
            return false;
        }
        return id != null && id.equals(((ItemView) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemView{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", total='" + getTotal() + "'" +
            ", ready='" + getReady() + "'" +
            ", working='" + getWorking() + "'" +
            ", canNot='" + getCanNot() + "'" +
            "}";
    }
}
