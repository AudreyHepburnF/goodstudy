package com.goodstudy.springbootstudy.config;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author congyaozhu
 * @date 2020-02-23 18:20
 * @description 自定义错误信息
 */
@Configuration
public class ErrorInfoConfig extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        if((Integer)map.get("status") == 500){
            map.put("message", "服务器内部错误");
        }
        return map;
    }
}
