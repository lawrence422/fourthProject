package com.intern.tools.utils;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PageUtil<T> {

    private List<T> pageData;
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    private Integer totalCount;

    public int getPageCount() {
        if (this.totalCount % this.pageSize == 0) {
            return this.totalCount / this.pageSize;
        }
        return this.totalCount / this.pageSize + 1;
    }

    public PageUtil(List<T> pageData, Integer totalCount) {
        this.pageData = pageData;
        this.totalCount = totalCount;
    }

    public PageUtil() {
    }

    public boolean isFirst() {
        return (this.currentPage == 1)
                || (this.totalCount == 0);
    }

    public boolean isLast() {
        return (this.totalCount == 0)
                || (this.currentPage >= getPageCount());
    }

    public boolean isHasNext() {
        return this.currentPage < getPageCount();
    }

    public boolean isHasPrev() {
        return this.currentPage > 1;
    }

    public Integer getNextPage() {
        if (this.currentPage >= getPageCount()) {
            return getPageCount();
        }
        return this.currentPage + 1;
    }

    public Integer getPrevPage() {
        if (this.currentPage <= 1) {
            return 1;
        }
        return this.currentPage - 1;
    }

    public List<T> getPageData() {
        return this.pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
