package com.gaf.reminderserver.models;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class EmailRequest {
    @NotNull
    public String senderAddress;
    @NotNull
    public String senderDisplayName;
    @NotNull
    public String subject;
    @NotEmpty
    public List<String> emails;
    @NotNull
    public String message;
    public String tr_ref;
    public List<String> CC;
    public List<String> BCC;
    public List<MultipartFile> input;

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderDisplayName() {
        return senderDisplayName;
    }

    public void setSenderDisplayName(String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTr_ref() {
        return tr_ref;
    }

    public void setTr_ref(String tr_ref) {
        this.tr_ref = tr_ref;
    }

    public List<String> getCC() {
        return CC;
    }

    public void setCC(List<String> CC) {
        this.CC = CC;
    }

    public List<String> getBCC() {
        return BCC;
    }

    public void setBCC(List<String> BCC) {
        this.BCC = BCC;
    }

    public List<MultipartFile> getInput() {
        return input;
    }

    public void setInput(List<MultipartFile> input) {
        this.input = input;
    }
}
