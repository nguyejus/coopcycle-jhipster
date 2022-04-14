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
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "i_dcourse", nullable = false)
    private Integer iDcourse;

    @NotNull
    @Column(name = "iddelveryman", nullable = false)
    private Integer iddelveryman;

    @OneToMany(mappedBy = "course")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orderContents", "course", "customer", "cooperative" }, allowSetters = true)
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "courses" }, allowSetters = true)
    private DeliveryMan deliveryMan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Course id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDcourse() {
        return this.iDcourse;
    }

    public Course iDcourse(Integer iDcourse) {
        this.setiDcourse(iDcourse);
        return this;
    }

    public void setiDcourse(Integer iDcourse) {
        this.iDcourse = iDcourse;
    }

    public Integer getIddelveryman() {
        return this.iddelveryman;
    }

    public Course iddelveryman(Integer iddelveryman) {
        this.setIddelveryman(iddelveryman);
        return this;
    }

    public void setIddelveryman(Integer iddelveryman) {
        this.iddelveryman = iddelveryman;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        if (this.orders != null) {
            this.orders.forEach(i -> i.setCourse(null));
        }
        if (orders != null) {
            orders.forEach(i -> i.setCourse(this));
        }
        this.orders = orders;
    }

    public Course orders(Set<Order> orders) {
        this.setOrders(orders);
        return this;
    }

    public Course addOrder(Order order) {
        this.orders.add(order);
        order.setCourse(this);
        return this;
    }

    public Course removeOrder(Order order) {
        this.orders.remove(order);
        order.setCourse(null);
        return this;
    }

    public DeliveryMan getDeliveryMan() {
        return this.deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Course deliveryMan(DeliveryMan deliveryMan) {
        this.setDeliveryMan(deliveryMan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", iDcourse=" + getiDcourse() +
            ", iddelveryman=" + getIddelveryman() +
            "}";
    }
}
