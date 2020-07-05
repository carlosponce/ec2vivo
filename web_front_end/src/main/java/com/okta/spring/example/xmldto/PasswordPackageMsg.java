package com.okta.spring.example.xmldto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "password_package")
@XmlAccessorType(XmlAccessType.FIELD)
public class PasswordPackageMsg implements Serializable {
    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ref_no")
    private String refno;

    @XmlElement(name="name")
    private String name;


    @XmlElementWrapper(name="groups")
    @XmlElement(name="group")
    private List<GroupMsg> groups;

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupMsg> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupMsg> groups) {
        this.groups = groups;
    }

    
}