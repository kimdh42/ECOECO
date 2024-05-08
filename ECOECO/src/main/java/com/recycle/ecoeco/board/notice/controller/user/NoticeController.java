package com.recycle.ecoeco.board.notice.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

    // faq 상세 페이지
    @GetMapping("/user/borad/faqDetailsPage")
    public void faqDetailsPage() {
    }

    // faq 페이지
    @GetMapping("/user/borad/faqPage")
    public void faqPage() {
    }

    // 통합 검색 후 페이지
    @GetMapping("/user/borad/integratedSearchResultsPage")
    public void integratedSearchResultsPage() {
    }

    // 메이커 faq 페이지
    @GetMapping("/user/borad/makerFaq")
    public void makerFaqPage() {
    }

    // 공지사항 페이지
    @GetMapping("/user/borad/notice")
    public void noticePage() {
    }

    // 공지사항 상세 페이지
    @GetMapping("/user/borad/noticeClickDetailPage")
    public void noticeClickDetailPage() {
    }

    // 서포트 faq
    @GetMapping("/user/borad/supporterFaq")
    public void supporterFaq() {
    }
}
