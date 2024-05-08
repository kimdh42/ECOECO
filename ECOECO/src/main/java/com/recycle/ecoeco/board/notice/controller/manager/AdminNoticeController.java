package com.recycle.ecoeco.board.notice.controller.manager;


import com.recycle.ecoeco.board.notice.model.dto.NoticeDTO;
import com.recycle.ecoeco.board.notice.model.dto.NoticeImageDTO;
import com.recycle.ecoeco.board.notice.service.manager.AdminNoticeService;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/manager/board")
public class AdminNoticeController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final AdminNoticeService adminNoticeService;

    @Autowired
    public AdminNoticeController(AdminNoticeService adminNoticeService) {
        this.adminNoticeService = adminNoticeService;
    }

    // 공지사항 리스트 페이지
    @GetMapping("/adminNoticeList")
    public void checkedBoard(@RequestParam(defaultValue = "1") int page,
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

        Map<String, Object> noticeListAndPaging = adminNoticeService.selectNoticeList(searchMap, page);
        model.addAttribute("paging", noticeListAndPaging.get("paging"));
        model.addAttribute("noticeList", noticeListAndPaging.get("noticeList"));
//        List<NoticeDTO> noticeList = noticeService.selectNoticeList();
//        model.addAttribute("noticeList", noticeList);
    }

    // 공지사항 상세보기
    @GetMapping("/adminNoticeDetail")
    public String getBoardDetail(@RequestParam int noticeNo, Model model) {

        NoticeDTO noticeDetail = adminNoticeService.selectNoticeDetail(noticeNo);
        log.info("noticeDetail : {}", noticeDetail);
        model.addAttribute("noticeDetail", noticeDetail);

        System.out.println(noticeDetail);

        return "manager/board/adminNoticeDetail";
    }

    // 공지사항 작성 페이지
    @GetMapping("/write")
    public String getWriteBoard() {
        return "manager/board/adminNoticeWrite";
    }

    @PostMapping("/write")
    public String writeBoard(NoticeDTO notice, MultipartFile singleFile,
                             @AuthenticationPrincipal UserInfoDTO user) {

        log.info("notice request : {}", notice);
        log.info("singleFile request : {}", singleFile);

        String noticePath = IMAGE_DIR + "notice";
        File dir = new File(noticePath);
        if (!dir.exists()) dir.mkdirs();

        // 이미지 정보가 있는지 확인하고 처리
        NoticeImageDTO attachImage = new NoticeImageDTO();
        try {
            if (singleFile.getSize() > 0) {
                // 이미지 파일이 업로드된 경우
                String noticeOriginFileName = singleFile.getOriginalFilename();
                log.info("originalFileName : {}", noticeOriginFileName);

                String ext = noticeOriginFileName.substring(noticeOriginFileName.lastIndexOf("."));
                String noticeSaveName = UUID.randomUUID() + ext;
                log.info("savedFileName : {}", noticeSaveName);

                /* 서버의 설정 디렉토리에 파일 저장하기 */
                singleFile.transferTo(new File(noticePath + "/" + noticeSaveName));

                /* DB에 저장할 파일의 정보 설정 */
                attachImage.setNoticeOriginFileName(noticeOriginFileName);
                attachImage.setNoticeSaveName(noticeSaveName);
                attachImage.setNoticePath("/uploadFiles/notice/");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 게시글 작성 정보 설정
        notice.setWriter(user);
        notice.setImage(attachImage); // 이미지 정보 설정

        // 게시글 서비스를 통해 게시글 작성
        adminNoticeService.writeBoard(notice); // 이미지 파일도 함께 전달

        // 게시글 리스트로 리다이렉트
        return "redirect:/manager/board/adminNoticeList";
    }

    // 공지사항 삭제
    @PostMapping("/deleteNotice")
    public ResponseEntity<String> deleteNotice(@RequestBody NoticeDTO deleteNotice) {

        log.info("deleteNotice no : {}", deleteNotice.getNoticeNo());

        adminNoticeService.deleteNotice(deleteNotice);

        return ResponseEntity.ok("게시글 삭제 완료");
    }

    // 공지사항 수정 페이지 이동
    @GetMapping("/adminNoticeModify")
    public String showNoticeWriteModifyPage(@RequestParam int noticeNo, Model model) {
        // 공지사항 정보 가져오기
        NoticeDTO noticeModify = adminNoticeService.noticeModify(noticeNo);

        // 이미지 정보 출력
        NoticeImageDTO image = noticeModify.getImage();
        if (image != null) {
            System.out.println("이미지 정보: " + image.getNoticeOriginFileName());
        } else {
            System.out.println("이미지 정보가 없습니다.");
        }

        // 모델에 추가
        model.addAttribute("noticeNo", noticeNo);
        model.addAttribute("noticeModify", noticeModify);

        // 페이지 출력
        return "manager/board/adminNoticeModify";
    }

    // 공지사항 수정 등록
    @PostMapping("/adminNoticeModify")
    public String updateNotice(@RequestParam int noticeNo, @ModelAttribute NoticeDTO notice,
                               @RequestParam("singleFile") MultipartFile singleFile) {
        // 서비스 클래스의 업데이트 메소드 호출
        adminNoticeService.updateNotice(notice, singleFile);

        // 수정된 공지사항 상세 페이지로 리다이렉트
        return "redirect:/manager/board/adminNoticeDetail?noticeNo=" + noticeNo;
    }
}
