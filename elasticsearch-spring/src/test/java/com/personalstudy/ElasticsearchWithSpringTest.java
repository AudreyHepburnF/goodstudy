package com.personalstudy;

import com.personalstudy.es.entity.Article;
import com.personalstudy.es.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ：congyaozhu
 * @date ：Created in 2019/9/15 19:46
 * @description： 基于Spring的Elasticsearch操作
 * @version: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ElasticsearchWithSpringTest {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public  void createIndex(){
        elasticsearchTemplate.createIndex(Article.class);
    }
}
