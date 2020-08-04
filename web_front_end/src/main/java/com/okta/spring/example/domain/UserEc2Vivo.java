package com.okta.spring.example.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UserEc2Vivo.
 */
public class UserEc2Vivo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String lastName;

    private String phone;

    private String email;

    private String userName;

    private String userPassword;

    private String loginSource;

    private LocalDate registerDate;
    
    private Boolean isCreation;
    
    public UserEc2Vivo() {
    	this.isCreation = false;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserEc2Vivo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEc2Vivo lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public UserEc2Vivo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public UserEc2Vivo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public UserEc2Vivo userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserEc2Vivo userPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public UserEc2Vivo loginSource(String loginSource) {
        this.loginSource = loginSource;
        return this;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public UserEc2Vivo registerDate(LocalDate registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public Boolean getIsCreation() {
		return isCreation;
	}

	public void setIsCreation(Boolean isCreation) {
		this.isCreation = isCreation;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEc2Vivo)) {
            return false;
        }
        return id != null && id.equals(((UserEc2Vivo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserEc2Vivo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", userName='" + getUserName() + "'" +
            ", userPassword='" + getUserPassword() + "'" +
            ", loginSource='" + getLoginSource() + "'" +
            ", registerDate='" + getRegisterDate() + "'" +
            "}";
    }
}
