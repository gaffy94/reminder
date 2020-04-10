package com.gaf.reminderserver.models;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.*;

public class EmailProcessor {

    private static EmailProcessor processor;
    private Properties props;
    private Map images;

    private EmailProcessor(Properties props, Map images) {
        this.props = props;
        this.images = images;
    }

    /**
     * Sends a mail to a recipient
     *
     * @param mail
     */
    public void sendMail(final EmailData mail, String userName,String password) throws Exception {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });
        MimeMessage mimeMsg = createMimeMessage(mail, session);
        Transport.send(mimeMsg);
    }

    /**
     * Sends a collection of mails
     *
     * @param mails
     * @return list of successful reference numbers
     */
    public Collection<String> sendMail(final EmailData[] mails) {
        Collection<String> references = new ArrayList<String>();
        Session session = Session.getInstance(props, null);
        for (int i = 0; i < mails.length; i++) {
            try {
                MimeMessage mimeMsg = createMimeMessage(mails[i], session);
                Transport.send(mimeMsg);
                references.add(mails[i].getReferenceNo());
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }

        return references;
    }

    /**
     * Creates the message to be sent
     * @param mailData the email data
     * @param mailsession the email session
     * @return MimeMessage
     * @throws MessagingException if error occurs
     */
    private MimeMessage createMimeMessage(final EmailData mailData,
            final Session mailsession) throws MessagingException {
        InternetAddress fromInternetAddress = mailData.getFromInetAddress();
        InternetAddress[] toInternetAddress = mailData.getToInetAddresses();
        InternetAddress[] ccInternetAddress = mailData.getCCInetAddresses();
        InternetAddress[] bccInternetAddress = mailData.getBCCInetAddresses();
        InternetAddress[] replyToInternetAddress = mailData.getReplyToInetAddress();

        MimeMessage mimeMsg = new MimeMessage(mailsession);

        // Set the mandatory fields
        mimeMsg.setFrom(fromInternetAddress);
        mimeMsg.setRecipients(RecipientType.TO, toInternetAddress);
        mimeMsg.setSubject(mailData.getSubject());
        mimeMsg.setSentDate(new Date());

        // Set optional fields
        if (ccInternetAddress != null) {
            mimeMsg.setRecipients(RecipientType.CC, ccInternetAddress);
        }
        if (bccInternetAddress != null) {
            mimeMsg.setRecipients(RecipientType.BCC, bccInternetAddress);
        }

        if (replyToInternetAddress != null) {
            mimeMsg.setReplyTo(replyToInternetAddress);
        }

        MimeMultipart mimemultipart = new MimeMultipart();

        //Add Message Body
        MimeBodyPart mimebodypart = new MimeBodyPart();
        mimebodypart.setContent(mailData.getMessageBody(), mailData.getMimeType());
        mimemultipart.addBodyPart(mimebodypart);

        //Add Message Banner
        if(mailData.getBanner() != null)
        {
            mimebodypart = new MimeBodyPart();
            DataSource source = new FileDataSource(mailData.getBanner());
            mimebodypart.setDataHandler(new DataHandler(source));
            mimebodypart.setHeader("Content-ID", "<image>");
            mimemultipart.addBodyPart(mimebodypart);
        }

//                //Add Mail Images
//                if(images != null)
//                {
//                    for(Iterator it = images.entrySet().iterator(); it.hasNext();)
//                    {
//                        Entry entry = (Entry) it.next();
//                        mimebodypart = new MimeBodyPart();
//                        source = new FileDataSource((String) entry.getValue());
//                        mimebodypart.setDataHandler(new DataHandler(source));
//                        mimebodypart.setHeader("Content-ID", "<" + (String) entry.getKey() + ">");
//                        mimemultipart.addBodyPart(mimebodypart);
//                    }
//                }

        //Add Message attachments if any
        if (mailData.getAttachmentFiles() != null) {
            addMessageAttachments(mimemultipart, mailData.getAttachmentFiles());
        }

        mimeMsg.setContent(mimemultipart);
        return mimeMsg;
    }

    /**
     * Adds attachments to a multipart message
     * @param mimemultipart the multi-part to update
     * @param attachments list of file names to attach to message
     * @throws MessagingException if error occurs
     */
    private void addMessageAttachments(final MimeMultipart mimemultipart,
            final File[] attachments) throws MessagingException {
        MimeBodyPart[] amimebodypart = new MimeBodyPart[attachments.length];
        for (int i = 0; i < attachments.length; i++) {
            amimebodypart[i] = new MimeBodyPart();
            DataSource source = new FileDataSource(attachments[i]);
            amimebodypart[i].setDataHandler(new DataHandler(source));
            amimebodypart[i].setFileName(attachments[i].getName());
            mimemultipart.addBodyPart(amimebodypart[i]);
        }
    }

    public static EmailProcessor getInstance() throws Exception {
        if (processor == null) {
            throw new Exception("Processor not properly initialized.  Initialize by calling getInstance(Properties)");
        }

        return processor;
    }

    public static synchronized EmailProcessor getInstance(Properties props, Map images) {
        if (processor == null) {
            processor = new EmailProcessor(props, images);
        }

        return processor;
    }
}
