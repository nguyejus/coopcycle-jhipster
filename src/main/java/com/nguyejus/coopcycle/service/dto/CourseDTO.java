package com.nguyejus.coopcycle.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.nguyejus.coopcycle.domain.Course} entity.
 */
public class CourseDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer iDcourse;

    @NotNull
    private Integer iddelveryman;

    private DeliveryManDTO deliveryMan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDcourse() {
        return iDcourse;
    }

    public void setiDcourse(Integer iDcourse) {
        this.iDcourse = iDcourse;
    }

    public Integer getIddelveryman() {
        return iddelveryman;
    }

    public void setIddelveryman(Integer iddelveryman) {
        this.iddelveryman = iddelveryman;
    }

    public DeliveryManDTO getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryManDTO deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseDTO)) {
            return false;
        }

        CourseDTO courseDTO = (CourseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, courseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CourseDTO{" +
            "id=" + getId() +
            ", iDcourse=" + getiDcourse() +
            ", iddelveryman=" + getIddelveryman() +
            ", deliveryMan=" + getDeliveryMan() +
            "}";
    }
}
