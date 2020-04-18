package com.personalstudy.springcloud.eureka;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author congyaozhu
 * @date 2020-01-14 9:37
 * @description ControllerAdvice注解：增强的Controller。
 * 可以实现三个方面的功能更：
 *  1. 全局异常处理 (@ExceptionHandler)
 *  2. 全局数据绑定 (@ModelAttribute) : 标记该方法的返回数据是一个全局数据，默认情况下，这个全局数据的 key 就是返回的变量名，value 就是方法返回值，当然开发者可以通过 @ModelAttribute 注解的 name 属性去重新指定 key。
 *  3. 全局数据预处理 (@InitBinder)
 */
@ControllerAdvice
public class AdviceConfig {

    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }

    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }
}
