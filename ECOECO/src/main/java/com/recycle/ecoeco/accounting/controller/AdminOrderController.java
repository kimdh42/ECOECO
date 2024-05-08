
package com.recycle.ecoeco.accounting.controller;


import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.service.AdminOrderService;
import com.recycle.ecoeco.membership.service.manager.AdminCustomerService;
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
@RequestMapping("/manager/ordertab/order")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;
    private final AdminCustomerService adminCustomerService;

    @Autowired
    public AdminOrderController(AdminOrderService adminOrderService, AdminCustomerService adminCustomerService){
        this.adminOrderService = adminOrderService;
        this.adminCustomerService = adminCustomerService;
    }

    @GetMapping("/order_list")
    public String findOrderList(@RequestParam(defaultValue = "1") int page,
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

        Map<String, Object> boardListAndPaging = adminOrderService.findorderList(searchMap, page);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
//        model.addAttribute("boardList", boardListAndPaging.get("boardList"));
        model.addAttribute("orderList", boardListAndPaging.get("boardList"));

        System.out.println("boardListAndPaging : " + boardListAndPaging);

        return "/manager/ordertab/order/order_list";
//        List<OrderDTO> orderList = orderService.findorderList();
//        model.addAttribute("orderList", orderList);
    }

    @GetMapping("/orderInfo")
    public String order_info(@RequestParam int orderNo, Model model){
        OrderDTO orderInfo = adminOrderService.orderListDetail(orderNo);

        log.info("orderInfo : {}", orderInfo);
        model.addAttribute("orderInfo", orderInfo);

        return "manager/ordertab/order/order_info";
    }
}

