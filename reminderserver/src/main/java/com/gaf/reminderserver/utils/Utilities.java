package com.gaf.reminderserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Properties;
import java.util.Random;
@Service
public class Utilities {
    private static final Logger log = LoggerFactory.getLogger(Utilities.class);
    public static String getPropertiesValue(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        String retValue = "";
        try {
            input = Utilities.class.getResourceAsStream("config.properties");
            prop.load(input);
            retValue = prop.getProperty(key);
            //log.info("ReadFile::::::::::::::::::::::::::::::: KEY  " + key + "  retValue is " + retValue);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                    prop.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return retValue;
    }
    public static String phoneTrimmer(String phone){
        String cleanphone = phone;
        //08182120030
        try{
            if (phone.length() == 13){
                //  logger.info("phone len equals " + cleanphone);
                return cleanphone;
            }else if(phone.length()==11){
                cleanphone = "234"+phone.substring(1);
                return cleanphone;
            }

            else if(phone.length() > 13){
                // logger.info("phone len more than 13 " + cleanphone);

                cleanphone = phone.indexOf(",") > 0 ? phone.substring(0, phone.indexOf(",")):phone.substring(0,13);
                // logger.info("phone len cleaned up" + cleanphone);
                if(cleanphone.length() < 13){
                    //logger.info("phone len less than 13 after cleanup1 " + cleanphone);
                    cleanphone = "234"+cleanphone.substring(1);
                    //  logger.info("phone len cleaned up " + cleanphone);
                }
                // logger.info("phone len final clean up " + cleanphone);
                return cleanphone;
            } else if(phone.length() < 13){
                // logger.info("phone len less than 13 " + cleanphone);
                cleanphone = "234"+phone.substring(0);
                // logger.info("phone len clean " + cleanphone);
                return cleanphone;
            }
        }catch(Exception e){
            e.printStackTrace();
            return cleanphone;
        }
        return cleanphone;

    }
    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static String PBEncrypt(String str,String passPhrase) {
        Cipher ecipher;
        Cipher dcipher;

        byte[] salt = {
                (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
        };

        int iterationCount = 19;

        try {
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            if(str!=null){
                byte[] utf8 = str.getBytes("UTF8");
                byte[] enc = ecipher.doFinal(utf8);
                return new sun.misc.BASE64Encoder().encode(enc);
            }
        } catch (InvalidAlgorithmParameterException e) {
            log.info("EXCEPTION: InvalidAlgorithmParameterException");
        } catch (InvalidKeySpecException e) {
            log.info("EXCEPTION: InvalidKeySpecException");
        } catch (NoSuchPaddingException e) {
            log.info("EXCEPTION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            log.info("EXCEPTION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            log.info("EXCEPTION: InvalidKeyException");
        } catch (Exception ee){;}

        return null;
    }
    public static String PBDecrypt(String str,String passPhrase) {
        Cipher ecipher;
        Cipher dcipher;

        byte[] salt = {
                (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
        };

        int iterationCount = 19;

        try {
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            if(str!=null){
                byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
                byte[] utf8 = dcipher.doFinal(dec);
                return new String(utf8, "UTF8");
            }
        } catch (InvalidAlgorithmParameterException e) {
            log.info("EXCEPTION: InvalidAlgorithmParameterException");
        } catch (InvalidKeySpecException e) {
            log.info("EXCEPTION: InvalidKeySpecException");
        } catch (NoSuchPaddingException e) {
            log.info("EXCEPTION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            log.info("EXCEPTION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            log.info("EXCEPTION: InvalidKeyException");
        }catch (Exception ee){;}

        return null;
    }

    public static String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] stringUpper = new char[4];
        char[] stringLower = new char[4];
        char[] stringNum = new char[2];
        char[] stringChar = new char[1];
        int x =0;
        for (int i = 0; i < stringUpper.length; i++) //3
        {
            Random random = new Random();
            x = random.nextInt(chars.length());
            stringUpper[i] = chars.charAt(x);
        }
        chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < stringLower.length; i++) //3
        {
            Random random = new Random();
            x = random.nextInt(chars.length());
            stringLower[i] = chars.charAt(x);
        }
        chars = "0123456789";
        for (int i = 0; i < stringNum.length; i++) //3
        {
            Random random = new Random();
            x = random.nextInt(chars.length());
            stringNum[i] = chars.charAt(x);
        }
        chars ="@#*!";
        for (int i = 0; i < stringChar.length; i++) //3
        {
            Random random = new Random();
            x = random.nextInt(chars.length());
            stringChar[i] = chars.charAt(x);
        }

        String[] finals = new String[]{new String(stringUpper), new String(stringLower),
                new String(stringNum), new String(stringChar)};
        String[] components = new String[4];
        for(int i = 0; i < components.length; i++) {
            Random random = new Random();
            do {
                x = random.nextInt(components.length);
            } while(components[x] != null);
            components[i] = finals[x];
        }

        String finalString = "";
        for(String component : components)
            finalString += component;

        return finalString;
    }

    public static String flattenStringArray(String[] values)
    {
        String outcome = "";
        for(String value : values) {
            outcome += (outcome.isEmpty() ? ""+ value : ("," + value));
            log.info("Outcome is : "+outcome);
        }

        return outcome;
    }

    public static String flattenStringList(List<String> values)
    {
        String outcome = "";
        for(String value : values) {
            outcome += (outcome.isEmpty() ? ""+ value : ("," + value));
            log.info("Outcome is : "+outcome);
        }

        return outcome;
    }
}
