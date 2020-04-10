package com.gaf.reminderserver.models;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

/**
 * @author toochukwu_n1
 */
public class EmailAddress extends BaseData {
    /**
     * address the address to be verified
     */
    private InternetAddress address;

    /**
     * Returns the Internet Address
     * @return InternetAddress
     */
    public InternetAddress getAddress() {
        return address;
    }

    /**
     * Sets the Internet address
     * @param address
     */
    public void setAddress(InternetAddress address) {
        this.address = address;
    }

    /**
     * Public Constructor
     * @param address
     * @throws IllegalArgumentException if address is invalid
     */
    public EmailAddress(final String address)
        throws IllegalArgumentException
    {
        try
        {
            this.address = new InternetAddress(address);
        }
        catch (AddressException e)
        {
            throw new IllegalArgumentException(address + " is invalid");
        }
    }
    
    /**
     * Public Constructor
     * @param address
     * @param displayName
     * @throws IllegalArgumentException if address is invalid
     */
    public EmailAddress(final String address, final String displayName)
            throws IllegalArgumentException {
        try
        {
            this.address = new InternetAddress(address);
            if(displayName != null && !displayName.trim().equals(""))
                this.address.setPersonal(displayName);
        } 
        catch (AddressException e)
        {
            throw new IllegalArgumentException(address + " is invalid");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
}
