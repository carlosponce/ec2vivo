package com.okta.spring.example.xmldto;

import java.io.Serializable;

public class GroupPayload implements Serializable {
    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;
    
    private String packageName;
    private String groupName;
    private String groupPasswordType;
    private Long groupCountPassword;

    public GroupPayload(){
        packageName = "New Package";
        groupName = "New Group";
        groupPasswordType = "random";
        groupCountPassword = 1L;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPasswordType() {
        return groupPasswordType;
    }

   

    public void setGroupPasswordType(String groupPasswordType) {
        this.groupPasswordType = groupPasswordType;
    }

    public Long getGroupCountPassword() {
        return groupCountPassword;
    }

    public void setGroupCountPassword(Long groupCountPassword) {
        this.groupCountPassword = groupCountPassword;
    }

    


}