package com.recycle.ecoeco.board.notice.model.dto;

public class NoticeImageDTO {

    private int noticeImgNo;                    // 이미지 번호
    private String noticeOriginFileName;        // 이미지 원본명
    private String noticeSaveName;              // 이미지 저장명
    private String noticePath;                  // 경로?
    private char noticeImageStatus;             // 이미지 삭제 여부
    private int noticeNo;                       // 공지사항 번호

    public NoticeImageDTO() {}

    public NoticeImageDTO(int noticeImgNo, String noticeOriginFileName, String noticeSaveName, String noticePath, char noticeImageStatus, int noticeNo) {
        this.noticeImgNo = noticeImgNo;
        this.noticeOriginFileName = noticeOriginFileName;
        this.noticeSaveName = noticeSaveName;
        this.noticePath = noticePath;
        this.noticeImageStatus = noticeImageStatus;
        this.noticeNo = noticeNo;
    }

    public int getNoticeImgNo() {
        return noticeImgNo;
    }

    public void setNoticeImgNo(int noticeImgNo) {
        this.noticeImgNo = noticeImgNo;
    }

    public String getNoticeOriginFileName() {
        return noticeOriginFileName;
    }

    public void setNoticeOriginFileName(String noticeOriginFileName) {
        this.noticeOriginFileName = noticeOriginFileName;
    }

    public String getNoticeSaveName() {
        return noticeSaveName;
    }

    public void setNoticeSaveName(String noticeSaveName) {
        this.noticeSaveName = noticeSaveName;
    }

    public String getNoticePath() {
        return noticePath;
    }

    public void setNoticePath(String noticePath) {
        this.noticePath = noticePath;
    }

    public char getNoticeImageStatus() {
        return noticeImageStatus;
    }

    public void setNoticeImageStatus(char noticeImageStatus) {
        this.noticeImageStatus = noticeImageStatus;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    @Override
    public String toString() {
        return "NoticeImageDTO{" +
                "noticeImgNo=" + noticeImgNo +
                ", noticeOriginFileName='" + noticeOriginFileName + '\'' +
                ", noticeSaveName='" + noticeSaveName + '\'' +
                ", noticePath='" + noticePath + '\'' +
                ", noticeImageStatus=" + noticeImageStatus +
                ", noticeNo=" + noticeNo +
                '}';
    }
}
