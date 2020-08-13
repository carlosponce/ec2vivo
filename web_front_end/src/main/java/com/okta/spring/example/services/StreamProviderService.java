package com.okta.spring.example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.spring.example.config.ApplicationProperties;
import com.okta.spring.example.xmldto.ResponseMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import  com.okta.spring.example.dto.ResponseMsgDto;
import java.util.Map;
import java.time.LocalDate;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import com.okta.spring.example.domain.UserEc2Vivo;

@Component
public class StreamProviderService {

    @Autowired
    ApplicationProperties applicationProperties;

    public ResponseMsg getVideoList(String videoSource) {

        final String uri = applicationProperties.getStreamingServiceUri() + "/getListVideosJsonVideoSource?videoSource=" + videoSource;

        RestTemplate restTemplate = new RestTemplate();
        String stringJsonResponse = restTemplate.getForObject(uri, String.class);

        System.out.println(stringJsonResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMsg videoList;

        try {
            videoList = objectMapper.readValue(stringJsonResponse, ResponseMsg.class);
        } catch (JsonProcessingException e) {
            videoList = new ResponseMsg();
            e.printStackTrace();
        }

        return videoList;
    }

    public ResponseMsg getAllVideoList() {

      final String uri = applicationProperties.getStreamingServiceUri() + "/getListVideosJson";

      RestTemplate restTemplate = new RestTemplate();
      String stringJsonResponse = restTemplate.getForObject(uri, String.class);

      System.out.println(stringJsonResponse);

      ObjectMapper objectMapper = new ObjectMapper();
      ResponseMsg videoList;

      try {
          videoList = objectMapper.readValue(stringJsonResponse, ResponseMsg.class);
      } catch (JsonProcessingException e) {
          videoList = new ResponseMsg();
          e.printStackTrace();
      }

      return videoList;
  }

    public ResponseMsg getVideobyRefNo(String refNo) {

        final String uri = applicationProperties.getStreamingServiceUri() + "/getVideobyRefNo?refNo=" + refNo;

        RestTemplate restTemplate = new RestTemplate();
        String stringJsonResponse = restTemplate.getForObject(uri, String.class);

        System.out.println("getVideobyRefNo:" + refNo +  ":\n" + stringJsonResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMsg videoList;

        try {
            videoList = objectMapper.readValue(stringJsonResponse, ResponseMsg.class);
        } catch (JsonProcessingException e) {
            videoList = new ResponseMsg();
            e.printStackTrace();
        }

        return videoList;
    }


    public byte[] getThumbailVideoImage(String videoRef) {
        return getVideoImage(videoRef,"thumb");
    }

    public byte[] getLargeVideoImage(String videoRef) {
        return getVideoImage(videoRef,"large");
    }

    private byte[] getVideoImage(String videoRef, String type){
        final String uri = applicationProperties.getStreamingServiceUri() + "/getPrimaryVideoImage?videoRef="+videoRef+"&type=" + type;

        RestTemplate restTemplate = new RestTemplate();
        byte[] byteArrayResponse = restTemplate.getForObject(uri, byte[].class);

        return byteArrayResponse;
    }

    public String getVideoImageBase64(String videoRef, String type){
        final String uri = applicationProperties.getStreamingServiceUri() + "/getPrimaryVideoImageBase64?videoRef="+videoRef+"&type=" + type;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);

    }

    public void saveNewUser(UserEc2Vivo userVivelo) {
        final String uri = applicationProperties.getStreamingServiceUri() + "/saveUser";

        Map<String, String> params = new HashMap<String, String>();

        params.put("name", userVivelo.getName());
        params.put("lastName", userVivelo.getLastName());
        params.put("phone", userVivelo.getPhone());
        params.put("email", userVivelo.getEmail());
        params.put("userName", userVivelo.getUserName());
        params.put("userPassword", userVivelo.getUserPassword());
        params.put("loginSource", userVivelo.getLoginSource());
        params.put("registerDate",LocalDate.now() .toString());
        params.put("isCreation", "true");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseMsgDto> response = restTemplate.postForEntity(uri, params, ResponseMsgDto.class );

    }


}