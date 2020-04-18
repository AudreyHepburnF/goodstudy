package com.personalstudy.goodstudy.springmvc;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

/**
 * @author congyaozhu
 * @date 2020-02-23 14:08
 * @description 全局自定义异常处理
 */
@ControllerAdvice
public class MyGlobalExceptionHandler {

    // 定义全局异常处理，将异常信息添加到ModelAndView中
    @ExceptionHandler(Exception.class)
    public ModelAndView customException(Exception e){
        e.getCause();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message" , e.getMessage());
        modelAndView.setViewName("myerror");
        return modelAndView;
    }

    // 定义全局数据绑定，主要用于初始化的数据操作，这样在每一个Controller的接口中，就都能够访问到这些数据
    // 使用时，只需要添加Model形参，即可获取全局内容，key为globalData
    // Map<String, Object> map = model.asMap();
    @ModelAttribute(name = "globalData")
    public Map<String , Object> mydata(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }

// ------------------------- 数据预处理 start---------------------------

    /**
     * 经典案例：当两个接口中存在共同的属性字段时，前端传递的时候无法区分
     * 解决方法：使用@ControllerAdvice的全局数据预处理可以解决该问题
     * @param book
     * @param author
     */
    @PostMapping("/book")
    public void addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") TestInitializer author) {
        System.out.println(book);
        System.out.println(author);
    }

    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }

    // 前端传递时，只需要给不同的对象添加不同的前缀，可以实现参数的区分
// -------------------------- 数据预处理 end--------------------------------

}
