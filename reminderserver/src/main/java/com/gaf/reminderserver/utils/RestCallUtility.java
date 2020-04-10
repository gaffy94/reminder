package com.gaf.reminderserver.utils;


import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestCallUtility {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(RestCallUtility.class);


    @Autowired
    RestTemplate myTemplate;

    @Autowired
    JSONUtils jsonUtils;

    public ResponseEntity<?> objectRestCall(String url, Object obj) {
        try {
            logger.info("URL iS >> " + url);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            requestHeaders.set("x-client-id",Utility.getProperty("clientid"));
//            requestHeaders.set("x-client-secret",PropsReader.getProperty("clientsecret"));
            logger.info("Request OBJ : >> " + jsonUtils.toJson(obj));
            HttpEntity<?> requestEntity = new HttpEntity<>(obj, requestHeaders);
            ResponseEntity<?> responseEntity = myTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            if (responseEntity.hasBody()) {
                return responseEntity;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            ResponseEntity.BodyBuilder builder;
            builder = ResponseEntity.status(200);
            return builder.body("{\n\"responseMessage\":\"" + StringEscapeUtils.escapeJava(e.getLocalizedMessage()) + "\",\n\"responseCode\":\"" + "99" + "\"}");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
