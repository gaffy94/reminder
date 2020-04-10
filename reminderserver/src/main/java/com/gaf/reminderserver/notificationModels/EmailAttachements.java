package com.gaf.reminderserver.notificationModels;

import java.io.InputStream;

public class EmailAttachements {
    private String fileName;

    private InputStream file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }
}
