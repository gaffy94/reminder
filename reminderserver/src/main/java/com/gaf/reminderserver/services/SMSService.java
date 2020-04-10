package com.gaf.reminderserver.services;

import com.gaf.reminderserver.models.SmsRequest;
import com.gaf.reminderserver.utils.JSONUtils;
import com.gaf.reminderserver.utils.RestCallUtility;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    @Autowired
    RestCallUtility restCallUtility;
    @Autowired
    Environment environment;

    @Autowired
    JSONUtils jsonUtils;

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SMSService.class);
    void doSendSms(SmsRequest smsRequest){
        try{
            String url = environment.getProperty("SMS_URL");
            ResponseEntity<?> responseEntity = restCallUtility.objectRestCall(url, smsRequest);
            if (!responseEntity.hasBody()) {
                return;
            }
            logger.info("SMS SERVICE RESPONSE : " + jsonUtils.toJson(responseEntity.getBody()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
