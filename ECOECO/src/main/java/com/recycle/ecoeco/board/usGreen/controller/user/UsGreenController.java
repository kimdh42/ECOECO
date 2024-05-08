package com.recycle.ecoeco.board.usGreen.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsGreenController {

    // 우리가 그린 게시글 상세 & 댓글
    @GetMapping("/user/usgreen/usGreenWriteDetailAndComment")
    public void usGreenWriteDetailAndComment() {
    }

    // 우리가 그린 게시글 페이지
    @GetMapping("/user/usgreen/usGreenWritePage")
    public void usGreenWritePage() {
    }

    // 우리가 그린 글쓰기 페이지
    @GetMapping("/user/usgreen/usGreenWritingPage")
    public void usGreenWritingPage() {
    }
}
