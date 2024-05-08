package com.recycle.ecoeco.makerProject.model.dto;

import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;

import java.sql.Date;

public class NewsDTO {
    private int newsNo;
    private String newsCategory;//새소식 카테고리
    private String newsTitle;//새소식 작성
    private String newsContent; //새소식 내용
    private Date newsDate; // 새소식 작성날짜
    private String newsImage; // 새소식 이미지
    private int projectNo;
    private int userNo;
    private ProjectDTO project;
    private UserInfoDTO userInfo;

    public NewsDTO() {}

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public int getNewsNo() {
        return newsNo;
    }

    public void setNewsNo(int newsNo) {
        this.newsNo = newsNo;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "newsNo=" + newsNo +
                ", newsCategory='" + newsCategory + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsDate=" + newsDate +
                ", newsImage='" + newsImage + '\'' +
                ", projectNo=" + projectNo +
                ", userNo=" + userNo +
                ", project=" + project +
                ", userInfo=" + userInfo +
                '}';
    }
}
