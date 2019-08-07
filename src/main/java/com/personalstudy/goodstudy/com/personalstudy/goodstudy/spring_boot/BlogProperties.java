package com.personalstudy.goodstudy.com.personalstudy.goodstudy.spring_boot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  9:50 2019-06-24
 * @Description :
 */
@Component
@Data
@ConfigurationProperties(prefix = "com.didispace.blog")
//@PropertySource(value = "config.properties")
@Validated
public class BlogProperties {

//    @Value("${com.didispace.blog.name}")
//    @Email(message = "请检查邮箱格式！！！")
    private String name;

//    @Value("${com.didispace.blog.title}")
    private String title;
}
