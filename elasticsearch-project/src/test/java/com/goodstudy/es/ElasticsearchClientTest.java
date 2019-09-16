package com.goodstudy.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodstudy.pojo.Article;
import com.goodstudy.util.GeneChar;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

/**
 * @author ：congyaozhu
 * @date ：Created in 2019/9/14 20:14
 * @description：使用Java方式创建Elasticsearch索引
 * @version: 0.0.1$
 */
public class ElasticsearchClientTest {

    TransportClient transport;
    @Before
    public void init() throws Exception{
        // 创建Settings
        Settings settings = Settings.builder()
                .put("cluster.name", "goodstudy")
                .build();

        // 配置连接信息
        transport = new PreBuiltTransportClient(settings);
        transport.addTransportAddresses(new TransportAddress(InetAddress.getByName("192.168.5.34"),9301));
        transport.addTransportAddresses(new TransportAddress(InetAddress.getByName("192.168.5.34"),9302));
        transport.addTransportAddresses(new TransportAddress(InetAddress.getByName("192.168.5.34"),9303));
    }

    @Test
    public void createIndex() throws Exception{

        // 索引的新增删除 需要获取管理员权限
        transport.admin().indices().prepareCreate("hello-es")
                // 执行操作
                .get();
        // 关闭连接
        transport.close();
    }

    @Test
    public void setMappings() throws Exception{

        // 构建json对象
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                    .startObject("article")
                        .startObject("properties")
                            .startObject("id")
                                .field("type","long")
                                .field("store", true)
                            .endObject()
                            .startObject("title")
                                .field("type", "text")
                                .field("store", true)
                                .field("analyzer", "ik_smart")
                            .endObject()
                            .startObject("content")
                                .field("type", "text")
                                .field("store", true)
                                .field("analyzer", "ik_smart")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();
        transport.admin().indices().preparePutMapping("hello-es")
                .setType("article")
                .setSource(builder)
                .get();
        transport.close();
    }

    @Test
    public void addDocument() throws Exception{
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                    .field("id", 1l)
                    .field("title", "三张海报读懂习近平香山之行")
                    .field("context", "中共中央在北京香山虽然只有半年时间，但这里是我们党领导解放战争走向全国胜利、新民主主义革命取得伟大胜利的总指挥部，是中国革命重心从农村转向城市的重要标志，在中国共产党历史、中华人民共和国历史上具有非常重要的地位。")
                .endObject();
        transport.prepareIndex()
                .setIndex("hello-es")
                .setType("article")
                .setId("1")
                .setSource(builder)
                .get();
        transport.close();
    }

    @Test
    public void addDocument2() throws Exception{
        // 新建Article对象
        Article article = new Article();
        article.setId(3l);
        article.setTitle("中秋佳节 听习近平讲中华优秀传统文化");
        article.setContext("习近平香山之行的讲话引发强烈反响 教育均衡发展");

        ObjectMapper mapper = new ObjectMapper();
        String document = mapper.writeValueAsString(article);
        transport.prepareIndex("hello-es", "article", "3")
                .setSource(document, XContentType.JSON)
                .get();
        transport.close();
    }

    /**
     * 初始化10W条测试数据
     * @throws Exception
     */
    @Test
    public void addDocument3() throws Exception{
        for (int i = 5; i < 100000; i++) {
            // 新建Article对象
            Article article = new Article();
            article.setId(Long.parseLong(i + ""));
            article.setTitle("随机生成标题 :" + i + " " + GeneChar.getRandomCharPlus() + ".....");
            article.setContext("随机生成内容 :" + i + " " + GeneChar.getRandomCharPlus() + ".....");
            ObjectMapper mapper = new ObjectMapper();
            String document = mapper.writeValueAsString(article);
            transport.prepareIndex("hello-es", "article", i + "")
                    .setSource(document, XContentType.JSON)
                    .get();
//            transport.close();
        }
        transport.close();
    }
}
