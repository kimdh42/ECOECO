package com.recycle.ecoeco.makerProject.controller.manager;

import com.recycle.ecoeco.makerProject.model.dto.*;
import com.recycle.ecoeco.makerProject.service.manager.AdminProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/project")
public class AdminProjectController {

    private final AdminProjectService adminProjectService;

    @Autowired
    public AdminProjectController(AdminProjectService adminProjectService) {
        this.adminProjectService = adminProjectService;
    }

    // 프로젝트 신규 리스트
    @GetMapping("/project_new_list")
    public String findProjectNewList(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(required = false) String searchCondition,
                                     @RequestParam(required = false) String searchValue,
                                     @RequestParam(required = false) String searchConditionDate,
                                     @RequestParam(required = false) String searchDate1,
                                     @RequestParam(required = false) String searchDate2,
                                     Model model){

//        System.out.println("project_new_list_controller");
        String listType = "new";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("searchConditionDate", searchConditionDate);
        searchMap.put("searchDate1", searchDate1);
        searchMap.put("searchDate2", searchDate2);
//        System.out.println("searchMap : " + searchMap);
//        System.out.println("listType : " + listType);

        Map<String, Object> boardListAndPaging = adminProjectService.findProjectNewList(searchMap, page, listType);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
        model.addAttribute("projectList", boardListAndPaging.get("boardList"));

//        System.out.println("boardListAndPaging : " + boardListAndPaging);

        return "/manager/project/project_new_list";

    }

    // 프로젝트 진행 리스트
    @GetMapping("/project_now_list")
    public String findProjectNowList(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(required = false) String searchCondition,
                                     @RequestParam(required = false) String searchValue,
                                     @RequestParam(required = false) String searchConditionDate,
                                     @RequestParam(required = false) String searchDate1,
                                     @RequestParam(required = false) String searchDate2,
                                     Model model){

//        System.out.println("project_now_list_controller");

        String listType = "now";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("searchConditionDate", searchConditionDate);
        searchMap.put("searchDate1", searchDate1);
        searchMap.put("searchDate2", searchDate2);
//        System.out.println("searchMap : " + searchMap);
//        System.out.println("listType : " + listType);

        Map<String, Object> boardListAndPaging = adminProjectService.findProjectNowList(searchMap, page, listType);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
        model.addAttribute("projectList", boardListAndPaging.get("boardList"));

//        System.out.println("boardListAndPaging : " + boardListAndPaging);

        return "/manager/project/project_now_list";

    }

    // 프로젝트 종료 리스트
    @GetMapping("/project_end_list")
    public String findProjectEndList(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(required = false) String searchCondition,
                                     @RequestParam(required = false) String searchValue,
                                     @RequestParam(required = false) String searchConditionDate,
                                     @RequestParam(required = false) String searchDate1,
                                     @RequestParam(required = false) String searchDate2,
                                     Model model){

//        System.out.println("project_end_list_controller");
        String listType = "end";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("searchConditionDate", searchConditionDate);
        searchMap.put("searchDate1", searchDate1);
        searchMap.put("searchDate2", searchDate2);
//        System.out.println("searchMap : " + searchMap);
//        System.out.println("listType : " + listType);

        Map<String, Object> boardListAndPaging = adminProjectService.findProjectEndList(searchMap, page, listType);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
        model.addAttribute("projectList", boardListAndPaging.get("boardList"));

//        System.out.println("boardListAndPaging : " + boardListAndPaging);

        return "/manager/project/project_end_list";

    }

    // 프로젝트 반려 리스트
    @GetMapping("/project_return_list")
    public String findProjectReturnList(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(required = false) String searchCondition,
                                     @RequestParam(required = false) String searchValue,
                                     @RequestParam(required = false) String searchConditionDate,
                                     @RequestParam(required = false) String searchDate1,
                                     @RequestParam(required = false) String searchDate2,
                                     Model model){

//        System.out.println("project_return_list_controller");
        String listType = "return";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("searchConditionDate", searchConditionDate);
        searchMap.put("searchDate1", searchDate1);
        searchMap.put("searchDate2", searchDate2);
//        System.out.println("searchMap : " + searchMap);
//        System.out.println("listType : " + listType);

        Map<String, Object> boardListAndPaging = adminProjectService.findProjectReturnList(searchMap, page, listType);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
        model.addAttribute("projectList", boardListAndPaging.get("boardList"));

//        System.out.println("boardListAndPaging : " + boardListAndPaging);

        return "/manager/project/project_return_list";

    }

    // 프로젝트 정보 상세 페이지
    @GetMapping("/project_detail")
    public String projectDetail(@RequestParam int projectNo, @RequestParam String listType, Model model) {
//        System.out.println("project_detail");
//        System.out.println("projectNo : " + projectNo);
//        System.out.println("listType : " + listType);

        ProjectDTO projectDetail = adminProjectService.projectDetail(projectNo);

        model.addAttribute("projectDetail", projectDetail);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("listType", listType);

//        System.out.println("projectDetail : " + projectDetail);

        return "/manager/project/project_detail";
    }

    // 프로젝트 정보 수정 페이지
    @GetMapping("/project_modify")
    public String projectModify(@RequestParam int projectNo, @RequestParam String listType, Model model) {
//        System.out.println("project_modify");
//        System.out.println("projectNo : " + projectNo);

        ProjectDTO projectModify = adminProjectService.projectModify(projectNo);
        model.addAttribute("projectModify", projectModify);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("listType", listType);

        return "/manager/project/project_modify";
    }

    // 프로젝트 정보 수정
    @PostMapping("/project_modify")
//    public String modifyProject(@RequestParam int projectNo, ProjectDTO projectDTO, MakerDTO makerDTO, Model model) {
    public String modifyProject(ProjectDTO projectDTO, MakerDTO makerDTO, @RequestParam String listType) {
//        System.out.println("project_modify");
//        System.out.println("projectNo : " + projectNo);
//        System.out.println("listType : " + listType);

        adminProjectService.modifyProject(projectDTO, makerDTO);

//        model.addAttribute("projectNo", projectNo);

        return "redirect:/manager/project/project_detail?projectNo=" + projectDTO.getProjectNo() + "&listType=" + listType;
    }

    // 프로젝트 정보 수정 카테고리 리스트
    @GetMapping("/category")
    public @ResponseBody List<CategoryDTO> findCategoryList() {
//        System.out.println("category");

        return adminProjectService.findCategoryList();
    }

    // 프로젝트 삭제
    @PostMapping("/deleteProject")
    public String deleteProject(@RequestParam int projectNo, @RequestParam String listType, RedirectAttributes rttr) {
//        System.out.println("deleteProject");
//        System.out.println("projectNo : " + projectNo);
//        System.out.println("listType : " + listType);

        String message = adminProjectService.deleteProject(projectNo);

//        System.out.println("message : " + message);

        // 삭제 작업 성공 여부 메시지를 리다이렉트할 때 함께 전달
        rttr.addFlashAttribute("message", message);

        if (!message.equals("모든 삭제 작업이 성공적으로 수행되었습니다.")) {
            // 삭제 실패 후 메세지 출력
            return "redirect:/manager/project/project_detail?projectNo=" + projectNo + "&listType=" + listType;
        } else {
            // 삭제 성공 후 listType에 맞는 프로젝트 목록 페이지로 리다이렉트
            return "redirect:/manager/project/project_" + listType + "_list";
        }

    }

    // 프로젝트 새소식
    @GetMapping("/project_news")
    public String projectNews(@RequestParam int projectNo, @RequestParam String listType, Model model) {
//        System.out.println("project_news");
//        System.out.println("projectNo : " + projectNo);

        List<NewsDTO> projectNews = adminProjectService.projectNews(projectNo);
        model.addAttribute("projectNews", projectNews);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("listType", listType);

//        System.out.println("projectNews : " + projectNews);

        return "/manager/project/project_news";
    }

    // 프로젝트 새소식 삭제
    @PostMapping("/deleteNews")
    public String deleteNews(@RequestParam int projectNo, @RequestParam String listType, @RequestParam("selectedNews") List<Integer> selectedNews) {
        System.out.println("listType : " + listType);
        // 선택된 뉴스 항목 삭제
        adminProjectService.deleteNews(selectedNews);

        // 삭제 후 뉴스 목록 페이지로 리다이렉트
        return "redirect:/manager/project/project_news?projectNo=" + projectNo + "&listType=" + listType;

    }

    // 프로젝트 리뷰
    @GetMapping("/project_review")
    public String projectReview(@RequestParam int projectNo, @RequestParam String listType, Model model) {
//        System.out.println("project_review");
//        System.out.println("projectNo : " + projectNo);

        List<ReviewDTO> projectReview = adminProjectService.projectReview(projectNo);
        model.addAttribute("projectReview", projectReview);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("listType", listType);

//        System.out.println("projectReview : " + projectReview);

        return "/manager/project/project_review";
    }

    // 프로젝트 리뷰 삭제
    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam int projectNo, @RequestParam String listType, @RequestParam("selectedReviews") List<Integer> selectedReviews) {

        // 선택된 리뷰 항목 삭제
        adminProjectService.deleteReview(selectedReviews);

        // 삭제 후 리뷰 목록 페이지로 리다이렉트
        return "redirect:/manager/project/project_review?projectNo=" + projectNo + "&listType=" + listType;

    }

}