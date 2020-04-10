package com.gaf.reminderserver.services;

import com.gaf.reminderserver.entities.Tenants;
import com.gaf.reminderserver.models.EmailRequest;
import com.gaf.reminderserver.models.SmsRequest;
import net.bytebuddy.asm.Advice;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Service
public class NotifyService {
@Autowired
    EmailService emailService;
@Autowired
    Environment environment;

    @Value("classpath:/mail.html")
    Resource resourceFile;

    @Autowired
    SMSService smsService;

    public void doNotify(Tenants tenants){
        System.out.println("Trying to add" +tenants);
        EmailRequest request = new EmailRequest();
        request.setSenderAddress("OLASTORE@gmail.com");
        request.setEmails(Collections.singletonList(tenants.getEmail()));
        String s = "";
        try {
             s = IOUtils.toString(resourceFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.setMessage(s.replace("{tenant}",tenants.getFirstName()+" "+tenants.getLastName()));
        request.setSenderDisplayName("12 ADEMUYIWA STREET");
        request.setSubject("REMIDER - BILLS DUE");
        request.setTr_ref("MEP");
        emailService.sendEmail(request);
        String apiKey = environment.getProperty("SMS_API_KEY");
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setApi_token(apiKey);
        smsRequest.setBody(environment.getProperty("SMS_MESSAGE").replace("{tenant}", tenants.getFirstName()+" "+tenants.getLastName()));
        smsRequest.setDnd("4");
        smsRequest.setFrom("REMINDER");
        smsRequest.setTo(tenants.getPhoneNumber());
        smsService.doSendSms(smsRequest);
    }


}
