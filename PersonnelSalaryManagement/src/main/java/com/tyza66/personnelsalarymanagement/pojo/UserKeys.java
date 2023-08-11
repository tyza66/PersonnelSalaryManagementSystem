package com.tyza66.personnelsalarymanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: tyza66
 * Date: 2023/8/11 11:58
 * Github: https://github.com/tyza66
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserKeys {
    Integer id;
    String name;
    String privatekey;
    String publickey;
}
