package com.wcc.hanems.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.wcc.hanems.domain.BlackList} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BlackListDTO implements Serializable {

    private Long id;

    @Lob
    private String description;

    @NotNull
    private Instant blackDate;

    private Instant createDate;

    private Instant modifyDate;

    private PersonDTO person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getBlackDate() {
        return blackDate;
    }

    public void setBlackDate(Instant blackDate) {
        this.blackDate = blackDate;
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

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackListDTO)) {
            return false;
        }

        BlackListDTO blackListDTO = (BlackListDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, blackListDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BlackListDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", blackDate='" + getBlackDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", person=" + getPerson() +
            "}";
    }
}
