package com.recycle.ecoeco.accounting.model.dto;

import com.recycle.ecoeco.board.notice.model.dto.NoticeDTO;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.makerProject.model.dto.RewardDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


public class PaymentDTO implements java.io.Serializable{
    private int paymentNo;
    private int paymentPrice;
    private LocalDateTime paymentDate;
    private String apiPayNo;
    private int projectNo;
    private int orderNo;
    private int rewardNo;
    private int userNo;

    private ProjectDTO project;
    private OrderDTO order;
    private RewardDTO reward;
    private UserInfoDTO orderer;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentNo, int paymentPrice, LocalDateTime paymentDate, String apiPayNo, int projectNo, int orderNo, int rewardNo, int userNo) {
        this.paymentNo = paymentNo;
        this.paymentPrice = paymentPrice;
        this.paymentDate = paymentDate;
        this.apiPayNo = apiPayNo;
        this.projectNo = projectNo;
        this.orderNo = orderNo;
        this.rewardNo = rewardNo;
        this.userNo = userNo;
    }

    public PaymentDTO(int paymentNo, int paymentPrice, LocalDateTime paymentDate, String apiPayNo, int projectNo, int orderNo, int rewardNo, int userNo, ProjectDTO project, OrderDTO order, RewardDTO reward, UserInfoDTO orderer) {
        this.paymentNo = paymentNo;
        this.paymentPrice = paymentPrice;
        this.paymentDate = paymentDate;
        this.apiPayNo = apiPayNo;
        this.projectNo = projectNo;
        this.orderNo = orderNo;
        this.rewardNo = rewardNo;
        this.userNo = userNo;
        this.project = project;
        this.order = order;
        this.reward = reward;
        this.orderer = orderer;
    }

    public int getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(int paymentNo) {
        this.paymentNo = paymentNo;
    }

    public int getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getApiPayNo() {
        return apiPayNo;
    }

    public void setApiPayNo(String apiPayNo) {
        this.apiPayNo = apiPayNo;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getRewardNo() {
        return rewardNo;
    }

    public void setRewardNo(int rewardNo) {
        this.rewardNo = rewardNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public RewardDTO getReward() {
        return reward;
    }

    public void setReward(RewardDTO reward) {
        this.reward = reward;
    }

    public UserInfoDTO getOrderer() {
        return orderer;
    }

    public void setOrderer(UserInfoDTO orderer) {
        this.orderer = orderer;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentNo=" + paymentNo +
                ", paymentPrice=" + paymentPrice +
                ", paymentDate=" + paymentDate +
                ", apiPayNo='" + apiPayNo + '\'' +
                ", projectNo=" + projectNo +
                ", orderNo=" + orderNo +
                ", rewardNo=" + rewardNo +
                ", userNo=" + userNo +
                ", project=" + project +
                ", order=" + order +
                ", reward=" + reward +
                ", orderer=" + orderer +
                '}';
    }
}
