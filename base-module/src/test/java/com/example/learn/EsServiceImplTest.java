package com.example.learn;

import com.alibaba.fastjson.JSONObject;
import com.example.learn.es.search.EsUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EsServiceImplTest {

    @Autowired
    private EsUtil esUtil;


    @Test
    public void searchByUserIdAndName() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
//                .must(QueryBuilders.termQuery("username", "zyh"))
                .must(QueryBuilders.matchQuery("comment", "员"));

        SearchSourceBuilder builder = new SearchSourceBuilder()
                .size(10)
                .query(boolQueryBuilder);

        List<JSONObject> search = esUtil.search(EsUtil.INDEX_NAME, builder, JSONObject.class);
        search.stream().forEach(System.out::println);
    }
}
