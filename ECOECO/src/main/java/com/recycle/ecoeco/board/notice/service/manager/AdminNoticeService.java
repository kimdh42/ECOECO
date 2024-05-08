package com.recycle.ecoeco.board.notice.service.manager;


import com.recycle.ecoeco.board.notice.model.dao.manager.AdminNoticeMapper;
import com.recycle.ecoeco.board.notice.model.dto.NoticeDTO;
import com.recycle.ecoeco.board.notice.model.dto.NoticeImageDTO;
import com.recycle.ecoeco.common.paging.Pagenation;
import com.recycle.ecoeco.common.paging.SelectCriteria;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
@Transactional
public class AdminNoticeService {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final AdminNoticeMapper adminNoticeMapper;

    public AdminNoticeService(AdminNoticeMapper adminNoticeMapper) {
        this.adminNoticeMapper = adminNoticeMapper;
    }

    /* 공지사항 리스트 조회 */
//    public List<NoticeDTO> selectNoticeList() {
//        return noticeMapper.selectNoticeList();
//    }
    public Map<String, Object> selectNoticeList(Map<String, String> searchMap, int page) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminNoticeMapper.selectTotalCount(searchMap);
        log.info("noticeList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("noticeList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<NoticeDTO> noticeList = adminNoticeMapper.selectNoticeList(selectCriteria);
        log.info("noticeList : {}", noticeList);

        Map<String, Object> noticeListAndPaging = new HashMap<>();
        noticeListAndPaging.put("paging", selectCriteria);
        noticeListAndPaging.put("noticeList", noticeList);

        return noticeListAndPaging;
    }

    // 공지 상세보기
    public NoticeDTO selectNoticeDetail(int noticeNo) {

        return adminNoticeMapper.selectNoticeDetail(noticeNo);
    }

    // 공지사항 작성
    public void writeBoard(NoticeDTO notice) {

        // 1. 공지사항 등록
        adminNoticeMapper.insertNotice(notice);

        // 2. 등록된 공지사항의 번호를 가져옴
        int noticeNo = notice.getNoticeNo();

        // 3. 공지사항 이미지 등록
        NoticeImageDTO image = notice.getImage();
        image.setNoticeNo(noticeNo); // 공지사항 번호 설정
        adminNoticeMapper.insertNoticeImage(image);
    }

    // 공지사항 삭제
    public void deleteNotice(NoticeDTO deleteNotice) {

        adminNoticeMapper.deleteNotice(deleteNotice);
    }

    // 공지사항 작성 정보 불러오기 (수정페이지)
    public NoticeDTO noticeModify(int noticeNo) {
        // 공지사항 정보 및 이미지 정보 가져오기
        NoticeDTO notice = adminNoticeMapper.noticeModify(noticeNo);

        return notice;
    }

    public void updateNotice(NoticeDTO notice, MultipartFile singleFile) {
        // 공지사항 번호로 기존 엔티티를 조회
        NoticeDTO existingNotice = adminNoticeMapper.noticeModify(notice.getNoticeNo());

        // 조회된 엔티티가 존재할 경우에만 업데이트 수행
        if (existingNotice != null) {
            // 기존 엔티티의 필드를 새로운 값으로 업데이트
            existingNotice.setNoticeCategory(notice.getNoticeCategory());
            existingNotice.setNoticeSubCategory(notice.getNoticeSubCategory());
            existingNotice.setNoticeTitle(notice.getNoticeTitle());
            existingNotice.setNoticeDetail(notice.getNoticeDetail());
            existingNotice.setNoticeDate(LocalDate.now());

            // 이미지 파일이 존재하는 경우
            if (singleFile != null && !singleFile.isEmpty()) {
                try {
                    // 이미지 파일이 업로드된 경우
                    String noticeOriginFileName = singleFile.getOriginalFilename();
                    log.info("originalFileName : {}", noticeOriginFileName);

                    String ext = noticeOriginFileName.substring(noticeOriginFileName.lastIndexOf("."));
                    String noticeSaveName = UUID.randomUUID() + ext;
                    log.info("savedFileName : {}", noticeSaveName);

                    // 서버의 설정 디렉토리에 파일 저장
                    String root = "src/main/resources/static/uploadFiles";
                    String noticePath = root + "/notice";

                    File dir = new File(noticePath);
                    if (!dir.exists()) dir.mkdirs();
                    singleFile.transferTo(new File(noticePath + "/" + noticeSaveName));

                    // 새로운 이미지 정보 설정
                    NoticeImageDTO newImage = new NoticeImageDTO();
                    newImage.setNoticeOriginFileName(noticeOriginFileName);
                    newImage.setNoticeSaveName(noticeSaveName);
                    newImage.setNoticePath("/uploadFiles/notice/");

                    // 새로운 이미지 정보에 공지사항 번호 설정
                    newImage.setNoticeNo(existingNotice.getNoticeNo());

                    // 새로운 이미지 정보를 기존 공지사항에 연결
                    existingNotice.setImage(newImage);

                    // 새로운 이미지 정보를 데이터베이스에 저장
                    adminNoticeMapper.updateNoticeImage(newImage); // 예시 코드이므로 실제 메소드명은 적절히 변경해야 합니다.
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // 엔티티 저장
            adminNoticeMapper.updateNotice(existingNotice);
        } else {
            // 엔티티가 존재하지 않을 경우 예외 처리 또는 로그 등 수행
            log.info("공지사항이 존재하지 않습니다.");
        }
    }

}
