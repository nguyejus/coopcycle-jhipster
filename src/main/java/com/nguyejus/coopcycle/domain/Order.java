package com.nguyejus.coopcycle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nguyejus.coopcycle.domain.enumeration.State;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "i_dorder", nullable = false)
    private Integer iDorder;

    @NotNull
    @Column(name = "i_dcooperative", nullable = false)
    private Integer iDcooperative;

    @NotNull
    @Column(name = "i_dcustomer", nullable = false)
    private Integer iDcustomer;

    @NotNull
    @Column(name = "i_dcourse", nullable = false)
    private Integer iDcourse;

    @Min(value = 3)
    @Max(value = 300)
    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "date")
    private ZonedDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @OneToMany(mappedBy = "order")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "products", "order" }, allowSetters = true)
    private Set<OrderContent> orderContents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "orders", "deliveryMan" }, allowSetters = true)
    private Course course;

    @ManyToOne
    @JsonIgnoreProperties(value = { "orders" }, allowSetters = true)
    private Customer customer;

    @ManyToOne
    @JsonIgnoreProperties(value = { "menus", "orders" }, allowSetters = true)
    private Cooperative cooperative;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Order id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDorder() {
        return this.iDorder;
    }

    public Order iDorder(Integer iDorder) {
        this.setiDorder(iDorder);
        return this;
    }

    public void setiDorder(Integer iDorder) {
        this.iDorder = iDorder;
    }

    public Integer getiDcooperative() {
        return this.iDcooperative;
    }

    public Order iDcooperative(Integer iDcooperative) {
        this.setiDcooperative(iDcooperative);
        return this;
    }

    public void setiDcooperative(Integer iDcooperative) {
        this.iDcooperative = iDcooperative;
    }

    public Integer getiDcustomer() {
        return this.iDcustomer;
    }

    public Order iDcustomer(Integer iDcustomer) {
        this.setiDcustomer(iDcustomer);
        return this;
    }

    public void setiDcustomer(Integer iDcustomer) {
        this.iDcustomer = iDcustomer;
    }

    public Integer getiDcourse() {
        return this.iDcourse;
    }

    public Order iDcourse(Integer iDcourse) {
        this.setiDcourse(iDcourse);
        return this;
    }

    public void setiDcourse(Integer iDcourse) {
        this.iDcourse = iDcourse;
    }

    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public Order totalPrice(Integer totalPrice) {
        this.setTotalPrice(totalPrice);
        return this;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ZonedDateTime getDate() {
        return this.date;
    }

    public Order date(ZonedDateTime date) {
        this.setDate(date);
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public State getState() {
        return this.state;
    }

    public Order state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<OrderContent> getOrderContents() {
        return this.orderContents;
    }

    public void setOrderContents(Set<OrderContent> orderContents) {
        if (this.orderContents != null) {
            this.orderContents.forEach(i -> i.setOrder(null));
        }
        if (orderContents != null) {
            orderContents.forEach(i -> i.setOrder(this));
        }
        this.orderContents = orderContents;
    }

    public Order orderContents(Set<OrderContent> orderContents) {
        this.setOrderContents(orderContents);
        return this;
    }

    public Order addOrderContent(OrderContent orderContent) {
        this.orderContents.add(orderContent);
        orderContent.setOrder(this);
        return this;
    }

    public Order removeOrderContent(OrderContent orderContent) {
        this.orderContents.remove(orderContent);
        orderContent.setOrder(null);
        return this;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Order course(Course course) {
        this.setCourse(course);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Cooperative getCooperative() {
        return this.cooperative;
    }

    public void setCooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
    }

    public Order cooperative(Cooperative cooperative) {
        this.setCooperative(cooperative);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", iDorder=" + getiDorder() +
            ", iDcooperative=" + getiDcooperative() +
            ", iDcustomer=" + getiDcustomer() +
            ", iDcourse=" + getiDcourse() +
            ", totalPrice=" + getTotalPrice() +
            ", date='" + getDate() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
