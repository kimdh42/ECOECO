package com.recycle.ecoeco.membership.controller.manager;


import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import com.recycle.ecoeco.membership.service.manager.AdminCustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/manager/customer")
public class AdminCustomerController {

    private final AdminCustomerService adminCustomerService;

    @Autowired
    public AdminCustomerController(AdminCustomerService adminCustomerService){
        this.adminCustomerService = adminCustomerService;
    }

    //회원 관리 리스트
    @GetMapping("/customer_list")
    public String getCustomers(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(required = false) String searchCondition,
                               @RequestParam(required = false) String searchValue,
                               @RequestParam(required = false) String searchDate1,
                               @RequestParam(required = false) String searchDate2,
                               Model model) {

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("searchDate1", searchDate1);
        searchMap.put("searchDate2", searchDate2);

        Map<String, Object> boardListAndPaging = adminCustomerService.findCustomerList(searchMap, page);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
        model.addAttribute("customerList", boardListAndPaging.get("boardList"));

        return "/manager/customer/customer_list";
    }

    //회원 관리 상세 페이지
    @GetMapping("/customer_info")
    public String customer_info(@RequestParam int userNo, Model model) {
        UserInfoDTO customerInfo = adminCustomerService.userListDetail(userNo);
        model.addAttribute("customerInfo", customerInfo);

        return "/manager/customer/customer_info";
    }

    // 회원 삭제
    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("userNo") int userNo) {
        System.out.println("======================삭제요청==================================================================");
        System.out.println("userNo: " + userNo); // 받아온 userNo를 출력해서 확인합니다.
        adminCustomerService.deleteCustomer(userNo); // 사용자 삭제 서비스 메서드 호출
        return "redirect:/manager/customer/customer_list"; // 사용자 삭제 후 회원 목록 페이지로 리다이렉트
    }
    // 회원 등급 수정
    @PostMapping("/update_grade")
    public String updateCustomerGrade(@RequestParam("userNo") int userNo, @RequestParam("userGrade") int userGrade) {
        // 사용자 번호와 선택한 등급을 서비스에 전달하여 등급을 업데이트합니다.
        adminCustomerService.updateCustomerGrade(userNo, userGrade);

        // 변경 후 회원관리 상세 페이지로 리다이렉트
        return "redirect:/manager/customer/customer_info?userNo=" + userNo;
    }


}
