package com.commons.codecDemo;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.codec.digest.Sha2Crypt;
import org.apache.commons.codec.net.URLCodec;

import java.io.UnsupportedEncodingException;

/**
 * Title:
 * Description: 测试commons-codec中的加密与解密方法
 * <p>
 * 均可以使用这些编码对字符串进行编码，将包含特殊字符的字符串编码之后存放到JSON串中
 * <p>
 * Project: MyShiroApplication
 * Create User: 王杰
 * Create Time: 2017/3/8
 */
public class CodecDemo {

    public static void main(String[] args) {

        String encodeStr = "Hello World";
        String charsetName = "UTF-8";

//        Base64Test(encodeStr, charsetName);
//        HexTest();
//        URLCodecTest();

        MD5Test(encodeStr,charsetName);

    }

    /**
     * 使用Base64进行编码与解码
     * 可以使用这个方式将字符串中的特殊字符进行编码，存放到JSON串中
     */
    public static void Base64Test(String encodeStr, String charsetName) {
        try {
            Base64 base64 = new Base64();
            String decodeStr = base64.encodeAsString(encodeStr.getBytes(charsetName));
            System.out.println(encodeStr + " 使用Base64编码之后：" + decodeStr);
            String encodeStr1 = new String(base64.decode(decodeStr), charsetName);
            System.out.println(decodeStr + " 使用Base64解码之后：" + encodeStr1);
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持的编码字符集");
        }

    }

    /**
     * 使用Hex进行编码和解码
     */
    public static void HexTest(String encodeStr, String charsetName) {
        try {
            Hex hex = new Hex();
            String decodeStr = new String(hex.encode(encodeStr.getBytes(charsetName)));
            System.out.println(encodeStr + " 使用Hex编码之后：" + decodeStr);
            String encodeStr1 = new String(hex.decode(decodeStr.getBytes(charsetName)), charsetName);
            System.out.println(decodeStr + " 使用Hex解码之后：" + encodeStr1);
        } catch (DecoderException e) {
            System.out.println("使用Hex进行转码失败");
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持的编码字符集");
        }
    }

    /**
     * 测试URLCodec进行编码和解码
     */
    public static void URLCodecTest(String encodeStr, String charsetName) {
        try {
            URLCodec codec = new URLCodec();
            String decodeStr = codec.encode(encodeStr, charsetName);
            System.out.println(encodeStr + " 使用URLCodec编码之后：" + decodeStr);
            String encodeStr1 = codec.decode(decodeStr, charsetName);
            System.out.println(decodeStr + " 使用URLCodec解码之后：" + encodeStr1);
        } catch (DecoderException e) {
            System.out.println("使用URLCodec解码出错");
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持的编码字符集");
        }
    }

    /**
     * 测试MD5、Sh2等安全策略的编码
     */
    public static void MD5Test(String encodeStr,String charsetName) {
        try{
            String decode2 = DigestUtils.md2Hex(encodeStr);
            System.out.println(encodeStr + " 使用md2Hex方法进行编码：" + decode2);

            String decode4 = DigestUtils.md5Hex(encodeStr);
            System.out.println(encodeStr + " 使用md5Hex方法进行编码：" + decode4);

            String decode6 = DigestUtils.sha1Hex(encodeStr);
            System.out.println(encodeStr + " 使用sha1Hex方法进行编码：" + decode6);

            String decode8 = DigestUtils.sha256Hex(encodeStr);
            System.out.println(encodeStr + " 使用sha256Hex方法进行编码：" + decode8);

            String decode10 = DigestUtils.sha384Hex(encodeStr);
            System.out.println(encodeStr + " 使用sha384Hex方法进行编码：" + decode10);

            String decode12 = DigestUtils.sha512Hex(encodeStr);
            System.out.println(encodeStr + " 使用sha512Hex方法进行编码：" + decode12);

            String decode14= Sha2Crypt.sha256Crypt(encodeStr.getBytes(charsetName));
            System.out.println(encodeStr + " 使用Sha2Crypt.sha256Crypt方法进行编码："+decode14);

            //salt必须以$5$开头，并且至少有一个字符
            String decode16=Sha2Crypt.sha256Crypt(encodeStr.getBytes(charsetName),"$5$3434()~!`123#$%%^&**()_3456788990;:?/|@@@!!#@#@%^&$##*)&");
            System.out.println(encodeStr + " 使用Sha2Crypt.sha256Crypt方法(添加盐salt)进行编码："+decode16);

            String decode18 =Sha2Crypt.sha512Crypt(encodeStr.getBytes(charsetName));
            System.out.println(encodeStr + " 使用Sha2Crypt.sha512Crypt方法进行编码："+decode18);

            String decode20 =Sha2Crypt.sha512Crypt(encodeStr.getBytes(charsetName),"$5$aseq2311!~~~``");
            System.out.println(encodeStr + " 使用Sha2Crypt.sha512Crypt(添加盐salt)方法进行编码："+decode20);

            String decode22= Md5Crypt.apr1Crypt(encodeStr.getBytes(charsetName));
            System.out.println(encodeStr + " 使用Md5Crypt.apr1Crypt方法进行编码："+decode22);


//            final Pattern p = Pattern.compile("^" + prefix.replace("$", "\\$") + "([\\.\\/a-zA-Z0-9]{1,8}).*");
//            final Matcher m = p.matcher(salt);
            //初步测试：不能以$开始，任意长度
            String decode24= Md5Crypt.apr1Crypt(encodeStr.getBytes(charsetName),"sjdas$kdjasd");
            System.out.println(encodeStr + " 使用Md5Crypt.apr1Crypt(添加盐salt)方法进行编码："+decode24);

            String decode26=Md5Crypt.md5Crypt(encodeStr.getBytes(charsetName));
            System.out.println(encodeStr + " 使用Md5Crypt.md5Crypt方法进行编码："+decode26);

            //必须以 $1$ 开头
            String decode28=Md5Crypt.md5Crypt(encodeStr.getBytes(charsetName),"$1$dfdsf");
            System.out.println(encodeStr + " 使用Md5Crypt.md5Crypt(添加盐salt)方法进行编码："+decode28);

        }catch (UnsupportedEncodingException e){

        }
    }


}
