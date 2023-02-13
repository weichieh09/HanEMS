package com.wcc.hanems.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.wcc.hanems.domain.LendReturnRecord} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LendReturnRecordDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant lendDate;

    private Instant returnDate;

    @Lob
    private String description;

    private Instant createDate;

    private Instant modifyDate;

    private EquipmentDTO equipment;

    private PersonDTO person;

    private ReasonDTO reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getLendDate() {
        return lendDate;
    }

    public void setLendDate(Instant lendDate) {
        this.lendDate = lendDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public EquipmentDTO getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentDTO equipment) {
        this.equipment = equipment;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public ReasonDTO getReason() {
        return reason;
    }

    public void setReason(ReasonDTO reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LendReturnRecordDTO)) {
            return false;
        }

        LendReturnRecordDTO lendReturnRecordDTO = (LendReturnRecordDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, lendReturnRecordDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LendReturnRecordDTO{" +
            "id=" + getId() +
            ", lendDate='" + getLendDate() + "'" +
            ", returnDate='" + getReturnDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", equipment=" + getEquipment() +
            ", person=" + getPerson() +
            ", reason=" + getReason() +
            "}";
    }
}
