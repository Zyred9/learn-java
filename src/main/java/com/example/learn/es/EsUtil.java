package com.example.learn.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.*;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.*;
import org.elasticsearch.search.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/31 15:50
 **/
@Component
public class EsUtil {

    @Value("localhost")
    public String host;
    @Value("9200")
    public int port;
    @Value("http")
    public String scheme;
    public static final String INDEX_NAME = "book-index";
    public static final String INDEX_TYPE = "book";
    public static String CREATE_INDEX;
    public static RestHighLevelClient restClient = null;

    private static String readFileToString(String filePath) {
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        try (FileReader reader = new FileReader(file)) {
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = bReader.readLine()) != null) {
                sb.append(s + "\n");
            }
            return sb.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    @PostConstruct
    public void init() {
        CREATE_INDEX = readFileToString("src/main/resources/es.txt");
        System.out.println("CREATE_INDEX = " + CREATE_INDEX);
        try {
            if (restClient != null) {
                restClient.close();
            }
            restClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
            if (this.indexExist(INDEX_NAME)) {
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(INDEX_NAME);
            request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
            request.mapping(CREATE_INDEX, XContentType.JSON);
            CreateIndexResponse res = restClient.indices().create(request, RequestOptions.DEFAULT);
            if (!res.isAcknowledged()) {
                throw new RuntimeException("初始化失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public boolean indexExist(String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        return restClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    public IndexResponse insertOrUpdateOne(String index, EsEntity entity) {
        IndexRequest request = new IndexRequest(index);
        request.id(entity.getId());
        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
        try {
            return restClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BulkResponse insertBatch(String index, List<EsEntity> list) {
        BulkRequest request = new BulkRequest();
        for (EsEntity item : list) {
            String _json = JSON.toJSONString(item.getData());
            String _id = item.getId();
            IndexRequest indexRequest = new IndexRequest(index).id(_id).source(_json, XContentType.JSON);
            request.add(indexRequest);
        }
        try {
            return restClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> search(String index, SearchSourceBuilder searchSourceBuilder, Class<T> resultClass) {
        SearchRequest request = new SearchRequest(index);
        request.source(searchSourceBuilder);
        try {
            SearchResponse response = restClient.search(request, RequestOptions.DEFAULT);

            SearchHits hits1 = response.getHits();
            SearchHit[] hits2 = hits1.getHits();
            List<T> retList = new ArrayList<>(hits2.length);
            for (SearchHit hit : hits2) {
                String strJson = hit.getSourceAsString();
                retList.add(JSON.parseObject(strJson, resultClass));
            }
            return retList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public AcknowledgedResponse deleteIndex(String index) {
        try {
            IndicesClient indicesClient = restClient.indices();
            DeleteIndexRequest request = new DeleteIndexRequest(index);
            AcknowledgedResponse response = indicesClient.delete(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BulkByScrollResponse deleteByQuery(String index, QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(index);
        request.setQuery(builder);
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            BulkByScrollResponse response = restClient.deleteByQuery(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> BulkResponse deleteBatch(String index, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        for (T t : idList) {
            String s = t.toString();
            request.add(new DeleteRequest(index, INDEX_TYPE, t.toString()));
        }
        try {
            BulkResponse response = restClient.bulk(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
