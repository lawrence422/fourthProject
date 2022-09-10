package com.intern.tools.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Locale;

@Component
public class RandomUtil {

    public static String createVerificationCode(){
        SecureRandom random=new SecureRandom();
        return new BigInteger(30,random).toString(32).toUpperCase();
    }


}
