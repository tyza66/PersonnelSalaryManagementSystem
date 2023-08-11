package com.tyza66.personnelsalarymanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Author: tyza66
 * Date: 2023/8/11 11:20
 * Github: https://github.com/tyza66
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSalaryEncryption {
    Integer id;
    String name;
    String salary;
    String year;
    String month;
}
