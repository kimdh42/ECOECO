package com.recycle.ecoeco.accounting.controller;



import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.accounting.service.AdminPaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/manager/ordertab/payment")
public class AdminPaymentController {


    private final AdminPaymentService paymentService;

    @Autowired
    public AdminPaymentController(AdminPaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/payment_list")
    public String findPaymentList(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String searchCondition,
                                @RequestParam(required = false) String searchValue,
                                @RequestParam(required = false) String searchDate1,
                                @RequestParam(required = false) String searchDate2,
                                Model model){
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("searchDate1", searchDate1);
        searchMap.put("searchDate2", searchDate2);

        Map<String, Object> boardListAndPaging = paymentService.findPaymentList(searchMap, page);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
//        model.addAttribute("boardList", boardListAndPaging.get("boardList"));
        model.addAttribute("paymentList", boardListAndPaging.get("boardList"));

        System.out.println("boardListAndPaging : " + boardListAndPaging);

        return "/manager/ordertab/payment/payment_list";
//        List<PaymentDTO> paymentList = paymentService.findPaymentList();
//        model.addAttribute("paymentList", paymentList);
    }
    @GetMapping("/payment_info")
    public String payment_info(@RequestParam int paymentNo, Model model){
        PaymentDTO paymentInfo = paymentService.paymentListDetail(paymentNo);
        log.info("paymentInfo : {}", paymentInfo);
        model.addAttribute("paymentInfo", paymentInfo);
        return "manager/ordertab/payment/payment_info";
    }
}
