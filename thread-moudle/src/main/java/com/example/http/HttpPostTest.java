package com.example.http;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class HttpPostTest {

    public static void main(String[] args) {
//        String url = "http://localhost:24930/testPost/other";
        String url = "http://123.232.119.120:9999/environment/hzbcompanyinfo/companyInfoExternal";
        String str = "http://123.232.119.120:9999/environment/companyinfo/companyInfoExternal";

//        String json = "{\"name\":\"张三\", \"password\":\"123456\", \"address\":\"重庆市南川区\"}";
        String json = "{\"PROCESS_TYPE\":\"01\",\"data\":[{\"COMPANG_PROP\":null,\"COMPANG_SCALE\":null,\"COMPANG_STATE\":\"2\",\"COMPANG_TYPE\":null,\"COMPANY_NAME\":\"济宁星亚化工有限公司\",\"CONTACT\":\"王奇\",\"DETAILED_ADDRESS\":\"汶上化工园区\",\"EMAIL\":null,\"ENTERPRISES_PARK\":null,\"FAX\":null,\"HAZARDOUS_CHEMICAL_ENTERPRISES\":null,\"HAZARDOUS_CHEMICAL_ENTERPRISES_TYPE\":null,\"HAZARDOUS_CHEMICAL_LICENSE_NUM\":null,\"HAZARDOUS_CHEMICAL_PERMIT_DATE\":null,\"HAZARDOUS_CHEMICAL_USE\":null,\"INDUSTRY_TYPE\":\"C2652\",\"IN_THE_TIME\":null,\"KEY_MONITORING_POINTS\":null,\"LEGAL_CONTACT\":\"王奇\",\"LICENSE_USE\":null,\"PHONE\":\"15866096832\",\"SAFETY_STANDARDS\":null,\"SAFE_USE_LICENSE_NUM\":null,\"SAFE_USE_LICENSE_RXPIRES\":null,\"SUBORDINATE_PARK\":null,\"THREE_BATCH_SUBDIVISIONS\":null,\"URL\":null,\"USCC\":\"91370830MA3C4KWC1P\",\"VILLAGE_ID\":null}],\"sign\":\"ofSZQmh/SawL+yXdCPx+XA==\"}";

        post2(url, json);
    }

    private static void post1 (String url, String json){
        HttpClient client = HttpClientBuilder.create().build();
        org.apache.http.client.methods.HttpPost post = new HttpPost(url);
        StringBuilder sb = new StringBuilder();
        try {
            StringEntity s = new StringEntity(json);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json;charset=utf-8");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                String result = EntityUtils.toString(res.getEntity());
                sb.append(result);
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void post2 (String url, String json){
        System.out.println(HttpUtil.post(url, json));
    }

}
