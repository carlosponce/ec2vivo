package com.ec.vivo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;
/**
 * Properties specific to Ec 2 Vivo.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */




@Getter 
@Setter
@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String svpapikey;
    private String svpapicode;
    private String svpbaseUrl;
    private String svptokenservice;
    private String svpvideoplaylistservice;
    private String svplistppvpackages;
    private String svpgetprimaryvideoimage;
    private String svpcreatepasswordpackage;
    private String svpcreateppvpackage;
    private String svplistpasswordpackage;
    private String svpsetvideopasswordpackage;
    private String svpaddgrouppassword;
    private String svplistvideos;


    public ApplicationProperties(){}

    public String getSvpapikey() {
        return svpapikey;
    }

    public void setSvpapikey(String svpapikey) {
        this.svpapikey = svpapikey;
    }

    public String getSvpapicode() {
        return svpapicode;
    }

    public void setSvpapicode(String svpapicode) {
        this.svpapicode = svpapicode;
    }

    public String getSvpbaseUrl() {
        return svpbaseUrl;
    }

    public void setSvpbaseUrl(String svpbaseUrl) {
        this.svpbaseUrl = svpbaseUrl;
    }

    public String getSvptokenservice() {
        return svptokenservice;
    }

    public void setSvptokenservice(String svptokenservice) {
        this.svptokenservice = svptokenservice;
    }

    public String getSvpvideoplaylistservice() {
        return svpvideoplaylistservice;
    }

    public void setSvpvideoplaylistservice(String svpvideoplaylistservice) {
        this.svpvideoplaylistservice = svpvideoplaylistservice;
    }

    public String getSvplistppvpackages() {
        return svplistppvpackages;
    }

    public void setSvplistppvpackages(String svplistppvpackages) {
        this.svplistppvpackages = svplistppvpackages;
    }

    public String getSvplistvideos() {
        return svplistvideos;
    }

    public void setSvplistvideos(String svplistvideos) {
        this.svplistvideos = svplistvideos;
    }

    public String getSvpcreatepasswordpackage() {
        return svpcreatepasswordpackage;
    }

    public void setSvpcreatepasswordpackage(String svpcreatepasswordpackage) {
        this.svpcreatepasswordpackage = svpcreatepasswordpackage;
    }

    public String getSvpcreateppvpackage() {
        return svpcreateppvpackage;
    }

    public void setSvpcreateppvpackage(String svpcreateppvpackage) {
        this.svpcreateppvpackage = svpcreateppvpackage;
    }

    public String getSvplistpasswordpackage() {
        return svplistpasswordpackage;
    }

    public void setSvplistpasswordpackage(String svplistpasswordpackage) {
        this.svplistpasswordpackage = svplistpasswordpackage;
    }

    public String getSvpsetvideopasswordpackage() {
        return svpsetvideopasswordpackage;
    }

    public void setSvpsetvideopasswordpackage(String svpsetvideopasswordpackage) {
        this.svpsetvideopasswordpackage = svpsetvideopasswordpackage;
    }

    public String getSvpaddgrouppassword() {
        return svpaddgrouppassword;
    }

    public void setSvpaddgrouppassword(String svpaddgrouppassword) {
        this.svpaddgrouppassword = svpaddgrouppassword;
    }

    public String getSvpgetprimaryvideoimage() {
        return svpgetprimaryvideoimage;
    }

    public void setSvpgetprimaryvideoimage(String svpgetprimaryvideoimage) {
        this.svpgetprimaryvideoimage = svpgetprimaryvideoimage;
    }

    
}
