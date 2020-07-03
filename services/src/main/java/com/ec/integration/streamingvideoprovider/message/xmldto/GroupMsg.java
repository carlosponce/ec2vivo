package com.ec.integration.streamingvideoprovider.message.xmldto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupMsg implements Serializable {
    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "order_no")
    private String orderno;
    @XmlElement(name="count")
    private Long count;
    @XmlElement(name="ref_no")
    private String refno;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    
}