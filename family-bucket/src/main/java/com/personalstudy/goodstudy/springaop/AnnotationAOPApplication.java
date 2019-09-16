package com.personalstudy.goodstudy.springaop;

import com.personalstudy.goodstudy.springaop.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  17:11 2019-08-12
 * @Description :
 */
@EnableAspectJAutoProxy
@ComponentScan("com.personalstudy.goodstudy.springaop.*")
public class AnnotationAOPApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationAOPApplication.class);
        UserService user = context.getBean(UserService.class);
        user.login();
    }
}
