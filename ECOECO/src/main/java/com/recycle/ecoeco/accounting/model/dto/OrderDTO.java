package com.recycle.ecoeco.accounting.model.dto;

import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.makerProject.model.dto.RewardDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderDTO implements java.io.Serializable{
    private int orderNo;
    private int orderPrice;
    private String orderStatus;
    private String orderCategory;
    private String paymentCategory;
    private String paymentStatus;
    private LocalDateTime orderDate;
    private String buyerName;
    private String buyerTel;
    private String buyerAddr;
    private String buyerAccount;
    private int userNo;
    private int projectNo;
    private int rewardNo;
    private int accountNo;

    private UserInfoDTO orderer;    // 회원 DTO == 주문자
    private ProjectDTO project;     // 프로젝트 DTO -> 프로젝트명
    private RewardDTO reward;       // 리워드 DTO -> 리워드명, 금액  dto필드는 하나면 다 가능
    private BankAccountDTO bankAccount;
}
