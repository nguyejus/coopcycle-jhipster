package com.nguyejus.coopcycle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Menu.
 */
@Entity
@Table(name = "menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "i_dmenu", nullable = false)
    private Integer iDmenu;

    @NotNull
    @Column(name = "i_dcooperative", nullable = false)
    private Integer iDcooperative;

    @Column(name = "lastupdate")
    private ZonedDateTime lastupdate;

    @OneToMany(mappedBy = "menu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "menu", "ordercontents" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "menus", "orders" }, allowSetters = true)
    private Cooperative cooperative;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Menu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDmenu() {
        return this.iDmenu;
    }

    public Menu iDmenu(Integer iDmenu) {
        this.setiDmenu(iDmenu);
        return this;
    }

    public void setiDmenu(Integer iDmenu) {
        this.iDmenu = iDmenu;
    }

    public Integer getiDcooperative() {
        return this.iDcooperative;
    }

    public Menu iDcooperative(Integer iDcooperative) {
        this.setiDcooperative(iDcooperative);
        return this;
    }

    public void setiDcooperative(Integer iDcooperative) {
        this.iDcooperative = iDcooperative;
    }

    public ZonedDateTime getLastupdate() {
        return this.lastupdate;
    }

    public Menu lastupdate(ZonedDateTime lastupdate) {
        this.setLastupdate(lastupdate);
        return this;
    }

    public void setLastupdate(ZonedDateTime lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.setMenu(null));
        }
        if (products != null) {
            products.forEach(i -> i.setMenu(this));
        }
        this.products = products;
    }

    public Menu products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public Menu addProduct(Product product) {
        this.products.add(product);
        product.setMenu(this);
        return this;
    }

    public Menu removeProduct(Product product) {
        this.products.remove(product);
        product.setMenu(null);
        return this;
    }

    public Cooperative getCooperative() {
        return this.cooperative;
    }

    public void setCooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
    }

    public Menu cooperative(Cooperative cooperative) {
        this.setCooperative(cooperative);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        return id != null && id.equals(((Menu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", iDmenu=" + getiDmenu() +
            ", iDcooperative=" + getiDcooperative() +
            ", lastupdate='" + getLastupdate() + "'" +
            "}";
    }
}
