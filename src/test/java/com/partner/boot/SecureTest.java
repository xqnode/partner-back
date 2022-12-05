package com.partner.boot;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.DES;
import org.junit.jupiter.api.Test;

public class SecureTest {

    @Test
    public void test() {
        // 定义秘钥和明文
        String key = "123456";
        String text = "Sa-Token 一个轻量级java权限认证框架";

// 加密
        String ciphertext = SaSecureUtil.aesEncrypt(key, text);
        System.out.println("AES加密后：" + ciphertext);

// 解密
        String text2 = SaSecureUtil.aesDecrypt(key, ciphertext);
        System.out.println("AES解密后：" + text2);

    }

    @Test
    public void test1() throws Exception {
        String password = "123456";
        DES des = SecureUtil.des("asds()<>an>>//ks__ajbdn2#$jsanblaksdl".getBytes());
        String encode = des.encryptHex(password);  // 5cccfdd3deb40538  2a5a251979ba2333
        System.out.println(encode);
        String decode = des.decryptStr(encode);
        System.out.println(decode);
    }

}
