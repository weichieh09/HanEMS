package com.wcc.hanems.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.wcc.hanems.domain.EqReturnView} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EqReturnViewDTO implements Serializable {

    private Long id;

    private String idno;

    private String name;

    private Long itemId;

    private Long statusId;

    private Long personId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EqReturnViewDTO)) {
            return false;
        }

        EqReturnViewDTO eqReturnViewDTO = (EqReturnViewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, eqReturnViewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EqReturnViewDTO{" +
            "id=" + getId() +
            ", idno='" + getIdno() + "'" +
            ", name='" + getName() + "'" +
            ", itemId=" + getItemId() +
            ", statusId=" + getStatusId() +
            ", personId=" + getPersonId() +
            "}";
    }
}
