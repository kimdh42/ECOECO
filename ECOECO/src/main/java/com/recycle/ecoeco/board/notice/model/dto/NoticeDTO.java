package com.recycle.ecoeco.board.notice.model.dto;


import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

public class NoticeDTO {

    private int noticeNo;                   // 공지사항 번호

    private String noticeCategory;          // 공지사항 분류

    private String noticeSubCategory;       // 공지사항 소분류 : FAQ만 해당(메이커/서포터)

    private String noticeTitle;             // 공지사항 제목

    private String noticeDetail;            // 공지사항 상세 내용

    private LocalDate noticeDate;           // 공지사항 작성날짜

    private char noticeStatus;              // 공지사항 삭제여부

    private int userNo;                     // 공지사항 사용자 번호

    private UserInfoDTO writer;             // 작성자

    private NoticeImageDTO image;           // 이미지

    public NoticeDTO() {}

    public NoticeDTO(int noticeNo, String noticeCategory, String noticeSubCategory, String noticeTitle, String noticeDetail, LocalDate noticeDate, char noticeStatus, int userNo, UserInfoDTO writer, NoticeImageDTO image) {
        this.noticeNo = noticeNo;
        this.noticeCategory = noticeCategory;
        this.noticeSubCategory = noticeSubCategory;
        this.noticeTitle = noticeTitle;
        this.noticeDetail = noticeDetail;
        this.noticeDate = noticeDate;
        this.noticeStatus = noticeStatus;
        this.userNo = userNo;
        this.writer = writer;
        this.image = image;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getNoticeCategory() {
        return noticeCategory;
    }

    public void setNoticeCategory(String noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

    public String getNoticeSubCategory() {
        return noticeSubCategory;
    }

    public void setNoticeSubCategory(String noticeSubCategory) {
        this.noticeSubCategory = noticeSubCategory;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDetail() {
        return noticeDetail;
    }

    public void setNoticeDetail(String noticeDetail) {
        this.noticeDetail = noticeDetail;
    }

    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }

    public char getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(char noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public UserInfoDTO getWriter() {
        return writer;
    }

    public void setWriter(UserInfoDTO writer) {
        this.writer = writer;
    }

    public NoticeImageDTO getImage() {
        return image;
    }

    public void setImage(NoticeImageDTO image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "noticeNo=" + noticeNo +
                ", noticeCategory='" + noticeCategory + '\'' +
                ", noticeSubCategory='" + noticeSubCategory + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeDetail='" + noticeDetail + '\'' +
                ", noticeDate=" + noticeDate +
                ", noticeStatus=" + noticeStatus +
                ", userNo=" + userNo +
                ", writer=" + writer +
                ", image=" + image +
                '}';
    }
}
