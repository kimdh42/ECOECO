package com.recycle.ecoeco.accounting.controller;


import com.recycle.ecoeco.accounting.model.dto.RefundDTO;
import com.recycle.ecoeco.accounting.service.AdminRefundService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/manager/ordertab/refund")
public class AdminRefundController {

    private final AdminRefundService refundService;

    @Autowired
    public AdminRefundController(AdminRefundService refundService){
        this.refundService = refundService;
    }

    @GetMapping("/refund_list")
    public String findRefundList(@RequestParam(defaultValue = "1") int page,
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

        Map<String, Object> boardListAndPaging = refundService.findRefundList(searchMap, page);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
//        model.addAttribute("boardList", boardListAndPaging.get("boardList"));
        model.addAttribute("refundList", boardListAndPaging.get("boardList"));

        System.out.println("boardListAndPaging : " + boardListAndPaging);

        return "/manager/ordertab/refund/refund_list";
//        List<RefundDTO> refundList = refundService.findRefundList();
//        model.addAttribute("refundList", refundList);
    }

    @GetMapping("/refund_info")
    public String refund_info(@RequestParam int refundNo, Model model){
        RefundDTO refundInfo = refundService.refundListDetail(refundNo);
        log.info("refundInfo : {}", refundInfo);
        model.addAttribute("refundInfo", refundInfo);

        return "/manager/ordertab/refund/refund_info";

    }

    // 환불 상태 변경
    @PostMapping("/updateRefund")
    public String updateRefund(@RequestParam int refundNo, @RequestParam String refundStatus) {


        refundService.updateRefund(refundNo, refundStatus);

    // 변경 후 환불 상세 페이지로 리다이렉트
        return "redirect:/manager/ordertab/refund/refund_info?refundNo=" + refundNo;
    }
}
