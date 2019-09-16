package com.goodstudy.pojo;

/**
 * @author ：congyaozhu
 * @date ：Created in 2019/9/14 22:55
 * @description：
 * @version: $
 */
public class Article {

    private Long id;

    private String title;

    private String context;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
