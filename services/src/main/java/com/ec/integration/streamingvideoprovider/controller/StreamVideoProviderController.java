package com.ec.integration.streamingvideoprovider.controller;

import com.ec.integration.streamingvideoprovider.StreamVideoProviderConfig;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}