package com.personalstudy.goodstudy.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:49 2019-08-08
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

    @Value("18")
    private int age;

    @Value("zhangsan")
    private String name;
}
