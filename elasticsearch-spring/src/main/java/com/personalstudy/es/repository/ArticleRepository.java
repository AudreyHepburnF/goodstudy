package com.personalstudy.es.repository;

import com.personalstudy.es.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ：congyaozhu
 * @date ：Created in 2019/9/15 19:25
 * @description： elasticsearch dao
 * @version: $
 */
public interface ArticleRepository extends ElasticsearchRepository<Article , Long> {
}
