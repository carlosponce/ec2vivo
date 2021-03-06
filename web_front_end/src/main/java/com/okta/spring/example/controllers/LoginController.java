/*
 * Copyright 2017 Okta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.okta.spring.example.controllers;

import com.okta.spring.boot.oauth.config.OktaOAuth2Properties;
import com.okta.spring.example.config.ApplicationProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class LoginController {

    private static final String STATE = "state";
    private static final String NONCE = "nonce";
    private static final String SCOPES = "scopes";
    private static final String OKTA_BASE_URL = "oktaBaseUrl";
    private static final String OKTA_CLIENT_ID = "oktaClientId";
    private static final String REDIRECT_URI = "redirectUri";
    private static final String ISSUER_URI = "issuerUri";

    private final OktaOAuth2Properties oktaOAuth2Properties;
    
    @Autowired
    ApplicationProperties applicationProperties;


    public LoginController(OktaOAuth2Properties oktaOAuth2Properties) {
        this.oktaOAuth2Properties = oktaOAuth2Properties;
    }

    @GetMapping(value = "/custom-login")
    public ModelAndView login(HttpServletRequest request,
                              @RequestParam(name = "state", required = false) String state,
                              @RequestParam(name = "nonce") String nonce) throws MalformedURLException {

        // if we don't have the state parameter redirect
        if (state == null) {
            return new ModelAndView("redirect:" + oktaOAuth2Properties.getRedirectUri());
        }

        String issuer = oktaOAuth2Properties.getIssuer();
        // the widget needs the base url, just grab the root of the issuer
        String orgUrl = new URL(new URL(issuer), "/").toString();

        ModelAndView mav = new ModelAndView("login");
        mav.addObject(STATE, state);
        mav.addObject(NONCE, nonce);
        mav.addObject(SCOPES, oktaOAuth2Properties.getScopes());
        mav.addObject(OKTA_BASE_URL, orgUrl);
        mav.addObject(OKTA_CLIENT_ID, oktaOAuth2Properties.getClientId());

        // from ClientRegistration.redirectUriTemplate, if the template is change you must update this
        //String str_redir_uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + oktaOAuth2Properties.getRedirectUri();
        StringBuilder   strbRedirectUrl = new StringBuilder();

        strbRedirectUrl.append(request.getScheme());
        strbRedirectUrl.append("://");
        strbRedirectUrl.append(request.getServerName());

        if(applicationProperties.getIncludePortInOauth2RedirectUri()){
            strbRedirectUrl.append(":");
            strbRedirectUrl.append(request.getServerPort());
        }

        strbRedirectUrl.append(request.getContextPath());
        strbRedirectUrl.append(oktaOAuth2Properties.getRedirectUri());

        mav.addObject(REDIRECT_URI, strbRedirectUrl.toString());
        mav.addObject(ISSUER_URI, issuer);

        return mav;
    }

    @GetMapping("/post-logout")
    public String logout() {
        return "index";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}