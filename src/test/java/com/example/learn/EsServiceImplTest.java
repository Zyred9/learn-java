package com.example.learn;

import com.alibaba.fastjson.JSONObject;
import com.example.learn.es.EsService;
import com.example.learn.es.QueryPage;
import com.example.learn.es.Local;
import com.example.learn.es.search.EsUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EsServiceImplTest {

    @Autowired
    private EsUtil esUtil;

    @Autowired
    private EsService esService;

    @Test
    public void insert() {
        List<Local> students = new ArrayList<>();
        for (int i = 10; i <= 12; i++) {
            Local student = new Local();
            students.add(student);
        }
        esService.addAll(students);
    }

    @Test
    public void fuzzySearch() {
        QueryPage queryPage = new QueryPage();
        queryPage.setCurrent(1).setSize(5);
        Page<Local> list = esService.search("张永红", queryPage);
        list.forEach(System.out::println);
    }

    @Test
    public void searchByUserIdAndName() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("username", "zyh"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("comment", "张永红"));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(10).query(boolQueryBuilder);
        List<JSONObject> search = esUtil.search(EsUtil.INDEX_NAME, builder, JSONObject.class);

        search.stream().forEach(System.out::println);

    }
}
