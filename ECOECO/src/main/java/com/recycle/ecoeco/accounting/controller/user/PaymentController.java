package com.recycle.ecoeco.accounting.controller.user;


import com.recycle.ecoeco.accounting.model.dto.BankAccountDTO;
import com.recycle.ecoeco.accounting.model.dto.KakaoDTO;
import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.accounting.service.user.PaymentService;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.makerProject.model.dto.RewardDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // (주문) 페이지
    @GetMapping("/user/payment/crowdfundingPage")
    public void crowdfundingPage(@RequestParam int projectNo, Model model) {

        model.addAttribute("projectNo", projectNo);

    }

    // 주문 등록
    @PostMapping("/user/payment/registOrder")
    public String registOrder(OrderDTO order, String zipCode, String address1, String address2, Model model, RedirectAttributes rttr, HttpSession session) {

        String address = zipCode + "$" + address1 + "$" + address2;
        order.setBuyerAddr(address);

        paymentService.registOrder(order);

        if(order.getPaymentCategory().equals("즉시결제")) {

            String strProjectNo = Integer.toString(order.getProjectNo());
            String strRewardNo = Integer.toString(order.getRewardNo());
            String name = "리워드P" + strProjectNo + "R" + strRewardNo;
            int amount = order.getOrderPrice();
//            String buyer_email = "testmail@gmail.com";
            String buyer_name = order.getBuyerName();
            String buyer_tel = order.getBuyerTel();
            String buyer_addr = address1;
            String buyer_postcode = zipCode;
            int projectNo = order.getProjectNo();
            int orderNo = order.getOrderNo();
            int rewardNo = order.getRewardNo();
            int userNo = order.getUserNo();

            KakaoDTO kakao = new KakaoDTO(
                name,
                amount,
//                buyer_email,
                buyer_name,
                buyer_tel,
                buyer_addr,
                buyer_postcode,
                projectNo,
                orderNo,
                rewardNo,
                userNo
            );
            session.setAttribute("kakao", kakao);
//            System.out.println("kakao 1 : " + kakao);

            return "redirect:/user/payment/kakaoPayment";
        } else {
//        rttr.addFlashAttribute("message", "주문이 완료 되었습니다.");
            return "redirect:/user/payment/prepaymentCompletePage";
        }
    }

    // 주문 페이지 리워드 리스트
    @GetMapping("/user/payment/reward")
    public @ResponseBody List<RewardDTO> findRewardList(@RequestParam int projectNo) {

        return paymentService.findRewardList(projectNo);
    }

    // 주문 페이지 은행 리스트
    @GetMapping("/user/payment/bank")
    public @ResponseBody List<BankAccountDTO> findBankList() {

        return paymentService.findBankList();
    }

    // 주문 페이지 주문자 정보
    @GetMapping("/user/payment/buyer")
    public @ResponseBody List<UserInfoDTO> findBuyerList(int userNo) {

        return paymentService.findBuyerList(userNo);
    }

    // 주문 페이지 배송상품 프로젝트 여부
    @GetMapping("/user/payment/delivery")
    public @ResponseBody List<ProjectDTO> findProjectList(int projectNo) {

        return paymentService.findProjectList(projectNo);
    }

    // 예약 결제 완료 페이지
    @GetMapping("/user/payment/prepaymentCompletePage")
    public void prepaymentCompletePage() {
    }

    // 카카오 결제 페이지
    @GetMapping("/user/payment/kakaoPayment")
    public void kakaoPaymentPage() {
//    public String kakaoPaymentPage(HttpSession session) {
//        KakaoDTO kakao = (KakaoDTO) session.getAttribute("kakao");
//        System.out.println("kakao 2 : " + kakao);

//        return "/user/payment/kakaoPayment";
    }

    // 카카오 DTO 가져오기
    @GetMapping("/user/payment/getKakao")
    public @ResponseBody KakaoDTO getKakaoObject(HttpSession session) {
        return (KakaoDTO) session.getAttribute("kakao");
    }

    // 카카오 즉시 결제 등록
    @GetMapping("/user/payment/kakaoRegistPayment")
    public String kakaoRegistPayment(PaymentDTO payment, @RequestParam String apiPayNo, HttpSession session) {
        System.out.println("kakaoRegistPayment apiPayNo: " + apiPayNo );

        KakaoDTO kakao = (KakaoDTO) session.getAttribute("kakao");

        payment.setPaymentPrice(kakao.getAmount());
//        payment.setPaymentDate(LocalDateTime.now());
        payment.setApiPayNo(apiPayNo);
        payment.setProjectNo(kakao.getProjectNo());
        payment.setOrderNo(kakao.getOrderNo());
        payment.setRewardNo(kakao.getRewardNo());
        payment.setUserNo(kakao.getUserNo());

        paymentService.registPayment(payment);

        return "/user/payment/prepaymentCompletePage";
    }
}
