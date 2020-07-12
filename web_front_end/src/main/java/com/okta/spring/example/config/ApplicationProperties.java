package com.okta.spring.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Properties specific to Ec 2 Vivo.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */

@Configuration
@ConfigurationProperties(prefix = "server", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String name;
    private String port;
    private Boolean includePortInOauth2RedirectUri;

    public ApplicationProperties(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Boolean getIncludePortInOauth2RedirectUri() {
        return includePortInOauth2RedirectUri;
    }

    public void setIncludePortInOauth2RedirectUri(Boolean includePortInOauth2RedirectUri) {
        this.includePortInOauth2RedirectUri = includePortInOauth2RedirectUri;
    }

}
