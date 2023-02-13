package com.wcc.hanems.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A LendReturnRecord.
 */
@Entity
@Table(name = "lend_return_record")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LendReturnRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "lend_date", nullable = false)
    private Instant lendDate;

    @Column(name = "return_date")
    private Instant returnDate;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "modify_date")
    private Instant modifyDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "item", "status" }, allowSetters = true)
    private Equipment equipment;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Reason reason;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LendReturnRecord id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getLendDate() {
        return this.lendDate;
    }

    public LendReturnRecord lendDate(Instant lendDate) {
        this.setLendDate(lendDate);
        return this;
    }

    public void setLendDate(Instant lendDate) {
        this.lendDate = lendDate;
    }

    public Instant getReturnDate() {
        return this.returnDate;
    }

    public LendReturnRecord returnDate(Instant returnDate) {
        this.setReturnDate(returnDate);
        return this;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    public String getDescription() {
        return this.description;
    }

    public LendReturnRecord description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public LendReturnRecord createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return this.modifyDate;
    }

    public LendReturnRecord modifyDate(Instant modifyDate) {
        this.setModifyDate(modifyDate);
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public LendReturnRecord equipment(Equipment equipment) {
        this.setEquipment(equipment);
        return this;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LendReturnRecord person(Person person) {
        this.setPerson(person);
        return this;
    }

    public Reason getReason() {
        return this.reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public LendReturnRecord reason(Reason reason) {
        this.setReason(reason);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LendReturnRecord)) {
            return false;
        }
        return id != null && id.equals(((LendReturnRecord) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LendReturnRecord{" +
            "id=" + getId() +
            ", lendDate='" + getLendDate() + "'" +
            ", returnDate='" + getReturnDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
