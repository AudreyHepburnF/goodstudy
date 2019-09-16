package com.personalstudy.goodstudy.springaop.aspect;

import com.personalstudy.goodstudy.springaop.service.UserService;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  10:32 2019-08-13
 * @Description :
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        byte[] bytes = ProxyGenerator.generateProxyClass("$ProxyXX", new Class[]{UserService.class});
        FileOutputStream fileOutputStream = new FileOutputStream("$ProxyTest.class");
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
