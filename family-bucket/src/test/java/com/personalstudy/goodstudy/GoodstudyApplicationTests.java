package com.personalstudy.goodstudy;

import com.personalstudy.goodstudy.base.BlogProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoodstudyApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoodstudyApplicationTests {

    @Autowired
    private BlogProperties blogProperties;


    @Test
    public void testProperties() {
//        System.out.println("名称：" + blogProperties.getName());
//        System.out.println("标题：" + blogProperties.getTitle());

    }

}
