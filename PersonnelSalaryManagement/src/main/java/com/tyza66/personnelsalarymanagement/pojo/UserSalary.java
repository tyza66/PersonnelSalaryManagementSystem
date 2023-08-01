package com.tyza66.personnelsalarymanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:01
 * Github: https://github.com/tyza66
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSalary {
    Integer id;
    String name;
    BigDecimal salary;
    String year;
    String month;
}
