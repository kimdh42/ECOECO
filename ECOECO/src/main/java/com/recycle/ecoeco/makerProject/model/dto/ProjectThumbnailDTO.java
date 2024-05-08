package com.recycle.ecoeco.makerProject.model.dto;

public class ProjectThumbnailDTO {

    private int projectImgNo;
    private int projectNo;
    private String projectImageFileName;
    private String projectImageSaveName;
    private String projectImagePath;
    //private ProjectDTO projectDTO;

    public ProjectThumbnailDTO() {
    }

    public ProjectThumbnailDTO(int projectImgNo, int projectNo, String projectImageFileName, String projectImageSaveName, String projectImagePath) {
        this.projectImgNo = projectImgNo;
        this.projectNo = projectNo;
        this.projectImageFileName = projectImageFileName;
        this.projectImageSaveName = projectImageSaveName;
        this.projectImagePath = projectImagePath;
    }


    public int getProjectImgNo() {
        return projectImgNo;
    }

    public void setProjectImgNo(int projectImgNo) {
        this.projectImgNo = projectImgNo;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectImageFileName() {
        return projectImageFileName;
    }

    public void setProjectImageFileName(String projectImageFileName) {
        this.projectImageFileName = projectImageFileName;
    }

    public String getProjectImageSaveName() {
        return projectImageSaveName;
    }

    public void setProjectImageSaveName(String projectImageSaveName) {
        this.projectImageSaveName = projectImageSaveName;
    }

    public String getProjectImagePath() {
        return projectImagePath;
    }

    public void setProjectImagePath(String projectImagePath) {
        this.projectImagePath = projectImagePath;
    }

    @Override
    public String toString() {
        return "ProjectThumbnailDTO{" +
                "projectImgNo=" + projectImgNo +
                ", projectNo=" + projectNo +
                ", projectImageFileName='" + projectImageFileName + '\'' +
                ", projectImageSaveName='" + projectImageSaveName + '\'' +
                ", projectImagePath='" + projectImagePath + '\'' +
                '}';
    }
}
