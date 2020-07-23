package com.book.pojo;

import java.util.List;

public class Page<T> {

    public static final Integer DEFAULT_PAGE_SIZE = 4;
    public static final Integer DEFAULT_MAX_PAGE_SIZE = 20;

    //当前页码
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //当前页面显示数量
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    //总记录数
    private Integer pageTotalCount;
    //当前页面数据
    private List<T> items = null;

    //分页条的请求地址
    private String url;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public void selfCheck(){
        if(pageNo < 1){
           pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }
        if(pageSize < 0){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if(pageSize > DEFAULT_MAX_PAGE_SIZE){
            pageSize = DEFAULT_MAX_PAGE_SIZE;
        }
    }

    public Integer getBeginItemIndex(){
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
