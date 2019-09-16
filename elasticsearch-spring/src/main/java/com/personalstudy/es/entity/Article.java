package com.personalstudy.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author ：congyaozhu
 * @date ：Created in 2019/9/15 19:25
 * @description：
 * @version: $
 */
@Document(indexName = "hello-springes" , type = "article")
public class Article {

    @Id
    @Field(type = FieldType.Long , store = true)
    private Long id;

    @Field(type = FieldType.Text , store = true , analyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text , store = true , analyzer = "ik_smart")
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
