package com.okta.spring.example.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A UserBilling.
 */
public class UserBilling implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String eventId;
    
    private String eventName;

    private String ticketId;

    private LocalDate billingDate;

    private String email;

    private BigDecimal discount;

    private BigDecimal price;

    private User user;

    private UserEc2Vivo user2vivo;

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

public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setUser(User user) {
        this.user = user;
    }

    public UserEc2Vivo getUser2vivo() {
        return user2vivo;
    }

    public UserBilling user2vivo(UserEc2Vivo userEc2Vivo) {
        this.user2vivo = userEc2Vivo;
        return this;
    }

    public void setUser2vivo(UserEc2Vivo userEc2Vivo) {
        this.user2vivo = userEc2Vivo;
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
