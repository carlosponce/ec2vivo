package com.okta.spring.example.xmldto;

import java.io.Serializable;

public class VideoPasswordPayload implements Serializable {
    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;
    
    private String videoRef;
    private String passwordRef;
    private String userRef;

    public String getVideoRef() {
        return videoRef;
    }

    public void setVideoRef(String videoRef) {
        this.videoRef = videoRef;
    }

    public String getPasswordRef() {
        return passwordRef;
    }

    public void setPasswordRef(String passwordRef) {
        this.passwordRef = passwordRef;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    
}