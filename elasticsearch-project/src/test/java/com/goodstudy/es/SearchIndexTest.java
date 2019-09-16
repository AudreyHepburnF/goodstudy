package com.goodstudy.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ：congyaozhu
 * @date ：Created in 2019/9/14 23:19
 * @description： Elasticsearch 查询api
 * @version: $
 */
public class SearchIndexTest {

    TransportClient client;

    @Before
    public void init(){
        // 创建Settings
        Settings settings = Settings.builder()
                .put("cluster.name", "goodstudy")
                .build();

        // 配置连接信息
        client = new PreBuiltTransportClient(settings);
        try {
            client.addTransportAddresses(new TransportAddress(InetAddress.getByName("192.168.5.34"),9301));
            client.addTransportAddresses(new TransportAddress(InetAddress.getByName("192.168.5.34"),9302));
            client.addTransportAddresses(new TransportAddress(InetAddress.getByName("192.168.5.34"),9303));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询功能公共方法
     * @param query
     */
    private void search(QueryBuilder query){
        SearchResponse searchResponse = client.prepareSearch("hello-es")
                .setTypes("article")
                .setQuery(query)
                // 设置分页查询 从0开始
                .setFrom(0)
                // 查询5条记录
                .setSize(5)
                // 执行操作
                .get();

        SearchHits hits = searchResponse.getHits();
        System.out.println("共查询记录：" + hits.getTotalHits());

        Iterator<SearchHit> iterator = hits.iterator();
        while(iterator.hasNext()){
            SearchHit next = iterator.next();
            String sourceAsString = next.getSourceAsString();
            System.out.println("当前记录信息：" + sourceAsString);
            Map<String, Object> map = next.getSourceAsMap();
            System.out.println("输出信息----------->");
            System.out.println("id:" + map.get("id"));
            System.out.println("title : " + map.get("title"));
            System.out.println("context : " + map.get("context"));
        }
        client.close();
    }

    private void searchHighLight(QueryBuilder query , String highLight){
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(highLight);
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        SearchResponse searchResponse = client.prepareSearch("hello-es")
                .setTypes("article")
                .setQuery(query)
                // 设置分页查询 从0开始
                .setFrom(0)
                // 查询5条记录
                .setSize(5)
                .highlighter(highlightBuilder)
                // 执行操作
                .get();

        SearchHits hits = searchResponse.getHits();
        System.out.println("共查询记录：" + hits.getTotalHits());

        Iterator<SearchHit> iterator = hits.iterator();
        while(iterator.hasNext()){
            SearchHit next = iterator.next();
            String sourceAsString = next.getSourceAsString();
            System.out.println("当前记录信息：" + sourceAsString);
            Map<String, Object> map = next.getSourceAsMap();
            System.out.println("输出信息----------->");
            System.out.println("id:" + map.get("id"));
            System.out.println("title : " + map.get("title"));
            System.out.println("context : " + map.get("context"));
            System.out.println("*****************高亮显示*******************");
            Map<String, HighlightField> highlightFields = next.getHighlightFields();
            if(!CollectionUtils.isEmpty(highlightFields)){
                System.out.println("高亮显示的结果： " + highlightFields);
                HighlightField highlightField = highlightFields.get(highLight);
                Text[] fragments = highlightField.getFragments();
                if(fragments != null){
                    String fragment = fragments[0].toString();
                    System.out.println("符合条件的高亮结果：" + fragment);
                }
            }
        }
        client.close();
    }

    @Test
    public void searchById(){
        // 根据id查询
        QueryBuilder query = QueryBuilders.idsQuery().addIds("1","2");
        search(query);
    }

    @Test
    public void searchByTerm(){
        // 参数1： 检索的字段
        // 参数2： 需要检索的值
        QueryBuilder query = QueryBuilders.termQuery("title", "习近平");
        search(query);
    }

    /**
     * 优点：先进行分词再进行检索
     */
    @Test
    public void searchByQueryString(){
        QueryBuilder query = QueryBuilders.queryStringQuery("学生时代的习题");
        search(query);
    }

    @Test
    public void searchByQueryStringAndHighLight(){
        QueryBuilder query = QueryBuilders.queryStringQuery("学生时代的习题");
        searchHighLight(query , "title");
    }
}
