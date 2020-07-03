package com.ec.integration.streamingvideoprovider.message.xmldto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "free_password_package")
@XmlAccessorType(XmlAccessType.FIELD)
public class FreePasswordPackageMsg implements Serializable {
    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ref_no")
    private String refno;

    @XmlElement(name="name")
    private String name;

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

    
}