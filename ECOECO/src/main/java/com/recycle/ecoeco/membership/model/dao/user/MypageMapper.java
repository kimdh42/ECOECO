package com.recycle.ecoeco.membership.model.dao.user;

import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.accounting.model.dto.SettlementDTO;
import com.recycle.ecoeco.makerProject.model.dto.NewsDTO;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import com.recycle.ecoeco.membership.model.dto.UserProfileImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {

    int joinus(UserInfoDTO user);       // 회원가입

    UserInfoDTO selectUserById(String userId);      // 아이디 중복체크

    UserInfoDTO findByUserId(String userId);        // 로그인

    UserInfoDTO findIdByUserNameAndEmail(String userName, String userEmail);        // 아이디 찾기

    String findPwdByUserIdAndUserEmail(String userId, String userEmail);        // 비밀번호 찾기

    void updateUserPassword(String encode, String userId);      // 임시 비밀번호 DB 업데이트

    void updateUserInfo(UserInfoDTO userInfoDTO);       // 내정보 수정

    void deleteUserInfo(String userId);     // 탈퇴하기

    void insertUserProfileImage(UserProfileImageDTO userProfileImageDTO);       // 이미지 업로드

    UserProfileImageDTO getLatestProfileImage(String userNo);       // 프로필 이미지 출력

    List<ProjectDTO> getProjectByUserId(String userId);     // 나의 프로젝트 리스트 조회

    ProjectDTO getProjectDetail(int projectNo);      // 나의 프로젝트 상세 조회

    List<PaymentDTO> getPaymentInfo(String projectNo);      // 나의 프로젝트 결제 현황

    List<SettlementDTO> getSettlementInfo(String projectNo);        // 나의 프로젝트 정산정보

    SettlementDTO getSettlementDTO(int settlementNo);        // 나의 프로젝트 정산내역

    List<ProjectDTO> getSupportMyProjectList(String userId);        // 내가 후원한 프로젝트

    ProjectDTO getSupportProjectDetail(String projectNo);           // 내가 후원한 프로젝트 상세

    void refundSupportProject(int orderNo);     // 주문내역 취소

    void mypageInsertNews(NewsDTO newsDTO);     // 새소식 등록

    List<NewsDTO> getNewsList(int projectNo);       // 새소식 리스트 출력

    NewsDTO getRegistNewsDetail(int newsNo);        // 새소식 상세 출력

    void updateNewsDetail(NewsDTO newsDTO);     // 새소식 수정

    void mypageDeleteNews(int newsNo, int projectNo);      // 새소식 삭제

//    String getInputProductImg(int projectNo);       // 프로젝트 이미지 출력
}
