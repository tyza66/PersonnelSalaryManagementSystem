package com.tyza66.personnelsalarymanagement.util;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.tyza66.personnelsalarymanagement.pojo.Admin;
import com.tyza66.personnelsalarymanagement.pojo.UserSalary;
import com.tyza66.personnelsalarymanagement.pojo.UserSalaryEncryption;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/11 10:43
 * Github: https://github.com/tyza66
 **/

public class UserSalaryEncryptionUtil {
    public static final String sm4key = "5000b4519d0790ef";

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
        userSalary.setName(sm4.decryptStr(data.getName()));
        userSalary.setMonth(sm4.decryptStr(data.getMonth()));
        userSalary.setYear(sm4.decryptStr(data.getYear()));
        SM2 sm2util = SmUtil.sm2(privateKey, null);
        userSalary.setSalary(new BigDecimal(sm2util.decryptStr(data.getSalary(), KeyType.PrivateKey)));
        return userSalary;
    }

    //批量解密
    public static List<UserSalary> decrypt(String privateKey, List<UserSalaryEncryption> data) {
        SymmetricCrypto sm4 = SmUtil.sm4(sm4key.getBytes());
        SM2 sm2util = SmUtil.sm2(privateKey, null);
        ArrayList<UserSalary> userSalaries = new ArrayList<UserSalary>();
        for (UserSalaryEncryption userSalaryEncryption : data) {
            UserSalary userSalary = new UserSalary();
            userSalary.setId(userSalaryEncryption.getId());
            userSalary.setName(sm4.decryptStr(userSalaryEncryption.getName()));
            userSalary.setMonth(sm4.decryptStr(userSalaryEncryption.getMonth()));
            userSalary.setYear(sm4.decryptStr(userSalaryEncryption.getYear()));
            userSalary.setSalary(new BigDecimal(sm2util.decryptStr(userSalaryEncryption.getSalary(), KeyType.PrivateKey)));
            userSalaries.add(userSalary);
        }
        return userSalaries;
    }
}
