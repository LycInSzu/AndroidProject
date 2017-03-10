package com.example.lyc.bootymusic.utils.AboutMusic;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：abc on 2017/1/13 10:14
 * 邮箱：liyuchong@kocla.com
 */

//def encrypted_id(id):
//        byte1 = bytearray('3go8&$8*3*3h0k(2)2')
//        byte2 = bytearray(id)
//        byte1_len = len(byte1)
//        for i in xrange(len(byte2)):
//        byte2[i] = byte2[i]^byte1[i%byte1_len]
//        m = md5.new()
//        m.update(byte2)
//        result = m.digest().encode('base64')[:-1]
//        result = result.replace('/', '_')
//        result = result.replace('+', '-')
//        return result
public class MakeMD5 {

   public static final byte[] byte1 = "3go8&$8*3*3h0k(2)2".getBytes();

    public static String makeMD5(String musicID) {
        String id = musicID;
        String result = "失败";
        byte[] byte2 = id.getBytes();
        for (int i = 0; i < byte2.length; i++) {
            byte2[i] = (byte) (byte2[i] ^ byte1[i % byte1.length]);
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(byte2);
            result =  Base64.encodeToString(md.digest(),Base64.DEFAULT);
            result=result.substring(0,result.length()-2);
            result = result.replace('/', '_');
            result = result.replace('+', '-');
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    /***
//     * encode by Base64
//     */
//    public static String encodeBase64(byte[]input) throws Exception{
//        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
//        Method mainMethod= clazz.getMethod("encode", byte[].class);
//        mainMethod.setAccessible(true);
//        Object retObj=mainMethod.invoke(null, new Object[]{input});
//        return (String)retObj;
//    }
//    /***
//     * decode by Base64
//     */
//    public static byte[] decodeBase64(String input) throws Exception{
//        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
//        Method mainMethod= clazz.getMethod("decode", String.class);
//        mainMethod.setAccessible(true);
//        Object retObj=mainMethod.invoke(null, input);
//        return (byte[])retObj;
//    }

}
