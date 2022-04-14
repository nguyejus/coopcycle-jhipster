package com.nguyejus.coopcycle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OrderContent.
 */
@Entity
@Table(name = "order_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "i_dproduct", nullable = false)
    private Integer iDproduct;

    @NotNull
    @Column(name = "i_dorder", nullable = false)
    private Integer iDorder;

    @Column(name = "quantity_asked")
    private Integer quantityAsked;

    @Column(name = "product_available")
    private Boolean productAvailable;

    @ManyToMany
    @NotNull
    @JoinTable(
        name = "rel_order_content__product",
        joinColumns = @JoinColumn(name = "order_content_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "menu", "ordercontents" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "orderContents", "course", "customer", "cooperative" }, allowSetters = true)
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderContent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDproduct() {
        return this.iDproduct;
    }

    public OrderContent iDproduct(Integer iDproduct) {
        this.setiDproduct(iDproduct);
        return this;
    }

    public void setiDproduct(Integer iDproduct) {
        this.iDproduct = iDproduct;
    }

    public Integer getiDorder() {
        return this.iDorder;
    }

    public OrderContent iDorder(Integer iDorder) {
        this.setiDorder(iDorder);
        return this;
    }

    public void setiDorder(Integer iDorder) {
        this.iDorder = iDorder;
    }

    public Integer getQuantityAsked() {
        return this.quantityAsked;
    }

    public OrderContent quantityAsked(Integer quantityAsked) {
        this.setQuantityAsked(quantityAsked);
        return this;
    }

    public void setQuantityAsked(Integer quantityAsked) {
        this.quantityAsked = quantityAsked;
    }

    public Boolean getProductAvailable() {
        return this.productAvailable;
    }

    public OrderContent productAvailable(Boolean productAvailable) {
        this.setProductAvailable(productAvailable);
        return this;
    }

    public void setProductAvailable(Boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public OrderContent products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public OrderContent addProduct(Product product) {
        this.products.add(product);
        product.getOrdercontents().add(this);
        return this;
    }

    public OrderContent removeProduct(Product product) {
        this.products.remove(product);
        product.getOrdercontents().remove(this);
        return this;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderContent order(Order order) {
        this.setOrder(order);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderContent)) {
            return false;
        }
        return id != null && id.equals(((OrderContent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderContent{" +
            "id=" + getId() +
            ", iDproduct=" + getiDproduct() +
            ", iDorder=" + getiDorder() +
            ", quantityAsked=" + getQuantityAsked() +
            ", productAvailable='" + getProductAvailable() + "'" +
            "}";
    }
}
