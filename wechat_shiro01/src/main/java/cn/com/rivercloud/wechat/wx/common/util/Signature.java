package cn.com.rivercloud.wechat.wx.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Signature {

    /**
     *  1）将token、timestamp、nonce三个参数进行字典序排序
     *  2）将三个参数字符串拼接成一个字符串进行sha1加密
     *  3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    public static boolean checkSignature(String token, String signature, String timestamp, String nonce){
        String[] arr = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(arr);

        StringBuilder content = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            //SHA-1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        // 比对 判断
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()): false;
    }

    private static String byteToStr(byte[] digest) {
        String strDigest = "";
        for(int i = 0; i < digest.length; i++){
            strDigest += byteToHexStr(digest[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte b) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b >>> 4) & 0X0F];
        tempArr[1] = Digit[b & 0X0F];
        String s = new String(tempArr);
        return s;
    }

}
