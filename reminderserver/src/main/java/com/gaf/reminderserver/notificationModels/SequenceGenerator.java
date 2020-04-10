/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaf.reminderserver.notificationModels;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

/**
 *
 * @author tnwosu
 */
public class SequenceGenerator {
    final static Logger logger = LoggerFactory.getLogger(SequenceGenerator.class);
    private static long getCurrentTime() {
        long current = 0;
        current = System.currentTimeMillis();
        return current++;
    }

    private static String getRMIID() {
        String rmiID = "";
        try {
            rmiID = new java.rmi.dgc.VMID().toString();
        } catch (Exception ex) {

        }
        return rmiID;
    }

    private static String getSecureRandom() {
        String strTemp = "";
        try {
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
            strTemp = Integer.toHexString(prng.nextInt());
        } catch (Exception ex) {
           logger.error(ex.getMessage());
        }
        return strTemp;
    }

    public static String GenerateUniqueCode18digit() {
        String key = "";
        try {
            Long lngTime = new Long(getCurrentTime());
            String strTime = lngTime.toString();
            int l = strTime.length();
            String str1 = strTime.substring(l - 4, l);
            String str = getRMIID();
            l = str.length();
            String str3 = str.substring(l - 4, l);
            String str2 = str.substring(0, 4);
            str = getSecureRandom();
            l = str.length();
            if (l > 6) {
                l = 6;
            }
            String str4 = str.substring(0, l);
            key = str1 + str2 + str3 + str4;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return key.toUpperCase();
    }

    public static String GenerateUniqueCode16digit() {
        String key = "";
        try {
            String strTime = String.valueOf(getCurrentTime());
            int l = strTime.length();
            String str1 = strTime.substring(l - 4, l);
            String str = getRMIID();
            l = str.length();
            String str3 = str.substring(l - 4, l);
            String str2 = str.substring(0, 4);
            str = getSecureRandom();
            l = str.length();
            if (l > 4) {
                l = 4;
            }
            String str4 = str.substring(0, 4);
            key = str1 + str2 + str3 + str4;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return key.toUpperCase();
    }

    public static String GenerateUniqueCode12digit() {
        String key = "";
        try {
            Long lngTime = new Long(getCurrentTime());
            String strTime = lngTime.toString();
            int l = strTime.length();
            String str1 = strTime.substring(l - 4, l);
            String str = getRMIID();
            l = str.length();
            String str3 = str.substring(l - 4, l);
            String str2 = str.substring(0, 4);

            str = getSecureRandom();
            l = str.length();
            if (l > 8) {
                l = 8;
            }
            String str4 = str.substring(0, l);
            String str5 = str.substring(l - 4, l);
            key = str1 + str3 + str5;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return key.toUpperCase();
    }

    public static String GenerateUniqueCode8digit() {
        String key = "";
        try {
            Long lngTime = new Long(getCurrentTime());
            String strTime = lngTime.toString();
            int l = strTime.length();
            String str1 = strTime.substring(l - 3, l);
            String str = getRMIID();
            l = str.length();
            String str3 = str.substring(l - 3, l);
            String str2 = str.substring(0, 4);
            str = getSecureRandom();
            l = str.length();
            if (l > 8) {
                l = 8;
            }
            String str4 = str.substring(0, l);
            String str5 = str.substring(l - 3, l);
            key = str1 + str3 + str5;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return key.toUpperCase();
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(getRMIID());
//        System.out.println(Long.toHexString(System.currentTimeMillis()));
        for (int i = 0; i < 5; i++) {
            logger.info("'" + GenerateUniqueCode12digit() + "', ");
            Thread.sleep(2000);
        }
    }
}
