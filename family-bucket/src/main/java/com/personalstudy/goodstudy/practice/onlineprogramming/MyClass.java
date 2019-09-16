package com.personalstudy.goodstudy.practice.onlineprogramming;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:29 2019-06-21
 * @Description : 流式编程和lambda表达式,实现目录遍历
 */
public abstract class MyClass {
    public static void main(String[] args) throws IOException {
        Path start = FileSystems.getDefault().getPath("E:\\数据\\桌面暂存");
        Files.walk(start)
                .filter(path -> path.toFile().isFile())
                .filter(path -> path.toString().endsWith(".pdf"))
                .forEach(System.out::println);
    }
}
