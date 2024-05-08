package com.recycle.ecoeco.accounting.model.dao;

import com.recycle.ecoeco.accounting.model.dto.BankAccountDTO;
import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.makerProject.model.dto.RewardDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    // 주문 페이지 리워드 리스트
    List<RewardDTO> findRewardList(int projectNo);

    // 주문 페이지 은행 리스트
    List<BankAccountDTO> findBankList();

    // 주문 페이지 주문자 정보
    List<UserInfoDTO> findBuyerList(int userNo);

    // 주문 페이지 배송상품 프로젝트 여부
    List<ProjectDTO> findProjectList(int projectNo);

    // 주문 등록
    int registOrder(OrderDTO order);

    // 결제 등록
    int registPayment(PaymentDTO payment);
}
