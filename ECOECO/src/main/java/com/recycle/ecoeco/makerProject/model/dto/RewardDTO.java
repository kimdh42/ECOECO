package com.recycle.ecoeco.makerProject.model.dto;

public class RewardDTO{
    private int rewardNo;//리워드 번호
    private String rewardName;
    private int rewardPrice; //리워드 금액
    private String rewardInfo; //리워드 설명
    private int projectNo; //프로젝트 번호
    private ProjectDTO project;

    public RewardDTO(){

    }

    public RewardDTO(int rewardNo, String rewardName, int rewardPrice, String rewardInfo, int projectNo, ProjectDTO project) {
        this.rewardNo = rewardNo;
        this.rewardName = rewardName;
        this.rewardPrice = rewardPrice;
        this.rewardInfo = rewardInfo;
        this.projectNo = projectNo;
        this.project = project;
    }

    public int getRewardNo() {
        return rewardNo;
    }

    public void setRewardNo(int rewardNo) {
        this.rewardNo = rewardNo;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getRewardPrice() {
        return rewardPrice;
    }

    public void setRewardPrice(int rewardPrice) {
        this.rewardPrice = rewardPrice;
    }

    public String getRewardInfo() {
        return rewardInfo;
    }

    public void setRewardInfo(String rewardInfo) {
        this.rewardInfo = rewardInfo;
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

    @Override
    public String toString() {
        return "RewardDTO{" +
                "rewardNo=" + rewardNo +
                ", rewardName='" + rewardName + '\'' +
                ", rewardSum=" + rewardPrice +
                ", rewardInfo='" + rewardInfo + '\'' +
                ", projectNo=" + projectNo +
                ", projectDTO=" + project +
                '}';
    }
}
