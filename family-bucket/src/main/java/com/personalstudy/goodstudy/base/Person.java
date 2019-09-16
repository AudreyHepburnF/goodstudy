package com.personalstudy.goodstudy.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  11:35 2019-08-07
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    private int age;

    public String sex;
}
