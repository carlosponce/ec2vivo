package com.ec.integration.streamingvideoprovider;

import com.ec.integration.streamingvideoprovider.message.xmldto.GroupMsg;
import com.ec.integration.streamingvideoprovider.message.xmldto.GroupPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.PasswordPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.ResponseMsg;
import com.ec.integration.streamingvideoprovider.message.xmldto.TicketMsg;
import com.ec.integration.streamingvideoprovider.message.xmldto.TicketPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.Video;
import com.ec.integration.streamingvideoprovider.message.xmldto.VideoPasswordPayload;
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

    public ResponseMsg  getListVideosJson(){
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


    public ResponseMsg  createPasswordPackageJson(GroupPayload groupInfo){
        String strxml = createPasswordPackage(groupInfo);
       
        ResponseMsg rp= XmlUtil.unmarshall(strxml, ResponseMsg.class);
        System.out.println(rp.getResult());

        for(GroupMsg o :rp.getGroups()){
            System.out.println(o.getRefno());
        }
         return rp;
    }

    public String  createPpvPackage(TicketPayload obj){
        System.out.println("inside createPpvPackage config");
        String token = XmlUtil.getNodeTextContent(getToken(), "auth_token");
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvpcreateppvpackage()+"&token="+token;
        url=url+"&package_name="+obj.getPackageName()+"&ticket_0_title="+obj.getTicketTitle()+"&ticket_0_currency="+obj.getTicketCurrency();
        url=url+"&ticket_0_price="+obj.getTicketPrice()+"&ticket_0_description="+obj.getTicketDescription();
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());

     
         return response.getBody();
    }

    public ResponseMsg  createPpvPackageJson(TicketPayload obj){
        String strxml = createPpvPackage(obj);
       
        ResponseMsg rp= XmlUtil.unmarshall(strxml, ResponseMsg.class);
        System.out.println(rp.getResult());

        for(TicketMsg o :rp.getTickets()){
            System.out.println(o.getOrderno());
        }
         return rp;
    }

    public String  getListPasswordPackages(){
        String token = XmlUtil.getNodeTextContent(getToken(), "auth_token");
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvplistpasswordpackage()+"&token="+token;
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());

     
         return response.getBody();
    }

    public ResponseMsg  getListPasswordPackagesJson(){
        String strxml = getListPasswordPackages();
       
        ResponseMsg rp= XmlUtil.unmarshall(strxml, ResponseMsg.class);
        System.out.println(rp.getResult());

        
         return rp;
    }

    public String  setVideoPassword(VideoPasswordPayload obj){
        System.out.println("inside VideoPassword config");
        String token = XmlUtil.getNodeTextContent(getToken(), "auth_token");
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvpsetvideopasswordpackage()+"&token="+token;
        url=url+"&video_ref="+obj.getVideoRef()+"&package_ref="+obj.getPasswordRef();
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());

     
         return response.getBody();
    }

    public ResponseMsg  setVideoPasswordJson(VideoPasswordPayload obj){
        String strxml = setVideoPassword(obj);
       
        ResponseMsg rp= XmlUtil.unmarshall(strxml, ResponseMsg.class);
        System.out.println(rp.getResult());

        
         return rp;
    }

    public String  addGroupPassword(PasswordPayload obj){
        System.out.println("inside addgroupPassword config");
        String token = XmlUtil.getNodeTextContent(getToken(), "auth_token");
        String url = applicationProperties.getSvpbaseUrl()+applicationProperties.getSvpaddgrouppassword()+"&token="+token;
        url=url+"&group_ref="+obj.getGroupRef()+"&password_type="+obj.getPasswordType()+"&count_passwords="+obj.getCountPassword();
        System.out.println(url);
        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());

     
         return response.getBody();
    }

    public ResponseMsg  addGroupPasswordJson(PasswordPayload obj){
        String strxml = addGroupPassword(obj);
       
        ResponseMsg rp= XmlUtil.unmarshall(strxml, ResponseMsg.class);
        System.out.println(rp.getResult());

        
         return rp;
    }

}