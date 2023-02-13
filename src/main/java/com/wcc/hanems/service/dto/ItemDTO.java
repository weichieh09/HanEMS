package com.wcc.hanems.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.wcc.hanems.domain.Item} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @Lob
    private String description;

    private Instant createDate;

    private Instant modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemDTO)) {
            return false;
        }

        ItemDTO itemDTO = (ItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, itemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
