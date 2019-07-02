package com.example.demo.common.core.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.core.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author oyg
 * @Date 2018/8/5/17:10
 */
public class Query {

    public  static <T, K extends BasePageModel> IPage<T> getPage(K pageModel, Class<T> tClass){
        return getPage(pageModel, null, false, tClass);
    }

    public static <T, K extends BasePageModel> IPage<T> getPage(K k, String defaultOrderField, boolean isAsc, Class<T> tClass) {
        //分页参数
        long curPage = Optional.ofNullable(k.getPage()).orElse(1L);

        long limit = Optional.ofNullable(k.getLimit()).orElse(10L);

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
