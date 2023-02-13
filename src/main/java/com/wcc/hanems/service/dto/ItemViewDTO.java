package com.wcc.hanems.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.wcc.hanems.domain.ItemView} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemViewDTO implements Serializable {

    private Long id;

    private String name;

    private String total;

    private String ready;

    private String working;

    private String canNot;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getReady() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getCanNot() {
        return canNot;
    }

    public void setCanNot(String canNot) {
        this.canNot = canNot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemViewDTO)) {
            return false;
        }

        ItemViewDTO itemViewDTO = (ItemViewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, itemViewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemViewDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", total='" + getTotal() + "'" +
            ", ready='" + getReady() + "'" +
            ", working='" + getWorking() + "'" +
            ", canNot='" + getCanNot() + "'" +
            "}";
    }
}
