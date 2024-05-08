package com.recycle.ecoeco.accounting.service.user;

import com.recycle.ecoeco.accounting.model.dao.PaymentMapper;
import com.recycle.ecoeco.accounting.model.dto.BankAccountDTO;
import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.makerProject.model.dto.RewardDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    // 주문 페이지 리워드 리스트
    @Transactional(readOnly = true)
    public List<RewardDTO> findRewardList(int projectNo) {
        return paymentMapper.findRewardList(projectNo);
    }

    // 주문 페이지 은행 리스트
    @Transactional(readOnly = true)
    public List<BankAccountDTO> findBankList() {
        return paymentMapper.findBankList();
    }

    // 주문 페이지 주문자 정보
    @Transactional(readOnly = true)
    public List<UserInfoDTO> findBuyerList(int userNo) {
        return paymentMapper.findBuyerList(userNo);
    }

    // 주문 페이지 배송상품 프로젝트 여부
    public List<ProjectDTO> findProjectList(int projectNo) {
        return paymentMapper.findProjectList(projectNo);
    }

    // 주문 등록
    public void registOrder(OrderDTO order) {

        int result = paymentMapper.registOrder(order);

        if (result > 0) {
            System.out.println("주문이 완료 되었습니다.");
        } else {
            System.out.println("주문이 실패 하였습니다.");
        }
    }

    // 결제 등록
    public void registPayment(PaymentDTO payment) {

        int result = paymentMapper.registPayment(payment);

        if (result > 0) {
            System.out.println("결제가 완료 되었습니다.");
        } else {
            System.out.println("결제가 실패 하였습니다.");
        }
    }
}
