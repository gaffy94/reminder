package com.gaf.reminderserver.services;


import com.gaf.reminderserver.models.*;
import com.gaf.reminderserver.utils.Utilities;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    Environment environment;
@Autowired
Utilities utilities;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(EmailService.class);
    public Response sendEmail(EmailRequest emailRequest) {
        Response restResponse = new Response();
        try{

            String resp = "99";
            if (emailRequest.getEmails().size() > 0) {

                ArrayList<EmailData> msgse = new ArrayList<>();
                EmailData emailData = new EmailData();
                emailData.setReferenceNo(emailRequest.getTr_ref());
//                if(emailRequest.getInput() != null){
//                    if(file.size() > 0) {
//                        File[] files = new File[file.size()];
//                        int i = 0;
//                    for (MultipartFile attach : file) {
//                        String rootPath = System.getProperty("HOME");
//                        File dir = new File(rootPath + File.separator + "ATTACHEMENTS" + File.separator);
//                        if (!dir.exists()) {
//                            dir.mkdirs();
//                        }
//                        File targetFile = new File(dir.getAbsolutePath() + File.separator  + File.separator + attach.getOriginalFilename());
//
//                        java.nio.file.Files.copy(
//                                attach.getInputStream(),
//                                targetFile.toPath(),
//                                StandardCopyOption.REPLACE_EXISTING);
//
//                        IOUtils.closeQuietly(attach.getInputStream());
//                        files[i] = targetFile;
//                        i++;
//                    }
//
//                        emailData.setAttachments(files);
//                    }
//                }


                emailData.setFromAddress(new EmailAddress(emailRequest.getSenderAddress(), emailRequest.getSenderDisplayName()));
                List<EmailAddress> toos = new ArrayList<>();
                for (String email : emailRequest.getEmails()) {
                    if (email.length() > 0 && !email.isEmpty() && utilities.isValidEmailAddress(email)) {
                        toos.add(new EmailAddress(email));

                    }
                }

                if(toos.size() > 0) {
                    EmailAddress[] tooaddr = new EmailAddress[toos.size()];
                    for(int i=0; i< toos.size(); i++) {
                        tooaddr[i] = toos.get(i);
                    }
                    emailData.setToAddresses(tooaddr);
                }
                List<EmailAddress> ccarg = new ArrayList<>();
                if (emailRequest.getCC() != null && emailRequest.getCC() .size() > 0) {
                    for (String email : emailRequest.getCC() ) {
                        log.info("checking : "+email);
                        if (email.length() > 0 && !email.isEmpty() && utilities.isValidEmailAddress(email)) {
                            log.info(email + "passed");
                            ccarg.add(new EmailAddress(email));

                        }
                    }
                }
                if(ccarg.size() > 0) {
                    EmailAddress[] ccaddr = new EmailAddress[ccarg.size()];
                    for(int i=0; i< ccarg.size(); i++) {
                        ccaddr[i] = ccarg.get(i);
                    }
                    emailData.setCcAddresses(ccaddr);
                }


                List<EmailAddress> bccarg = new ArrayList<>();
                if (emailRequest.getBCC() != null && emailRequest.getBCC().size() > 0) {
                    for (String email : emailRequest.getBCC()) {
                        if (email.length() > 0 && !email.isEmpty() && utilities.isValidEmailAddress(email)) {
                            bccarg.add(new EmailAddress(email));

                        }
                    }
                }
                if(bccarg.size() > 0) {
                    EmailAddress[] bccaddr = new EmailAddress[bccarg.size()];
                    for(int i=0; i< bccarg.size(); i++) {
                        bccaddr[i] = bccarg.get(i);
                    }
                    emailData.setBccAddresses(bccaddr);
                }

                emailRequest.setMessage(HtmlUtils.htmlUnescape(emailRequest.getMessage()));
                emailData.setMessageBody(emailRequest.getMessage());
                emailData.setMimeType("text/html; charset=utf-8");
                emailData.setSubject(emailRequest.getSubject());
                msgse.add(emailData);
                Properties mailProps = new Properties();
                mailProps.put("mail.smtp.host", environment.getProperty("EMAIL_SERVER_HOST"));
                mailProps.put("mail.smtp.port", environment.getProperty("EMAIL_SERVER_PORT"));
                mailProps.put("mail.smtp.auth", "true");
                mailProps.put("mail.smtp.starttls.enable", "true");
                EmailProcessor processorem = EmailProcessor.getInstance(mailProps, null);
                ArrayList<String> failure = new ArrayList<String>();
                ArrayList<String> success = new ArrayList<String>();
                for (EmailData x : msgse) {
                    try {
                        processorem.sendMail(x,environment.getProperty("EMAIL_SERVER_USER"),environment.getProperty("EMAIL_SERVER_PASSWORD"));
                        success.add(x.getReferenceNo());
                        restResponse.setResponseCode("000");
                        restResponse.setResponseMessage("SENT SUCCESSFULLY");
                        restResponse.setMessageId(emailRequest.getTr_ref());
                    } catch (Exception ex) {
                        log.info(ex.getMessage(),ex);
                        failure.add(x.getReferenceNo());
                        restResponse.setResponseCode("99");
                        restResponse.setResponseMessage("ERROR SENDING EMAIL BECAUSE : "+ex.getMessage());
                        restResponse.setMessageId(emailRequest.getTr_ref());
                    }
                }


            }


        }catch(Exception e){
            log.info(e.getMessage(),e);
            restResponse.setResponseCode("99");
            restResponse.setResponseMessage("ERROR SENDING EMAIL BECAUSE : "+e.getMessage());
            restResponse.setMessageId(emailRequest.getTr_ref());
        }
        return restResponse;
    }
    public Response sendEmailWithAttachement(List<MultipartFile> file, EmailRequest emailRequest) {
        Response restResponse = new Response();
        try{

            String resp = "99";
            if (emailRequest.getEmails().size() > 0) {

                ArrayList<EmailData> msgse = new ArrayList<>();
                EmailData emailData = new EmailData();
                emailData.setReferenceNo(emailRequest.getTr_ref());
                if(emailRequest.getInput() != null){
                    if(file.size() > 0) {
                        File[] files = new File[file.size()];
                        int i = 0;
                        for (MultipartFile attach : file) {
                            String rootPath = System.getProperty("HOME");
                            File dir = new File(rootPath + File.separator + "ATTACHEMENTS" + File.separator);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            File targetFile = new File(dir.getAbsolutePath() + File.separator  + File.separator + attach.getOriginalFilename());

                            java.nio.file.Files.copy(
                                    attach.getInputStream(),
                                    targetFile.toPath(),
                                    StandardCopyOption.REPLACE_EXISTING);

                            IOUtils.closeQuietly(attach.getInputStream());
                            files[i] = targetFile;
                            i++;
                        }

                        emailData.setAttachments(files);
                    }
                }


                emailData.setFromAddress(new EmailAddress(emailRequest.getSenderAddress(), emailRequest.getSenderDisplayName()));
                List<EmailAddress> toos = new ArrayList<>();
                for (String email : emailRequest.getEmails()) {
                    if (email.length() > 0 && !email.isEmpty() && utilities.isValidEmailAddress(email)) {
                        toos.add(new EmailAddress(email));

                    }
                }

                if(toos.size() > 0) {
                    EmailAddress[] tooaddr = new EmailAddress[toos.size()];
                    for(int i=0; i< toos.size(); i++) {
                        tooaddr[i] = toos.get(i);
                    }
                    emailData.setToAddresses(tooaddr);
                }
                List<EmailAddress> ccarg = new ArrayList<>();
                if (emailRequest.getCC() != null && emailRequest.getCC() .size() > 0) {
                    for (String email : emailRequest.getCC() ) {
                        log.info("checking : "+email);
                        if (email.length() > 0 && !email.isEmpty() && utilities.isValidEmailAddress(email)) {
                            log.info(email + "passed");
                            ccarg.add(new EmailAddress(email));

                        }
                    }
                }
                if(ccarg.size() > 0) {
                    EmailAddress[] ccaddr = new EmailAddress[ccarg.size()];
                    for(int i=0; i< ccarg.size(); i++) {
                        ccaddr[i] = ccarg.get(i);
                    }
                    emailData.setCcAddresses(ccaddr);
                }


                List<EmailAddress> bccarg = new ArrayList<>();
                if (emailRequest.getBCC() != null && emailRequest.getBCC().size() > 0) {
                    for (String email : emailRequest.getBCC()) {
                        if (email.length() > 0 && !email.isEmpty() && utilities.isValidEmailAddress(email)) {
                            bccarg.add(new EmailAddress(email));

                        }
                    }
                }
                if(bccarg.size() > 0) {
                    EmailAddress[] bccaddr = new EmailAddress[bccarg.size()];
                    for(int i=0; i< bccarg.size(); i++) {
                        bccaddr[i] = bccarg.get(i);
                    }
                    emailData.setBccAddresses(bccaddr);
                }

                emailRequest.setMessage(HtmlUtils.htmlUnescape(emailRequest.getMessage()));
                emailData.setMessageBody(emailRequest.getMessage());
                emailData.setMimeType("text/html; charset=utf-8");
                emailData.setSubject(emailRequest.getSubject());
                msgse.add(emailData);
                Properties mailProps = new Properties();
                mailProps.put("mail.smtp.host", environment.getProperty("EMAIL_SERVER_HOST"));
                mailProps.put("mail.smtp.port", environment.getProperty("EMAIL_SERVER_PORT"));
                EmailProcessor processorem = EmailProcessor.getInstance(mailProps, null);
                ArrayList<String> failure = new ArrayList<String>();
                ArrayList<String> success = new ArrayList<String>();
                for (EmailData x : msgse) {
                    try {
                        processorem.sendMail(x,environment.getProperty("EMAIL_SERVER_USER"),environment.getProperty("EMAIL_SERVER_PASSWORD"));
                        success.add(x.getReferenceNo());
                        restResponse.setResponseCode("000");
                        restResponse.setResponseMessage("SENT SUCCESSFULLY");
                        restResponse.setMessageId(emailRequest.getTr_ref());
                    } catch (Exception ex) {
                        log.info(ex.getMessage(),ex);
                        failure.add(x.getReferenceNo());
                        restResponse.setResponseCode("99");
                        restResponse.setResponseMessage("ERROR SENDING EMAIL BECAUSE : "+ex.getMessage());
                        restResponse.setMessageId(emailRequest.getTr_ref());
                    }
                }


            }


        }catch(Exception e){
            log.info(e.getMessage(),e);
            restResponse.setResponseCode("99");
            restResponse.setResponseMessage("ERROR SENDING EMAIL BECAUSE : "+e.getMessage());
            restResponse.setMessageId(emailRequest.getTr_ref());
        }
        return restResponse;
    }
}
