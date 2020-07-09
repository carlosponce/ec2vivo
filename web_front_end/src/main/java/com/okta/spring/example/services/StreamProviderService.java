package com.okta.spring.example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.spring.example.xmldto.ResponseMsg;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StreamProviderService {

    private final String base_uri = "http://localhost:8080/api/streamvideoprovider";

    public ResponseMsg getVideoList() {

        final String uri = base_uri + "/getListVideosJson";

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

        final String uri = base_uri + "/getVideobyRefNo?refNo=" + refNo;

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
        final String uri = base_uri + "/getPrimaryVideoImage?videoRef="+videoRef+"&type=" + type;

        RestTemplate restTemplate = new RestTemplate();
        byte[] byteArrayResponse = restTemplate.getForObject(uri, byte[].class);

        return byteArrayResponse;
    }

    public String getVideoImageBase64(String videoRef, String type){
        final String uri = base_uri + "/getPrimaryVideoImageBase64?videoRef="+videoRef+"&type=" + type;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);

    }

}