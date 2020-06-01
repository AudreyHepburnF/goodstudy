package com.interview.javabasic.reflect.demo;

import java.io.*;

/**
 * @author congyaozhu
 * @date 2020-06-01 22:57
 * @description 自定义类加载器
 * 核心方法：
 *  1. findClass 查找类信息(路径)
 *  2. defineClass 定义类信息(Java实现)
 */
public class MyClassLoader extends ClassLoader{

    // 路径
    private String path;
    // 文件名
    private String classLoaderName;

    public MyClassLoader(String path, String name) {
        this.path = path;
        this.classLoaderName = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {

        // 文件路径(全路径)
        classLoaderName = path + name + ".class";
        // 文件输入流
        InputStream in = null;
        // 文件输出流
        ByteArrayOutputStream bos = null;

        try {
            in = new FileInputStream(new File(classLoaderName));
            bos = new ByteArrayOutputStream();
            int i = 0;
            while ( (i = in.read()) != -1 ) {
                bos.write(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bos.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }


}
