/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2014-11-26
 */
package com.myhome.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

/**
 * BASE64 严格地说，属于编码格式，而非加密算法 Base64内容传送编码被设计用来把任意序列的8位字节描述为一种不易被人直接识别的形式 (The
 * Base64 Content-Transfer-Encoding is designed to represent arbitrary sequences
 * of octets in a form that need not be humanly readable.)
 * 
 * @author Flouny.Caesar
 * 
 */
public class BASE64 {

    /**
     * BASE64解密
     * 
     * @param key
     * @return
     */
    public static byte[] decryptBASE64(String key) {
        if (StringUtils.isBlank(key))
            return null;

        try {
            return (new sun.misc.BASE64Decoder()).decodeBuffer(key);
        } catch (IOException e) {
            // ...
        }

        return null;
    }

    /**
     * BASE64加密
     * 
     * @param key
     * @return
     */
    public static String encryptBASE64(byte[] key) {
        try {
            return (new sun.misc.BASE64Encoder()).encodeBuffer(key);
        } catch (Exception e) {
            // ...
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            StringBuilder pictureBuffer = new StringBuilder();
            InputStream input = new FileInputStream(new File("/home/goujia/project/goujia/html/store/website/uploaded/11111/3.jpg"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            for (int len = input.read(temp); len != -1; len = input.read(temp)) {
                out.write(temp, 0, len);
                pictureBuffer.append(encryptBASE64(out.toByteArray()));
                // out(pictureBuffer.toString());
                out.reset();
            }
            System.out.println(pictureBuffer.toString());
            //ImageUpload.imageUpload(pictureBuffer.toString());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}