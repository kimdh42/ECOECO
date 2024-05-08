package com.recycle.ecoeco.accounting.model.dto;

import com.recycle.ecoeco.makerProject.model.dto.MakerDTO;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

public class SettlementDTO implements java.io.Serializable{

    private int settlementNo;
    private char settlementStatus;
    private int settlementPrice;
    private LocalDateTime settlementDate;
    private int userNo;
    private int projectNo;

    private MakerDTO maker;
    private ProjectDTO project;
    private UserInfoDTO userInfo;

    private double achievementRate; // 새로운 필드 추가

    public SettlementDTO() {
    }

    public SettlementDTO(int settlementNo, char settlementStatus, int settlementPrice, LocalDateTime settlementDate, int userNo, int projectNo, double achievementRate) {
        this.settlementNo = settlementNo;
        this.settlementStatus = settlementStatus;
        this.settlementPrice = settlementPrice;
        this.settlementDate = settlementDate;
        this.userNo = userNo;
        this.projectNo = projectNo;
        this.achievementRate = achievementRate;
    }

    public SettlementDTO(int settlementNo, char settlementStatus, int settlementPrice, LocalDateTime settlementDate, int userNo, int projectNo, MakerDTO maker, ProjectDTO project, UserInfoDTO userInfo, double achievementRate) {
        this.settlementNo = settlementNo;
        this.settlementStatus = settlementStatus;
        this.settlementPrice = settlementPrice;
        this.settlementDate = settlementDate;
        this.userNo = userNo;
        this.projectNo = projectNo;
        this.maker = maker;
        this.project = project;
        this.userInfo = userInfo;
        this.achievementRate = achievementRate;
    }

    public int getSettlementNo() {
        return settlementNo;
    }

    public void setSettlementNo(int settlementNo) {
        this.settlementNo = settlementNo;
    }

    public char getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(char settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public int getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(int settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public LocalDateTime getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDateTime settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public MakerDTO getMaker() {
        return maker;
    }

    public void setMaker(MakerDTO maker) {
        this.maker = maker;
    }

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

    public double getAchievementRate() {
        return achievementRate;
    }

    public void setAchievementRate(double achievementRate) {
        this.achievementRate = achievementRate;
    }

    @Override
    public String toString() {
        return "SettlementDTO{" +
                "settlementNo=" + settlementNo +
                ", settlementStatus=" + settlementStatus +
                ", settlementPrice=" + settlementPrice +
                ", settlementDate=" + settlementDate +
                ", userNo=" + userNo +
                ", projectNo=" + projectNo +
                ", maker=" + maker +
                ", project=" + project +
                ", userInfo=" + userInfo +
                ", achievementRate=" + achievementRate +
                '}';
    }
}
