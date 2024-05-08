package com.recycle.ecoeco.membership.service.user;

import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.accounting.model.dto.SettlementDTO;
import com.recycle.ecoeco.makerProject.model.dto.NewsDTO;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.membership.model.dao.user.MypageMapper;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import com.recycle.ecoeco.membership.model.dto.UserProfileImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MyPageService {

    private final MypageMapper mypageMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public MyPageService(MypageMapper mypageMapper, EmailService emailService) {
        this.mypageMapper = mypageMapper;
        this.emailService = emailService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void joinus(UserInfoDTO user) {
        if(user.getUserPwd() != null) {
            String encodedPassword = passwordEncoder.encode(user.getUserPwd());
            user.setUserPwd(encodedPassword);
        }

        // 회원 가입일 설정
        LocalDate signUpDate = LocalDate.now();

        user.setUserDate(signUpDate);
        System.out.println("User Date Set : " + user.getUserDate());

        // DAO를 통해 회원 정보 DB저장
        mypageMapper.joinus(user);
        System.out.println("User Info Saved to Database");
    }

    public boolean selectUserById(String userId) {
        UserInfoDTO user = mypageMapper.selectUserById(userId);
        return user != null;
    }

    public String findIdByUserNameAndEmail(String userName, String userEmail) {

        UserInfoDTO user = mypageMapper.findIdByUserNameAndEmail(userName, userEmail);

        if (user != null) {
            return user.getUserId();        // 사용자 정보가 있으면 아이디 반환
        } else {
            return null;        // 사용자 정보가 없으면 null 반환
        }
    }

    public String findPwdByUserIdAndUserEmail(String userId, String userEmail) {
        String userPwd = mypageMapper.findPwdByUserIdAndUserEmail(userId, userEmail);

        if(userPwd != null) {
            String temporaryPassword = generateTemporaryPassword();     // 임시 비밀번호 생성
            boolean emailSent = emailService.sendTemporaryPasswordEmail(userEmail, temporaryPassword);

            if(emailSent) {
                mypageMapper.updateUserPassword(passwordEncoder.encode(temporaryPassword), userId);
                return "입력하신 이메일로 임시 비밀번호가 발송되었습니다.";
            } else {
                return "임시 비밀번호 발송에 실패했습니다.";
            }
        } else {
            return "등록된 회원정보가 없습니다.";
        }
    }

    private String generateTemporaryPassword() {
        int length = 10; // 임시 비밀번호의 길이를 결정합니다. 여기서는 10으로 설정합니다.
        StringBuilder sb = new StringBuilder(length);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*"; // 사용할 문자열을 정의합니다.

        // SecureRandom을 사용하여 안전한 랜덤값을 생성합니다.
        SecureRandom random = new SecureRandom();

        // 지정된 길이만큼 반복하여 임시 비밀번호를 생성합니다.
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString(); // 생성된 임시 비밀번호를 반환합니다.
    }

    public UserInfoDTO findByUserId(String userId) {
        return mypageMapper.findByUserId(userId);
    }

    /* 내정보 수정*/
    public void updateUserInfo(UserInfoDTO userInfoDTO) {
        mypageMapper.updateUserInfo(userInfoDTO);
    }

    /* 회원탈퇴 */
    public void deleteUserInfo(String userId) {

        mypageMapper.deleteUserInfo(userId);
    }

    /* 프로필 사진 변경 */
    public void uploadProfileImage(MultipartFile file, int userNo) {
        try {
            String root = "src/main/resources/static";
            // 파일 저장 경로 설정 (예시: 프로필 이미지는 /uploads/profiles 디렉토리에 저장)
            String uploadDir = root + "/uploadFiles/user/mypage";

            // 파일명 생성 (UUID를 사용하여 파일명 중복 방지)
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // 저장할 경로 설정
            Path uploadPath = Paths.get(uploadDir);

            // 디렉토리가 없으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일 저장 경로 설정
            Path filePath = uploadPath.resolve(fileName);

            // 파일 복사
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // UserProfileImageDTO 생성
            UserProfileImageDTO userProfileImageDTO = new UserProfileImageDTO();
            userProfileImageDTO.setProfileImageFileName(file.getOriginalFilename());        // 변경한 파일이름
            userProfileImageDTO.setProfileImageSaveName(fileName);                          // 원래 파일 이름
            userProfileImageDTO.setProfileImagePath(uploadDir);                             // 디렉토리 저장 위치
            userProfileImageDTO.setUserNo(userNo);                                          // 회원번호

            System.out.println("=====================MyPageService ProfileImage=====================");
            System.out.println(fileName);
            System.out.println(file.getOriginalFilename());
            System.out.println(uploadDir);
            System.out.println(userNo);

            // 데이터베이스에 사진 정보 저장
            mypageMapper.insertUserProfileImage(userProfileImageDTO);

        } catch (IOException e) {
            // 파일 저장이 실패한 경우 예외 처리
            e.printStackTrace();
            // 필요에 따라 로깅하거나 예외 처리를 수행할 수 있습니다.
        }
    }

    public UserProfileImageDTO getLatestProfileImage(String userNo) {
        System.out.println("================latest-profile-image Service==================");
        return mypageMapper.getLatestProfileImage(userNo);
    }

    public List<ProjectDTO> getProjectByUserId(String userId) {
        return mypageMapper.getProjectByUserId(userId);
    }

    public ProjectDTO getProjectDetail(int projectNo) {

        return mypageMapper.getProjectDetail(projectNo);
    }

    public List<PaymentDTO> getPaymentInfo(String projectNo) {
        return mypageMapper.getPaymentInfo(projectNo);
    }

    public List<SettlementDTO> getSettlementInfo(String projectNo) {
        return mypageMapper.getSettlementInfo(projectNo);
    }

    /* 정산내역 */
    public SettlementDTO getSettlementDTO(int settlementNo) {

        System.out.println("settlementNo = " + settlementNo);
        System.out.println("Query = " + mypageMapper.getSettlementDTO(settlementNo));

        return mypageMapper.getSettlementDTO(settlementNo);
    }

    public List<ProjectDTO> getSupportMyProjectList(String userId) {
        System.out.println("mypageMapper.getSupportMyProjectList(userId) = " + mypageMapper.getSupportMyProjectList(userId));

        List<ProjectDTO> projectDTO = mypageMapper.getSupportMyProjectList(userId);
        System.out.println("projectDTO = " + projectDTO);

        return projectDTO;
    }

    public ProjectDTO getSupportProjectDetail(String projectNo) {
        return mypageMapper.getSupportProjectDetail(projectNo);
    }

    public void refundSupportProject(int orderNo) {
        mypageMapper.refundSupportProject(orderNo);
    }

    public void mypageInsertNews(NewsDTO newsDTO) {
        mypageMapper.mypageInsertNews(newsDTO);
    }


    public List<NewsDTO> getNewsList(int projectNo) {
        return mypageMapper.getNewsList(projectNo);
    }

    public NewsDTO getRegistNewsDetail(int newsNo) {

        System.out.println("getRegistNewsDetail service newsNo = " + newsNo);
        return mypageMapper.getRegistNewsDetail(newsNo);
    }

    public void updateNewsDetail(NewsDTO newsDTO) {
        mypageMapper.updateNewsDetail(newsDTO);
    }

    public void mypageDeleteNews(int newsNo, int projectNo) {
        mypageMapper.mypageDeleteNews(newsNo, projectNo);
    }

//    public String getInputProductImg(int projectNo) {
//
//        String inputProductImg = mypageMapper.getInputProductImg(projectNo);
//
//        return inputProductImg;
//    }
}
