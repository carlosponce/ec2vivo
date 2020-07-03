package com.ec.integration.streamingvideoprovider.message.xmldto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.io.Serializable;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseMsg implements Serializable {

    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "result")
    private String result;

    @XmlElement(name="count")
    private String count;

    @XmlElement(name="code")
    private String code;

    @XmlElement(name="message")
    private String message;

    @XmlElement(name="class")
    private String classStr;

    @XmlElement(name="timestamp")
    private String timestamp;

    @XmlElement(name="ref_no")
    private String refno;
    
    @XmlElementWrapper(name="video_list")
    @XmlElement(name="video")
    private List<Video> videoList;

    @XmlElementWrapper(name="groups")
    @XmlElement(name="group")
    private List<GroupMsg> groups;

    @XmlElementWrapper(name="tickets")
    @XmlElement(name="ticket")
    private List<TicketMsg> tickets;

    @XmlElement(name="password_packages")
    private PasswordPackages passwordPackages;

    @XmlElementWrapper(name="passwords")
    @XmlElement(name="password")
    private List<String> passwords;

   
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public List<GroupMsg> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupMsg> groups) {
        this.groups = groups;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClassStr() {
        return classStr;
    }

    public void setClassStr(String classStr) {
        this.classStr = classStr;
    }

    public List<TicketMsg> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketMsg> tickets) {
        this.tickets = tickets;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public PasswordPackages getPasswordPackages() {
        return passwordPackages;
    }

    public void setPasswordPackages(PasswordPackages passwordPackages) {
        this.passwordPackages = passwordPackages;
    }

    public List<String> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
    }

    
}