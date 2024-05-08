package com.recycle.ecoeco.makerProject.model.dto;

import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import com.recycle.ecoeco.membership.model.dto.UserProfileImageDTO;

import java.time.LocalDate;

public class ReviewDTO {
    private int supportReviewsNo;
    private LocalDate supportReviewsDate;
    private String supportReviewsContent;
    private int supportReviewsCategory;
    private int projectNo;
    private ProjectDTO project;
    private int userNo;
    private UserInfoDTO userInfo;
    private UserProfileImageDTO userProfileImage;

    public ReviewDTO() {
    }

    public ReviewDTO(int supportReviewsNo, LocalDate supportReviewsDate, String supportReviewsContent, int supportReviewsCategory, int projectNo, int userNo) {
        this.supportReviewsNo = supportReviewsNo;
        this.supportReviewsDate = supportReviewsDate;
        this.supportReviewsContent = supportReviewsContent;
        this.supportReviewsCategory = supportReviewsCategory;
        this.projectNo = projectNo;
        this.userNo = userNo;
    }

    public ReviewDTO(int supportReviewsNo, LocalDate supportReviewsDate, String supportReviewsContent, int supportReviewsCategory, int projectNo, ProjectDTO project, int userNo, UserInfoDTO userInfo, UserProfileImageDTO userProfileImage) {
        this.supportReviewsNo = supportReviewsNo;
        this.supportReviewsDate = supportReviewsDate;
        this.supportReviewsContent = supportReviewsContent;
        this.supportReviewsCategory = supportReviewsCategory;
        this.projectNo = projectNo;
        this.project = project;
        this.userNo = userNo;
        this.userInfo = userInfo;
        this.userProfileImage = userProfileImage;
    }

    public int getSupportReviewsNo() {
        return supportReviewsNo;
    }

    public void setSupportReviewsNo(int supportReviewsNo) {
        this.supportReviewsNo = supportReviewsNo;
    }

    public LocalDate getSupportReviewsDate() {
        return supportReviewsDate;
    }

    public void setSupportReviewsDate(LocalDate supportReviewsDate) {
        this.supportReviewsDate = supportReviewsDate;
    }

    public String getSupportReviewsContent() {
        return supportReviewsContent;
    }

    public void setSupportReviewsContent(String supportReviewsContent) {
        this.supportReviewsContent = supportReviewsContent;
    }

    public int getSupportReviewsCategory() {
        return supportReviewsCategory;
    }

    public void setSupportReviewsCategory(int supportReviewsCategory) {
        this.supportReviewsCategory = supportReviewsCategory;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public UserProfileImageDTO getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(UserProfileImageDTO userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "supportReviewsNo=" + supportReviewsNo +
                ", supportReviewsDate=" + supportReviewsDate +
                ", supportReviewsContent='" + supportReviewsContent + '\'' +
                ", supportReviewsCategory=" + supportReviewsCategory +
                ", projectNo=" + projectNo +
                ", project=" + project +
                ", userNo=" + userNo +
                ", userInfo=" + userInfo +
                ", userProfileImage=" + userProfileImage +
                '}';
    }
}