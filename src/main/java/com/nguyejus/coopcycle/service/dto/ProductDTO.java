package com.nguyejus.coopcycle.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.nguyejus.coopcycle.domain.Product} entity.
 */
public class ProductDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer iDproduct;

    @NotNull
    private Integer iDmenu;

    private String name;

    private Float price;

    @Min(value = 0)
    private Integer disponibility;

    private MenuDTO menu;

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

    public Integer getiDmenu() {
        return iDmenu;
    }

    public void setiDmenu(Integer iDmenu) {
        this.iDmenu = iDmenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(Integer disponibility) {
        this.disponibility = disponibility;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", iDproduct=" + getiDproduct() +
            ", iDmenu=" + getiDmenu() +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            ", disponibility=" + getDisponibility() +
            ", menu=" + getMenu() +
            "}";
    }
}
