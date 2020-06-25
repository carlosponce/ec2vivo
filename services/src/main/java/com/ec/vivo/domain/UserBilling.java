package com.ec.vivo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A UserBilling.
 */
@Document(collection = "user_billing")
public class UserBilling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("event_id")
    private String eventId;

    @Field("ticket_id")
    private String ticketId;

    @Field("billing_date")
    private LocalDate billingDate;

    @Field("email")
    private String email;

    @Field("discount")
    private BigDecimal discount;

    @Field("price")
    private BigDecimal price;

    @DBRef
    @Field("user")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public UserBilling eventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public UserBilling ticketId(String ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public UserBilling billingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
        return this;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public String getEmail() {
        return email;
    }

    public UserBilling email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public UserBilling discount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserBilling price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public UserBilling user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserBilling)) {
            return false;
        }
        return id != null && id.equals(((UserBilling) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserBilling{" +
            "id=" + getId() +
            ", eventId='" + getEventId() + "'" +
            ", ticketId='" + getTicketId() + "'" +
            ", billingDate='" + getBillingDate() + "'" +
            ", email='" + getEmail() + "'" +
            ", discount=" + getDiscount() +
            ", price=" + getPrice() +
            "}";
    }
}
