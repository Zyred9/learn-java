package com.example.mybatis.plugin.util;

/**
 * <p>
 *      分页工具
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/15 15:21
 * @company 中再云图技术有限公司
 **/
public class PageUtil {

    private static final ThreadLocal<Page> local_page = new ThreadLocal<>();

    public static void setPage(int offset, int limit){
        Page page = new Page();
        page.setOffset(offset);
        page.setLimit(limit);
        local_page.set(page);
    }

    public static void remove(){
        local_page.remove();
    }
    
    public static Page getPage(){
        return local_page.get();
    }
}
