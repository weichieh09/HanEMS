package com.wcc.hanems.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BlackList.
 */
@Entity
@Table(name = "black_list")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BlackList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "black_date", nullable = false)
    private Instant blackDate;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "modify_date")
    private Instant modifyDate;

    @ManyToOne
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BlackList id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public BlackList description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getBlackDate() {
        return this.blackDate;
    }

    public BlackList blackDate(Instant blackDate) {
        this.setBlackDate(blackDate);
        return this;
    }

    public void setBlackDate(Instant blackDate) {
        this.blackDate = blackDate;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BlackList createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return this.modifyDate;
    }

    public BlackList modifyDate(Instant modifyDate) {
        this.setModifyDate(modifyDate);
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BlackList person(Person person) {
        this.setPerson(person);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackList)) {
            return false;
        }
        return id != null && id.equals(((BlackList) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BlackList{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", blackDate='" + getBlackDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
