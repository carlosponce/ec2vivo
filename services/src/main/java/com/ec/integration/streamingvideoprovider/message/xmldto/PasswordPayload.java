package com.ec.integration.streamingvideoprovider.message.xmldto;

import java.io.Serializable;

public class PasswordPayload implements Serializable {
    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;
    private String groupRef;
    private String passwordType;
    private Long countPassword;
    private String expiryDate;
    private Long totalAllowedViews;
    private Long totalAllowedViewsPerVideo;

    public PasswordPayload(){
        countPassword = 1L;
        passwordType = "random";
    }

    public String getGroupRef() {
        return groupRef;
    }

    public void setGroupRef(String groupRef) {
        this.groupRef = groupRef;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public Long getCountPassword() {
        return countPassword;
    }

    public void setCountPassword(Long countPassword) {
        this.countPassword = countPassword;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getTotalAllowedViews() {
        return totalAllowedViews;
    }

    public void setTotalAllowedViews(Long totalAllowedViews) {
        this.totalAllowedViews = totalAllowedViews;
    }

    public Long getTotalAllowedViewsPerVideo() {
        return totalAllowedViewsPerVideo;
    }

    public void setTotalAllowedViewsPerVideo(Long totalAllowedViewsPerVideo) {
        this.totalAllowedViewsPerVideo = totalAllowedViewsPerVideo;
    }

    
}