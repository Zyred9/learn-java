package com.example.learn.es;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class EsService {

    @Autowired
    private EsUtil esUtil;

    public List<Book> getAll() {
        return esUtil.search(EsUtil.INDEX_NAME, new SearchSourceBuilder(), Book.class);
    }

    public Book getByBookId(int bookId) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(new TermQueryBuilder("bookId", bookId));
        List<Book> res = esUtil.search(EsUtil.INDEX_NAME, builder, Book.class);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }

    public List<Book> searchByKey(String key) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", key));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(10).query(boolQueryBuilder);
        return esUtil.search(EsUtil.INDEX_NAME, builder, Book.class);
    }

    public IndexResponse putOne(Book book) {
        EsEntity<Book> entity = new EsEntity<>(book.getBookId() + "", book);
        return esUtil.insertOrUpdateOne(EsUtil.INDEX_NAME, entity);
    }

    public BulkResponse putBatch(List<Book> books) {
        List<EsEntity> list = new ArrayList<>();
        books.forEach(item -> list.add(new EsEntity<>(item.getBookId() + "", item)));
        return esUtil.insertBatch(EsUtil.INDEX_NAME, list);
    }

    public BulkByScrollResponse deleteById(int id) {
        return esUtil.deleteByQuery(EsUtil.INDEX_NAME, new TermQueryBuilder("bookId", id));
    }

    public BulkResponse deleteBatch(List<Integer> list) {
        return esUtil.deleteBatch(EsUtil.INDEX_NAME, list);
    }

}