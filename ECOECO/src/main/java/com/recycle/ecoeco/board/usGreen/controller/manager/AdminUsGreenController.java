package com.recycle.ecoeco.board.usGreen.controller.manager;

import com.recycle.ecoeco.board.notice.model.dto.NoticeDTO;
import com.recycle.ecoeco.board.usGreen.model.dto.UsGreenDTO;
import com.recycle.ecoeco.board.usGreen.service.manager.AdminUsGreenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/manager/board")
public class AdminUsGreenController {

    private final AdminUsGreenService adminUsGreenService;

    @Autowired
    public AdminUsGreenController(AdminUsGreenService adminUsGreenService) {
        this.adminUsGreenService = adminUsGreenService;
    }

    @GetMapping("/adminUsGreenList")
    public void showUsGreenListPage(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(required = false) String searchCondition,
                                    @RequestParam(required = false) String searchValue,
                                    @RequestParam(required = false) String searchConditionDate,
                                    @RequestParam(required = false) String searchDate1,
                                    @RequestParam(required = false) String searchDate2,
                                    Model model) {

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("searchConditionDate", searchConditionDate);
        searchMap.put("searchDate1", searchDate1);
        searchMap.put("searchDate2", searchDate2);

        Map<String, Object> usGreenListAndPaging = adminUsGreenService.selectUsGreenList(searchMap, page);
        model.addAttribute("paging", usGreenListAndPaging.get("paging"));
        model.addAttribute("usGreenList", usGreenListAndPaging.get("usGreenList"));
    }

    // 우리가그린 상세보기
    @GetMapping("/adminUsGreenDetail")
    public String getBoardDetail(@RequestParam int comuNo, Model model){

        UsGreenDTO usGreenDetail = adminUsGreenService.selectUsGreenDetail(comuNo);
        log.info("usGreenDetail : {}", usGreenDetail);
        model.addAttribute("usGreenDetail", usGreenDetail);

        System.out.println(usGreenDetail);

        return "manager/board/adminUsGreenDetail";
    }

    // 우리가그린 삭제
    @PostMapping("/deleteUsGreen")
    public ResponseEntity<String> deleteUsGreen(@RequestBody UsGreenDTO deleteUsGreen) {

        log.info("deleteUsGreen no : {}", deleteUsGreen.getComuNo());

        adminUsGreenService.deleteUsGreen(deleteUsGreen);

        return ResponseEntity.ok("게시글 삭제 완료");
    }
}
