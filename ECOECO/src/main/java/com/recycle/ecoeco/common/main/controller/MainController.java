package com.recycle.ecoeco.common.main.controller;

import com.recycle.ecoeco.common.main.service.MainService;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import com.recycle.ecoeco.membership.service.user.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final MyPageService myPageService;
    private final MainService mainService;

    public MainController(MainService mainService, MyPageService myPageService) {
        this.mainService = mainService;
        this.myPageService = myPageService;
    }

    // 메인페이지 바디부분 프로젝트
    @GetMapping(value = {"/", "/main"})
    public String main(Model model) {

        // 인기 프로젝트 서비스 메소드 호출
        List<ProjectDTO> mainPopularList = mainService.mainPopularList();
        model.addAttribute("mainPopularList", mainPopularList);

        // 신규 프로젝트 서비스 메소드 호출
        List<ProjectDTO> mainNewList = mainService.mainNewList();
        model.addAttribute("mainNewList", mainNewList);

        // 오픈예정 프로젝트 서비스 메소드 호출
        List<ProjectDTO> mainOpenList = mainService.mainOpenList();
        model.addAttribute("mainOpenList", mainOpenList);

        // 마감임박 프로젝트 서비스 메소드 호출
        List<ProjectDTO> mainCloseList = mainService.mainCloseList();
        model.addAttribute("mainCloseList", mainCloseList);

        return "main";
    }

    @GetMapping("/adminMain")
    public void managerMainPage() {}

    /* 메인페이지에서 로그인페이지 이동 */
    @GetMapping("/login")
    public void headerLoginPage() {}

    /* 메인페이지 에서 회원가입 페이지 이동 */
    @GetMapping("/joinus")
    public void headerJoinUsPage() {}

    /* 회원가입 처리 */
    @PostMapping("/joinus")
    public String joinus(UserInfoDTO user) {

        myPageService.joinus(user);

        return "redirect:/templates/login";
    }

    // 메인페이지에서 헤더쪽 분류(section) 눌렀을때 나올 페이지
    @GetMapping("/user/section")
    public String newList(@RequestParam String section, Model model) {

        switch (section) {

            case "popular": // 인기 프로젝트 서비스 메소드 호출
                List<ProjectDTO> popularList = mainService.popularList();
                model.addAttribute("popularList", popularList);
                model.addAttribute("section", "popular");
                break;

            case "new": // 신규 프로젝트 서비스 메소드 호출
                List<ProjectDTO> newList = mainService.newList();
                model.addAttribute("newList", newList);
                model.addAttribute("section", "new");
                break;

            case "open": // 오픈예정 프로젝트 서비스 메소드 호출
                List<ProjectDTO> openList = mainService.openList();
                model.addAttribute("openList", openList);
                model.addAttribute("section", "open");
                break;

            case "close": // 마감임박 프로젝트 서비스 메소드 호출
                List<ProjectDTO> closeList = mainService.closeList();
                model.addAttribute("closeList",closeList);
                model.addAttribute("section", "close");
                break;
        }
        return "/user/section";
    }


    // 메인 페이지 헤더의 카테고리 부분
    @GetMapping("/user/category")
    public String categoryList(@RequestParam int category, Model model) {

        switch (category) {

            case 1 :  // 리빙 카테고리 서비스 메소드 호출
                List<ProjectDTO> livingList = mainService.livingList();
                model.addAttribute("livingList", livingList);
                model.addAttribute("category", 1);
                break;

            case 2 :  // 가전 카테고리 서비스 메소드 호출
                List<ProjectDTO> applianceList = mainService.applianceList();
                model.addAttribute("applianceList", applianceList);
                model.addAttribute("category", 2);
                break;

            case 3 :  // 뷰티 카테고리 서비스 메소드 호출
                List<ProjectDTO> beautyList = mainService.beautyList();
                model.addAttribute("beautyList", beautyList);
                model.addAttribute("category", 3);
                break;

            case 4 :  // 의류 카테고리 서비스 메소드 호출
                List<ProjectDTO> clothingList = mainService.clothingList();
                model.addAttribute("clothingList",clothingList);
                model.addAttribute("category", 4);
                break;

            case 5 :  // 잡화 카테고리 서비스 메소드 호출
                List<ProjectDTO> generalItemsList = mainService.generalItemsList();
                model.addAttribute("generalItemsList",generalItemsList);
                model.addAttribute("category", 5);
                break;

            case 6 :  // 굿즈 카테고리 서비스 메소드 호출
                List<ProjectDTO> goodsList = mainService.goodsList();
                model.addAttribute("goodsList",goodsList);
                model.addAttribute("category", 6);
                break;

            case 7 :  // 영화/공연 카테고리 서비스 메소드 호출
                List<ProjectDTO> entertainmentList = mainService.entertainmentList();
                model.addAttribute("entertainmentList",entertainmentList);
                model.addAttribute("category", 7);
                break;

            case 8 : // 기타 카테고리 서비스 메소드 호출
                List<ProjectDTO> otherList = mainService.otherList();
                model.addAttribute("otherList",otherList);
                model.addAttribute("category", 8);
                break;
        }
        return "/user/category";
    }

}