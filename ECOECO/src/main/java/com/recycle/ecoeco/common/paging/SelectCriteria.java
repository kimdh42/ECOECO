package com.recycle.ecoeco.common.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
//@AllArgsConstructor
public class SelectCriteria {

    private int page;
    private int totalCount;
    private int limit;
    private int buttonAmount;
    private int maxPage;
    private int startPage;
    private int endPage;
    private int offset;
    private String searchCondition;
    private String searchValue;

    private String searchConditionDate;
    private String searchDate1;
    private String searchDate2;
    private String listType;

    public SelectCriteria(int page, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int offset, String searchCondition, String searchValue) {
        this.page = page;
        this.totalCount = totalCount;
        this.limit = limit;
        this.buttonAmount = buttonAmount;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.offset = offset;
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;
    }

    public SelectCriteria(int page, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int offset, String searchCondition, String searchValue, String searchConditionDate, String searchDate1, String searchDate2) {
        this.page = page;
        this.totalCount = totalCount;
        this.limit = limit;
        this.buttonAmount = buttonAmount;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.offset = offset;
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;
        this.searchConditionDate = searchConditionDate;
        this.searchDate1 = searchDate1;
        this.searchDate2 = searchDate2;
    }

    public SelectCriteria(int page, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int offset, String searchCondition, String searchValue, String searchDate1, String searchDate2) {
        this.page = page;
        this.totalCount = totalCount;
        this.limit = limit;
        this.buttonAmount = buttonAmount;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.offset = offset;
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;
        this.searchDate1 = searchDate1;
        this.searchDate2 = searchDate2;
    }

    public SelectCriteria(int page, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int offset, String searchCondition, String searchValue, String searchConditionDate, String searchDate1, String searchDate2, String listType) {
        this.page = page;
        this.totalCount = totalCount;
        this.limit = limit;
        this.buttonAmount = buttonAmount;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.offset = offset;
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;
        this.searchConditionDate = searchConditionDate;
        this.searchDate1 = searchDate1;
        this.searchDate2 = searchDate2;
        this.listType = listType;
    }
}
