package com.ec.integration.streamingvideoprovider.message.xmldto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "password_packages")
@XmlAccessorType(XmlAccessType.FIELD)
public class PasswordPackages implements Serializable {

    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "free_password_package")
    private List<FreePasswordPackageMsg> freePasswordPackages;
    
    @XmlElement(name="password_package")
    private List<PasswordPackageMsg> passwordPackages;

    public List<FreePasswordPackageMsg> getFreePasswordPackages() {
        return freePasswordPackages;
    }

    public void setFreePasswordPackages(List<FreePasswordPackageMsg> freePasswordPackages) {
        this.freePasswordPackages = freePasswordPackages;
    }

    public List<PasswordPackageMsg> getPasswordPackages() {
        return passwordPackages;
    }

    public void setPasswordPackages(List<PasswordPackageMsg> passwordPackages) {
        this.passwordPackages = passwordPackages;
    }

    
}