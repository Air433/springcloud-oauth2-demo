package com.example.demo.common.core.utils;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Author oyg
 * @Date 2018/7/29/21:51
 */
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<?> list;

    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.list = list;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public PageUtils(Page<?> page){
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = (int)page.getPages();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
