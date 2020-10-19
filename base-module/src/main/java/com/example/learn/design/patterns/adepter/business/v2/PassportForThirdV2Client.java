package com.example.learn.design.patterns.adepter.business.v2;

import com.example.learn.design.patterns.adepter.business.ResultMsg;
import com.example.learn.design.patterns.adepter.business.v2.adapters.AbstractAdapter;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:47
 * @company 中再云图技术有限公司
 **/
public class PassportForThirdV2Client {


    public static void main(String[] args) {
        IPassportForThird adapter = new PassportForThirdAdapter();
        ResultMsg resultMsg = adapter.loginForQQ("1231313123123");
        System.out.println(resultMsg);
    }
}
