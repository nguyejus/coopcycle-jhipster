package com.nguyejus.coopcycle.service.dto;

import com.nguyejus.coopcycle.domain.enumeration.State;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.nguyejus.coopcycle.domain.Order} entity.
 */
public class OrderDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer iDorder;

    @NotNull
    private Integer iDcooperative;

    @NotNull
    private Integer iDcustomer;

    @NotNull
    private Integer iDcourse;

    @NotNull
    private Integer iDproduct;

    @Min(value = 3)
    @Max(value = 300)
    private Integer totalPrice;

    private ZonedDateTime date;

    private State state;

    private Integer quantityAsked;

    private Boolean productAvailable;

    private Set<ProductDTO> products = new HashSet<>();

    private CourseDTO course;

    private CustomerDTO customer;

    private CooperativeDTO cooperative;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDorder() {
        return iDorder;
    }

    public void setiDorder(Integer iDorder) {
        this.iDorder = iDorder;
    }

    public Integer getiDcooperative() {
        return iDcooperative;
    }

    public void setiDcooperative(Integer iDcooperative) {
        this.iDcooperative = iDcooperative;
    }

    public Integer getiDcustomer() {
        return iDcustomer;
    }

    public void setiDcustomer(Integer iDcustomer) {
        this.iDcustomer = iDcustomer;
    }

    public Integer getiDcourse() {
        return iDcourse;
    }

    public void setiDcourse(Integer iDcourse) {
        this.iDcourse = iDcourse;
    }

    public Integer getiDproduct() {
        return iDproduct;
    }

    public void setiDproduct(Integer iDproduct) {
        this.iDproduct = iDproduct;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getQuantityAsked() {
        return quantityAsked;
    }

    public void setQuantityAsked(Integer quantityAsked) {
        this.quantityAsked = quantityAsked;
    }

    public Boolean getProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(Boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public CooperativeDTO getCooperative() {
        return cooperative;
    }

    public void setCooperative(CooperativeDTO cooperative) {
        this.cooperative = cooperative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        OrderDTO orderDTO = (OrderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", iDorder=" + getiDorder() +
            ", iDcooperative=" + getiDcooperative() +
            ", iDcustomer=" + getiDcustomer() +
            ", iDcourse=" + getiDcourse() +
            ", iDproduct=" + getiDproduct() +
            ", totalPrice=" + getTotalPrice() +
            ", date='" + getDate() + "'" +
            ", state='" + getState() + "'" +
            ", quantityAsked=" + getQuantityAsked() +
            ", productAvailable='" + getProductAvailable() + "'" +
            ", products=" + getProducts() +
            ", course=" + getCourse() +
            ", customer=" + getCustomer() +
            ", cooperative=" + getCooperative() +
            "}";
    }
}
