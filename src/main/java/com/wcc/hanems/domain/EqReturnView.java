package com.wcc.hanems.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A EqReturnView.
 */
@Entity
@Table(name = "eq_return_view")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EqReturnView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "idno")
    private String idno;

    @Column(name = "name")
    private String name;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "person_id")
    private Long personId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EqReturnView id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdno() {
        return this.idno;
    }

    public EqReturnView idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return this.name;
    }

    public EqReturnView name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public EqReturnView itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getStatusId() {
        return this.statusId;
    }

    public EqReturnView statusId(Long statusId) {
        this.setStatusId(statusId);
        return this;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getPersonId() {
        return this.personId;
    }

    public EqReturnView personId(Long personId) {
        this.setPersonId(personId);
        return this;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EqReturnView)) {
            return false;
        }
        return id != null && id.equals(((EqReturnView) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EqReturnView{" +
            "id=" + getId() +
            ", idno='" + getIdno() + "'" +
            ", name='" + getName() + "'" +
            ", itemId=" + getItemId() +
            ", statusId=" + getStatusId() +
            ", personId=" + getPersonId() +
            "}";
    }
}
