package com.recycle.ecoeco.makerProject.model.dto;
// 스토리 대표이미지

public class StoryRepImgDTO {

    private int storyNo; //스토리 번호
    private String storyImageFileName;// 스토리 이미지 원본명
    private String storyImageSaveName; // 스토리 이미지 저장명
    private String storyImagePath;// 스토리 이미지 경로명
    private int projectNo;  // 프로젝트 번호

    private ProjectDTO projectDTO;

    public StoryRepImgDTO(){}


    public StoryRepImgDTO(int storyNo, String storyImageFileName, String storyImageSaveName, String storyImagePath, int projectNo, ProjectDTO projectDTO) {
        this.storyNo = storyNo;
        this.storyImageFileName = storyImageFileName;
        this.storyImageSaveName = storyImageSaveName;
        this.storyImagePath = storyImagePath;
        this.projectNo = projectNo;
        this.projectDTO = projectDTO;
    }

    public int getStoryNo() {
        return storyNo;
    }

    public void setStoryNo(int storyNo) {
        this.storyNo = storyNo;
    }

    public String getStoryImageFileName() {
        return storyImageFileName;
    }

    public void setStoryImageFileName(String storyImageFileName) {
        this.storyImageFileName = storyImageFileName;
    }

    public String getStoryImageSaveName() {
        return storyImageSaveName;
    }

    public void setStoryImageSaveName(String storyImageSaveName) {
        this.storyImageSaveName = storyImageSaveName;
    }

    public String getStoryImagePath() {
        return storyImagePath;
    }

    public void setStoryImagePath(String storyImagePath) {
        this.storyImagePath = storyImagePath;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    @Override
    public String toString() {
        return "StoryRepImgDTO{" +
                "storyNo=" + storyNo +
                ", storyImageFileName='" + storyImageFileName + '\'' +
                ", storyImageSaveName='" + storyImageSaveName + '\'' +
                ", storyImagePath='" + storyImagePath + '\'' +
                ", projectNo=" + projectNo +
                ", projectDTO=" + projectDTO +
                '}';
    }
}



