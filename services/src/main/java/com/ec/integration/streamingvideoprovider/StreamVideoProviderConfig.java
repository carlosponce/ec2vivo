package com.ec.integration.streamingvideoprovider;

import com.ec.integration.streamingvideoprovider.message.xmldto.GroupMsg;
import com.ec.integration.streamingvideoprovider.message.xmldto.GroupPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.ResponseMsg;
import com.ec.integration.streamingvideoprovider.message.xmldto.Video;
import com.ec.util.XmlUtil;
import com.ec.vivo.config.ApplicationProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Response;
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

    public ResponseMsg  getListVideosObj(){
        String listVideo = getListVideos();
       
        ResponseMsg rp= XmlUtil.unmarshall(listVideo, ResponseMsg.class);
        System.out.println(rp.getResult());

        for(Video v :rp.getVideoList()){
            System.out.println(v.getTitle());
        }
         return rp;
    }
    

    public String  createPasswordPackage(GroupPayload groupInfo){
        System.out.println("inside createPasswordPackage config");
        String token = XmlUtil.getNodeTextContent(getToken(), "auth_token");
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvpcreatepasswordpackage()+"&token="+token;
        url=url+"&package_name="+groupInfo.getPackageName()+"&group_0_name="+groupInfo.getGroupName()+"&group_0_password_type="+groupInfo.getGroupPasswordType();
        url=url+"&group_0_count_passwords="+groupInfo.getGroupCountPassword();
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());

     
         return response.getBody();
    }


    public ResponseMsg  createPasswordPackageObj(GroupPayload groupInfo){
        String strxml = createPasswordPackage(groupInfo);
       
        ResponseMsg rp= XmlUtil.unmarshall(strxml, ResponseMsg.class);
        System.out.println(rp.getResult());

        for(GroupMsg o :rp.getGroups()){
            System.out.println(o.getRefno());
        }
         return rp;
    }
}