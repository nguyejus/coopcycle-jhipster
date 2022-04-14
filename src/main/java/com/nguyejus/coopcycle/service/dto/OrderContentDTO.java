package com.nguyejus.coopcycle.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.nguyejus.coopcycle.domain.OrderContent} entity.
 */
public class OrderContentDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer iDproduct;

    @NotNull
    private Integer iDorder;

    private Integer quantityAsked;

    private Boolean productAvailable;

    private Set<ProductDTO> products = new HashSet<>();

    private OrderDTO order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDproduct() {
        return iDproduct;
    }

    public void setiDproduct(Integer iDproduct) {
        this.iDproduct = iDproduct;
    }

    public Integer getiDorder() {
        return iDorder;
    }

    public void setiDorder(Integer iDorder) {
        this.iDorder = iDorder;
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderContentDTO)) {
            return false;
        }

        OrderContentDTO orderContentDTO = (OrderContentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderContentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderContentDTO{" +
            "id=" + getId() +
            ", iDproduct=" + getiDproduct() +
            ", iDorder=" + getiDorder() +
            ", quantityAsked=" + getQuantityAsked() +
            ", productAvailable='" + getProductAvailable() + "'" +
            ", products=" + getProducts() +
            ", order=" + getOrder() +
            "}";
    }
}
