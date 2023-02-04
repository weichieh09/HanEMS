package com.wcc.hanems.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.wcc.hanems.domain.Equipment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EquipmentDTO implements Serializable {

    private Long id;

    private String name;

    private Integer totalEle;

    private Integer rentedEle;

    private Integer currentEle;

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

    public Integer getTotalEle() {
        return totalEle;
    }

    public void setTotalEle(Integer totalEle) {
        this.totalEle = totalEle;
    }

    public Integer getRentedEle() {
        return rentedEle;
    }

    public void setRentedEle(Integer rentedEle) {
        this.rentedEle = rentedEle;
    }

    public Integer getCurrentEle() {
        return currentEle;
    }

    public void setCurrentEle(Integer currentEle) {
        this.currentEle = currentEle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EquipmentDTO)) {
            return false;
        }

        EquipmentDTO equipmentDTO = (EquipmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, equipmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", totalEle=" + getTotalEle() +
            ", rentedEle=" + getRentedEle() +
            ", currentEle=" + getCurrentEle() +
            "}";
    }
}
