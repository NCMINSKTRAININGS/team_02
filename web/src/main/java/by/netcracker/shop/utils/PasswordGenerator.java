package by.netcracker.shop.utils;

import by.netcracker.shop.exceptions.UtilException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordGenerator {
    private PasswordGenerator(){}

    public static PasswordGenerator getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public String generatePassword(String password, String salt) throws UtilException {
        String first = md5Digest(password);
        return md5Digest(first + salt);
    }

    public String generateSalt(String password) throws UtilException {
        String first = md5Digest(password);
        return md5Digest(first.substring(0, first.length()/2));
    }

    private String md5Digest(String original) throws UtilException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        }
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private static class SingletonHolder{
        private static final PasswordGenerator INSTANCE = new PasswordGenerator();
    }
}
