package com.recycle.ecoeco.makerProject.model.dto;

public class MakerProfileDTO {
    private int makerImgNo;
    private int makerNo;
    private int projectNo;
    private String makerImageFileName;
    private String makerImageSaveName;
    private String makerImagePath;
    private ProjectDTO projectDTO;


    public MakerProfileDTO() {

    }

    public MakerProfileDTO(int makerImgNo, int makerNo, int projectNo, String makerImageFileName, String makerImageSaveName, String makerImagePath, ProjectDTO projectDTO) {
        this.makerImgNo = makerImgNo;
        this.makerNo = makerNo;
        this.projectNo = projectNo;
        this.makerImageFileName = makerImageFileName;
        this.makerImageSaveName = makerImageSaveName;
        this.makerImagePath = makerImagePath;
        this.projectDTO = projectDTO;
    }

    public int getMakerImgNo() {
        return makerImgNo;
    }

    public void setMakerImgNo(int makerImgNo) {
        this.makerImgNo = makerImgNo;
    }

    public int getMakerNo() {
        return makerNo;
    }

    public void setMakerNo(int makerNo) {
        this.makerNo = makerNo;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public String getMakerImageFileName() {
        return makerImageFileName;
    }

    public void setMakerImageFileName(String makerImageFileName) {
        this.makerImageFileName = makerImageFileName;
    }

    public String getMakerImageSaveName() {
        return makerImageSaveName;
    }

    public void setMakerImageSaveName(String makerImageSaveName) {
        this.makerImageSaveName = makerImageSaveName;
    }

    public String getMakerImagePath() {
        return makerImagePath;
    }

    public void setMakerImagePath(String makerImagePath) {
        this.makerImagePath = makerImagePath;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    @Override
    public String toString() {
        return "MakerProfileDTO{" +
                "makerImgNo=" + makerImgNo +
                ", makerNo=" + makerNo +
                ", projectNo=" + projectNo +
                ", makerImageFileName='" + makerImageFileName + '\'' +
                ", makerImageSaveName='" + makerImageSaveName + '\'' +
                ", makerImagePath='" + makerImagePath + '\'' +
                ", projectDTO=" + projectDTO +
                '}';
    }
}


