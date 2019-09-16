package com.personalstudy.goodstudy.springaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:53 2019-08-12
 * @Description :
 */
@SpringBootApplication
@EnableConfigurationProperties
public class SpringAOPApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAOPApplication.class, args);
    }
}
