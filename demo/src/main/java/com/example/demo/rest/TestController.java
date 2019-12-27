package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @Value("${greeting}")
    private String greeting;


    @Autowired
    private Environment env;

    @RequestMapping("/ping")
    public String ping() {
        LOGGER.info(">>> enter ping method");

        String result = greeting;

        InetAddress ip ;
        String hostname ;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            result +=  String.format(" IP Address: %s, HostName: %s.", ip,  hostname);
        } catch (UnknownHostException e) {
            LOGGER.error("Error when trying to get hostIP and hostName", e);
        }
        return result;
    }


    @RequestMapping("/credetials")
    public String credetials() {
        LOGGER.info(">>> enter credetials method");
        return "Credential is:  " + env.getProperty("credential");
    }



}
