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
 * A Cooperative.
 */
@Entity
@Table(name = "cooperative")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cooperative implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "i_d", nullable = false)
    private Integer iD;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Size(min = 10, max = 10)
    @Column(name = "telephone", length = 10)
    private String telephone;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "cooperative")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "products", "cooperative" }, allowSetters = true)
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "cooperative")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orderContents", "course", "customer", "cooperative" }, allowSetters = true)
    private Set<Order> orders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cooperative id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiD() {
        return this.iD;
    }

    public Cooperative iD(Integer iD) {
        this.setiD(iD);
        return this;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }

    public String getName() {
        return this.name;
    }

    public Cooperative name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Cooperative surname(String surname) {
        this.setSurname(surname);
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Cooperative telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return this.address;
    }

    public Cooperative address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Menu> getMenus() {
        return this.menus;
    }

    public void setMenus(Set<Menu> menus) {
        if (this.menus != null) {
            this.menus.forEach(i -> i.setCooperative(null));
        }
        if (menus != null) {
            menus.forEach(i -> i.setCooperative(this));
        }
        this.menus = menus;
    }

    public Cooperative menus(Set<Menu> menus) {
        this.setMenus(menus);
        return this;
    }

    public Cooperative addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setCooperative(this);
        return this;
    }

    public Cooperative removeMenu(Menu menu) {
        this.menus.remove(menu);
        menu.setCooperative(null);
        return this;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        if (this.orders != null) {
            this.orders.forEach(i -> i.setCooperative(null));
        }
        if (orders != null) {
            orders.forEach(i -> i.setCooperative(this));
        }
        this.orders = orders;
    }

    public Cooperative orders(Set<Order> orders) {
        this.setOrders(orders);
        return this;
    }

    public Cooperative addOrder(Order order) {
        this.orders.add(order);
        order.setCooperative(this);
        return this;
    }

    public Cooperative removeOrder(Order order) {
        this.orders.remove(order);
        order.setCooperative(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cooperative)) {
            return false;
        }
        return id != null && id.equals(((Cooperative) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cooperative{" +
            "id=" + getId() +
            ", iD=" + getiD() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
