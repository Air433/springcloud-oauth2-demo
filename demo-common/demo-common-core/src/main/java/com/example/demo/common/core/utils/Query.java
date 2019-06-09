package com.example.demo.common.core.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.core.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author oyg
 * @Date 2018/8/5/17:10
 */
public class Query<T> extends LinkedHashMap<String, Object> {


    public <K extends BasePageModel> IPage<T> getPage(K k){
        return this.getPage(k, null, false);
    }

    public <K extends BasePageModel> IPage<T> getPage(K k, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (k.getPage()!=null){
            curPage = k.getPage();
        }

        if (k.getLimit() !=null){
            limit = k.getLimit();
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);


        String orderField = SQLFilter.sqlInject(k.getOrderField());

        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(k.getOrder())){
            if(Constant.ASC.equalsIgnoreCase(k.getOrder())) {
                return page.setAsc(orderField);
            }else {
                return page.setDesc(orderField);
            }
        }

        //默认排序
        if(isAsc) {
            page.setAsc(defaultOrderField);
        }else {
            page.setDesc(defaultOrderField);
        }

        return page;
    }

}
