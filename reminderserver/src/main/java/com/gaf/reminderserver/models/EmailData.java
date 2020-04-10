package com.gaf.reminderserver.models;

import javax.mail.internet.InternetAddress;
import java.io.File;

public class EmailData  extends BaseData {
    /**
     * referenceNo - Used to identify message
     */
    private String referenceNo;

    /**
     *  fromAddress
     */
    private EmailAddress fromAddress;

    /**
     *  toAddresses
     */
    private EmailAddress[] toAddresses;

    /**
     *  ccAddresses
     */
    private EmailAddress[] ccAddresses;

    /**
     *  bccAddresses
     */
    private EmailAddress[] bccAddresses;

    /**
     *  replyToAddresses
     */
    private EmailAddress[] replyToAddresses;

    /**
     *  The Subject of the Email
     */
    private String subject;

    /**
     *  The Content or body of the message
     */
    private String messageBody;

    /**
     * The MimeType of the Message
     */
    private String mimeType;

    /**
     *  attachments
     */
    private EmailAttachment[] attachments;

    /**
     *  banner
     */
    private String banner;

    public EmailData()
    {
    	toAddresses = null;
        messageBody = null;
        subject = "";
        fromAddress = null;
    }

    /**
     * Public Constructor
     * @param toAddresses
     * @param message
     */
    public EmailData(final String[] to, final String message, final String subject)
    {
        fromAddress = null;
        this.toAddresses = new EmailAddress[to.length];
        for(int i = 0; i < to.length; i++)
            toAddresses[i] = new EmailAddress(to[i]);

        this.messageBody = message;
        this.subject = (subject != null ? subject.trim() : "");
    }

    /**
     * Public Constructor
     * @param fromAddress
     * @param toAddresses
     * @param message
     */
    public EmailData(final String from, final String[] to, final String message, final String subject)
    {
        this.fromAddress = new EmailAddress(from);
        this.toAddresses = new EmailAddress[to.length];
        for(int i = 0; i < to.length; i++)
            toAddresses[i] = new EmailAddress(to[i]);

        this.messageBody = message;
        this.subject = (subject != null ? subject.trim() : "");
    }

    /**
     * Gets the reply to addresses
     * @return EmailAddress[]
     */
    public EmailAddress[] getReplyToAddresses()
    {
	return replyToAddresses;
    }

    /**
     * Sets the reply to addresses
     * @param replyToAddresses
     */
    public void setReplyToAddresses(EmailAddress[] replyToAddresses)
    {
	this.replyToAddresses = replyToAddresses;
    }

    /**
     * Sets the To address
     * @param toAddresses
     */
    public void setToAddresses(EmailAddress[] toAddresses)
    {
	this.toAddresses = toAddresses;
    }

    /**
     * Returns the subject.
     *
     * @return    String
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param  subj The new subject value
     */
    public void setSubject(final String subject)
    {
        if (subject == null)
        {
            this.subject = "";
        }
        else
        {
            this.subject = subject;
        }
    }

    /**
     * Method called to set the from address
     * Sets the fromAddress and the reply address
     * @param  frAddress The new fromAddress value
     * @exception  IllegalArgumentException  Description of the Exception
     */
    public void setFromAddress(final EmailAddress fromAddress) throws IllegalArgumentException
    {
        if (fromAddress == null)
        {
            throw new IllegalArgumentException("The fromAddress is Null");
        }
        this.fromAddress = fromAddress;
    }

    /**
     * Sets the messageBody.
     *
     * @param  messageBody
     */
    public void setMessageBody(final String messageBody)
    {
        if (messageBody == null)
        {
        	this.messageBody = "";
        }
        else
        {
        	this.messageBody = messageBody;
        }
    }

    /**
     * Gets the recipients' addresses
     * @return
     */
    public EmailAddress[] getToAddresses()
    {
        return (EmailAddress[]) this.toAddresses;
    }

    /**
     * Returns the bccAddresses.
     * @return EmailAddress[]
     */
    public EmailAddress[] getBccAddresses()
    {
        return bccAddresses;
    }

    /**
     * Sets the bcc Addresses.
     * @param bccAddresses The bccAddresses to set
     */
    public void setBccAddresses(final EmailAddress[] bccAddresses)
    {
        this.bccAddresses = bccAddresses;
    }

    /**
     * Returns the ccAddresses.
     * @return EmailAddress[]
     */
    public EmailAddress[] getCcAddresses()
    {
        return ccAddresses;
    }

    /**
     * Sets the cc Addresses.
     * @param ccAddresses The ccAddresses to set
     */
    public void setCcAddresses(final EmailAddress[] ccAddresses)
    {
        this.ccAddresses = ccAddresses;
    }

    /**
     * Returns the fromAddress.
     * @return EmailAddress
     */
    public EmailAddress getFromAddress()
    {
        return fromAddress;
    }

    /**
     * Returns a list of Internet Addresses
     * @return InternetAddress[]
     */
    public InternetAddress[] getToInetAddresses()
    {
        InternetAddress[] inetToAddresses = new InternetAddress[toAddresses.length];
        for (int i = 0; i < toAddresses.length; i++)
        {
        	inetToAddresses[i] = (InternetAddress) toAddresses[i].getAddress();
        }
        return inetToAddresses;

    }

    /**
     * Returns a list of Internet Addresses
     * @return InternetAddress[]
     */
    public InternetAddress[] getCCInetAddresses()
    {
        if (this.getCcAddresses() == null)
        {
            return null;
        }
        
        InternetAddress[] inetCcAddresses = new InternetAddress[ccAddresses.length];
        for (int i = 0; i < ccAddresses.length; i++)
        {
        	inetCcAddresses[i] = (InternetAddress) ccAddresses[i].getAddress();
        }
        return inetCcAddresses;

    }

    /**
     * Returns a list of Internet Addresses
     * @return InternetAddress[]
     */
    public InternetAddress[] getBCCInetAddresses()
    {
        if (this.getBccAddresses() == null)
        {
            return null;
        }

        InternetAddress[] inetBccAddress = new InternetAddress[bccAddresses.length];
        for (int i = 0; i < bccAddresses.length; i++)
        {
        	inetBccAddress[i] = (InternetAddress) bccAddresses[i].getAddress();
        }
        return inetBccAddress;

    }

    /**
     * Returns the FromInetAddress
     * @return InternetAddress
     */
    public InternetAddress getFromInetAddress()
    {
        return fromAddress.getAddress();

    }

    /**
     * Returns a list of Internet Addresses
     * @return InternetAddress[]
     */
    public InternetAddress[] getReplyToInetAddress()
    {
        if (this.getReplyToAddresses() == null)
        {
            return null;
        }
        InternetAddress[] inetReplyAddresses = new InternetAddress[replyToAddresses.length];
        for (int i = 0; i < replyToAddresses.length; i++)
        {
        	inetReplyAddresses[i] = (InternetAddress) replyToAddresses[i].getAddress();
        }
        
        return inetReplyAddresses;
    }

    /**
     * Returns the attachments.
     * @return EmailAttachment[]
     */
    public EmailAttachment[] getAttachments()
    {
        return attachments;
    }

    /**
     * Sets the attachments.
     * @param attachments The attachments to set
     */
    public void setAttachments(final File[] attachs)
    {
        this.attachments = new EmailAttachment[attachs.length];
        for(int i = 0; i < attachs.length; i++)
            attachments[i] = new EmailAttachment(attachs[i]);
    }

    /**
     * Method getAttachmentFiles.
     * @return File[] an array of file objects
     */
    public File[] getAttachmentFiles()
    {
        if (this.getAttachments() == null)
        {
            return null;
        }

        File[] files = new File[attachments.length];
        for (int i = 0; i < attachments.length; i++)
        {
            files[i] = attachments[i].getAttachment();
        }
        return files;
    }

    /**
     * Returns the body of the message
     * @return String
     */
    public String getMessageBody()
    {
	return messageBody;
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }

    public String getReferenceNo()
    {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo)
    {
        this.referenceNo = referenceNo;
    }
    
    public String getBanner()
    {
        return banner;
    }

    public void setBanner(String banner)
    {
        this.banner = banner;
    }
}
