package com.recycle.ecoeco.board.notice.model.dao.manager;

import com.recycle.ecoeco.board.notice.model.dto.NoticeDTO;
import com.recycle.ecoeco.board.notice.model.dto.NoticeImageDTO;
import com.recycle.ecoeco.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminNoticeMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);             // 공지사항 리스트 조회

    NoticeDTO selectNoticeDetail(int noticeNo);     // 공지사항 상세보기

    void insertNotice(NoticeDTO notice);             // 공지사항 등록

    void deleteNotice(NoticeDTO deleteNotice);      // 공지사항 삭제

    NoticeDTO noticeModify(int noticeNo);           // 공지사항 수정 페이지 이동 (값 가져오기)

    void insertNoticeImage(NoticeImageDTO image);      // 공지사항 이미지 등록

    void updateNotice(NoticeDTO existingNotice);    // 공지사항 등록 수정

    void updateNoticeImage(NoticeImageDTO newImage);
}
