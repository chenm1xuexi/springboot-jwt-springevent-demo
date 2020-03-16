package com.feifei.jwtdemo.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
public class Md5Util {

    /**
     * 对字符串进行md5加密
     *
     * @author shixiongfei
     * @date 2020-03-16
     * @updateDate 2020-03-16
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public static String crypt(String str) {
        if (StringUtils.isBlank(str)) {
            throw new RuntimeException("字符串格式错误");
        }

        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            // 返回16位的字节数组
            byte[] hash = md.digest();
            // 将加密后的字节数组以15进制字符串输出
            for (byte value : hash) {
                if ((0xff & value) < 0x10) {
                    hexString.append("0").append(Integer.toHexString(0xff & value));
                } else {
                    hexString.append(Integer.toHexString(0xff & value));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }
}
