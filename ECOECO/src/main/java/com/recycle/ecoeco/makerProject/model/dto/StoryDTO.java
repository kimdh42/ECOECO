package com.recycle.ecoeco.makerProject.model.dto;


import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;

import java.util.List;

public class StoryDTO{

    private int storyNo;
    private int projectNo;
    private String storySummary; //요약
    private String storyInfo; //스토리 내용(에디터에서 작성한 글)
    private List<StoryRepImgDTO> storyRepImg;
    private ProjectDTO projectDTO;
    private UserInfoDTO userInfoDTO;

    public StoryDTO(){}

    public StoryDTO(int storyNo, int projectNo, String storySummary, String storyInfo, List<StoryRepImgDTO> storyRepImg) {
        this.storyNo = storyNo;
        this.projectNo = projectNo;
        this.storySummary = storySummary;
        this.storyInfo = storyInfo;
        this.storyRepImg = storyRepImg;
    }

    public StoryDTO(int storyNo, int projectNo, String storySummary, String storyInfo, List<StoryRepImgDTO> storyRepImg, ProjectDTO projectDTO, UserInfoDTO userInfoDTO) {
        this.storyNo = storyNo;
        this.projectNo = projectNo;
        this.storySummary = storySummary;
        this.storyInfo = storyInfo;
        this.storyRepImg = storyRepImg;
        this.projectDTO = projectDTO;
        this.userInfoDTO = userInfoDTO;
    }

    public int getStoryNo() {
        return storyNo;
    }

    public void setStoryNo(int storyNo) {
        this.storyNo = storyNo;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public String getStorySummary() {
        return storySummary;
    }

    public void setStorySummary(String storySummary) {
        this.storySummary = storySummary;
    }

    public String getStoryInfo() {
        return storyInfo;
    }

    public void setStoryInfo(String storyInfo) {
        this.storyInfo = storyInfo;
    }

    public List<StoryRepImgDTO> getStoryRepImg() {
        return storyRepImg;
    }

    public void setStoryRepImg(List<StoryRepImgDTO> storyRepImg) {
        this.storyRepImg = storyRepImg;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    @Override
    public String toString() {
        return "StoryDTO{" +
                "storyNo=" + storyNo +
                ", projectNo=" + projectNo +
                ", storySummary='" + storySummary + '\'' +
                ", storyInfo='" + storyInfo + '\'' +
                ", storyRepImg=" + storyRepImg +
                ", projectDTO=" + projectDTO +
                ", userInfoDTO=" + userInfoDTO +
                '}';
    }
}
