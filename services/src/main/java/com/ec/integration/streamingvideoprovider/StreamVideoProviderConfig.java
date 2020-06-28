package com.ec.integration.streamingvideoprovider;

import com.ec.util.XmlUtil;
import com.ec.vivo.config.ApplicationProperties;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;




@Component
@Service
public class StreamVideoProviderConfig {
   
    @Autowired
    ApplicationProperties applicationProperties;    

    public String  getToken(){
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvptokenservice()+"&api_key="+applicationProperties.getSvpapikey()+"&api_code="+applicationProperties.getSvpapicode();
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());
     
        return response.getBody();
    }

    public String  getPpvPackages(){
        String token = XmlUtil.getNodeTextContent(getToken(), "auth_token");
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvplistppvpackages()+"&token="+token;
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());
         return response.getBody();
    }

    public String  getListVideos(){
        String token = XmlUtil.getNodeTextContent(getToken(), "auth_token");
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvplistvideos()+"&token="+token;
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());
         return response.getBody();
    }


}