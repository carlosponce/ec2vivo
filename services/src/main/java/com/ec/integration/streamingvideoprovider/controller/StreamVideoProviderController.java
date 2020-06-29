package com.ec.integration.streamingvideoprovider.controller;

import java.net.URISyntaxException;

import com.ec.integration.streamingvideoprovider.StreamVideoProviderConfig;
import com.ec.integration.streamingvideoprovider.message.xmldto.GroupPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.ResponseMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/streamvideoprovider")
@CrossOrigin(origins = "*")
public class StreamVideoProviderController{

    @Autowired
    StreamVideoProviderConfig config;

    @GetMapping(value = "/getToken")
	public  String getToken(){
        return config.getToken();
    }

    @GetMapping(value = "/getPpvPackages")
	public  String getPpvPackages(){
        return config.getPpvPackages();
    }

    @GetMapping(value = "/getListVideos")
	public  String getListVideos(){
        return config.getListVideos();
    }

    @GetMapping(value = "/getListVideosObj")
	public  ResponseMsg getListVideosObj(){
        return config.getListVideosObj();
    }

    @PostMapping("/createPasswordPackage")
    public ResponseEntity<ResponseMsg> createPasswordPackage(@RequestBody GroupPayload groupinfo) throws URISyntaxException {
        System.out.println("inside createPasswordPackage controller");
        ResponseMsg response = config.createPasswordPackageObj(groupinfo);
        return new ResponseEntity<ResponseMsg>(response,HttpStatus.OK);
    }

}