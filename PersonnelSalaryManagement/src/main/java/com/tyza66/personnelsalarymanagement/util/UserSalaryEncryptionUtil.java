package com.tyza66.personnelsalarymanagement.util;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.tyza66.personnelsalarymanagement.pojo.Admin;
import com.tyza66.personnelsalarymanagement.pojo.UserSalary;
import com.tyza66.personnelsalarymanagement.pojo.UserSalaryEncryption;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Author: tyza66
 * Date: 2023/8/11 10:43
 * Github: https://github.com/tyza66
 **/

public class UserSalaryEncryptionUtil {
    public static final String sm4key = "f4af68a69bf4fd6e";

    //加密
    public static UserSalaryEncryption encrypt(String publicKey, UserSalary data) {
        SymmetricCrypto sm4 = SmUtil.sm4(sm4key.getBytes());
        UserSalaryEncryption userSalaryEncryption = new UserSalaryEncryption();
        userSalaryEncryption.setId(data.getId());
        userSalaryEncryption.setName(sm4.encryptHex(data.getName()));
        userSalaryEncryption.setMonth(sm4.encryptHex(data.getMonth()));
        userSalaryEncryption.setYear(sm4.encryptHex(data.getMonth()));
        SM2 sm2util = SmUtil.sm2(null, publicKey);
        userSalaryEncryption.setSalary(sm2util.encryptBcd(String.valueOf(data.getSalary()), KeyType.PublicKey));
        return userSalaryEncryption;
    }

    //解密
    public static UserSalary decrypt(String privateKey, UserSalaryEncryption data) {
        SymmetricCrypto sm4 = SmUtil.sm4(sm4key.getBytes());
        UserSalary userSalary = new UserSalary();
        userSalary.setId(data.getId());
        userSalary.setName(data.getName());
        userSalary.setMonth(sm4.decryptStr(data.getMonth()));
        userSalary.setYear(sm4.decryptStr(data.getYear()));
        SM2 sm2util = SmUtil.sm2(privateKey, null);
        userSalary.setSalary(new BigDecimal(sm2util.decryptStr(data.getSalary(), KeyType.PrivateKey)));
        return userSalary;
    }
}
