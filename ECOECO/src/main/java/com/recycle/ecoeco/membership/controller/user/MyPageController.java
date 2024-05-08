package com.recycle.ecoeco.membership.controller.user;

import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.accounting.model.dto.SettlementDTO;
import com.recycle.ecoeco.makerProject.model.dto.NewsDTO;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import com.recycle.ecoeco.membership.model.dto.UserProfileImageDTO;
import com.recycle.ecoeco.membership.service.user.AuthService;
import com.recycle.ecoeco.membership.service.user.EmailService;
import com.recycle.ecoeco.membership.service.user.MyPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.recycle.ecoeco.membership.service.user.PasswordGenerator.generateTemporaryPassword;


@Slf4j
@Controller
@RequestMapping("/user/mypage")
public class MyPageController {


    private ProjectDTO projectDTO;
    private UserProfileImageDTO userProfileImageDTO;
    private final MyPageService myPageService;
    private AuthService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public MyPageController(AuthService authenticationService,MyPageService myPageService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userProfileImageDTO = userProfileImageDTO;
        this.myPageService = myPageService;
        this.emailService = emailService;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    /* 로그인 실패 시 */
    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
        System.out.println("로그인 실패");
        return "redirect:/login";
    }

    /* 로그아웃 */
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/main";
    }

    /* 로그인 상태 */
    @GetMapping("/loginStatus")
    public ResponseEntity<Object> loginStatus() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean loginStatus =  authentication != null &&  authentication.isAuthenticated();

        return ResponseEntity.ok().body(Map.of("loginStatus", loginStatus));
    }

    /* 아이디 중복 검사 */
    @ResponseBody
    @GetMapping("/selectUserById")
    public ResponseEntity<String> selectUserById(@RequestParam String userId) {

        boolean isDuplicate = myPageService.selectUserById(userId);

        if(isDuplicate) {
            return ResponseEntity.status(HttpStatus.OK).body("true");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("false");
        }
    }

    /* 아이디 비밀번호 찾기 선택 페이지 이동 */
    @GetMapping("/findSearchInfo")
    public void findSearchInfoPage() {}

    /* 아이디 찾기 페이지 이동 */
    @GetMapping("/findId")
    public void findIdPage() {}

    /* 아이디 찾기 */
    @PostMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail) {

        // 서비스로부터 아이디를 찾는 로직을 호출하고 결과를 반환합니다.
        String foundUserId = myPageService.findIdByUserNameAndEmail(userName, userEmail);

        if (foundUserId != null) {
            // 아이디를 찾았을 경우
            return ResponseEntity.ok("회원님의 아이디는 " + foundUserId + " 입니다.");
        } else {
            // 아이디를 찾지 못했을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("등록된 회원이 아닙니다.");
        }
    }

    /* 비밀번호 찾기 페이지 이동 */
    @GetMapping("/findPwd")
    public void findPwdPage() {}

    /* 비밀번호 찾기 구현 메소드 */
    @PostMapping("/findPwd")
    public ResponseEntity<String> findPwd(@RequestParam("searchPwdId") String userId, @RequestParam("searchPwdEmail") String userEmail) {

        // userId와 userEmail이 데이터베이스에 등록되어 있는지 확인
        UserInfoDTO user = myPageService.findByUserId(userId);
        if(user == null || !user.getUserEmail().equals(userEmail)) {
            // 등록되지 않은 사용자 정보이거나 이메일이 일치하지 않는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("등록된 회원정보가 없습니다.");
        }

        String foundPwd = myPageService.findPwdByUserIdAndUserEmail(userId, userEmail);

        if(foundPwd != null) {
            String temporaryPassword = generateTemporaryPassword();     // 임시비밀번호 생성
            boolean emailSent = emailService.sendTemporaryPasswordEmail(userEmail, temporaryPassword);

            System.out.println("userEmail : " + userEmail);
            System.out.println("temporaryPassword : " + temporaryPassword);
            System.out.println("emailSent : " + emailSent);

            if(emailSent) {
                // 임시 비밀번호 전송 성공 시 db에 임시 비밀번호 저장

                // 이메일 전송 성공 메시지
                return ResponseEntity.ok("입력하신 이메일로 임시 비밀번호가 발송되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("임시 비밀번호 발송에 실패하였습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("등록된 회원정보가 없습니다.");
        }
    }

    /* 마이페이지 이동 메인화면 이동 */
    @GetMapping("/mypageMain")
    public void mypageMainPage() {}

    /* 서포터 나의 프로젝트 이동 */
    @GetMapping("/mypage_MyProject")
    public void mypage_MyProjectPage() {}

    /* 후원한 프로젝트 이동 */
    @GetMapping("/mypage_SupportProject")
    public String mypage_SupportProjectPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        List<ProjectDTO> projectDTO = myPageService.getSupportMyProjectList(userId);

        System.out.println("mypage_SupportProject userId = " + userId);
        System.out.println("projectDTO = " + myPageService.getSupportMyProjectList(userId));

        model.addAttribute("projectDTO", projectDTO);

        return "/user/mypage/mypage_SupportProject";
    }

    /* 후원한 프로젝트 상세보기 이동 */
    @GetMapping("/mypage_SupportProject_Detail")
    public String getSupportProjectDetail(@RequestParam("projectNo") String projectNo, Model model) {
        // 프로젝트 번호를 사용하여 DB에서 프로젝트 상세 정보를 가져옴
        ProjectDTO projectDTO = myPageService.getSupportProjectDetail(projectNo);
        // 모델에 프로젝트 정보 추가
        model.addAttribute("projectDTO", projectDTO);
        // 상세 페이지로 이동
        return "/user/mypage/mypage_SupportProject_Detail";
    }

    /* 후원한 프로젝트 주문 취소 */
//    @PostMapping("/mypage_SupportProject_Detail")
//    public ResponseEntity<String> refundSupportProject(@RequestParam("orderNo") int orderNo) {
//        try {
//            // 주문 취소 로직 실행
//            myPageService.refundSupportProject(orderNo);
//
//            System.out.println("orderNo = " + orderNo);
//
//            return new ResponseEntity<>("주문 취소가 완료되었습니다.", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("주문 취소에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/refundSupportProject") // 엔드포인트 URL 변경
    public ResponseEntity<String> refundSupportProject(@RequestParam("orderNo") int orderNo) {
        try {
            // 주문 취소 로직 실행
            myPageService.refundSupportProject(orderNo);

            System.out.println("orderNo = " + orderNo);

            return new ResponseEntity<>("주문 취소가 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("주문 취소에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* 내정보 이동 */
    @GetMapping("/mypage_CheckMyInfo")
    public String mypage_CheckMyInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userId = auth.getName();

        System.out.println("auth.getName() : " + auth.getName());

        UserInfoDTO userInfoDTO = myPageService.findByUserId(userId);

        model.addAttribute("userInfoDTO", userInfoDTO);

        return "/user/mypage/mypage_CheckMyInfo";
    }

    /* 내정보 수정 */
    @PostMapping("/updateUserInfo")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        // 현재 사용자의 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        // 유효성 검사 및 수정된 내용을 서비스로 전달
        userInfoDTO.setUserId(userId); // userId 설정

        // 새로운 비밀번호가 입력되지 않은 경우
        if (userInfoDTO.getUserPwd() == null || userInfoDTO.getUserPwd().isEmpty()) {
            // 기존의 비밀번호를 가져와서 userInfoDTO에 설정
            UserInfoDTO existingUserInfo = myPageService.findByUserId(userId);
            userInfoDTO.setUserPwd(existingUserInfo.getUserPwd());
        } else {
            // 새로운 비밀번호가 입력된 경우, 비밀번호를 암호화하여 설정
            String encodedPassword = passwordEncoder.encode(userInfoDTO.getUserPwd());
            userInfoDTO.setUserPwd(encodedPassword);
        }

        myPageService.updateUserInfo(userInfoDTO);

        System.out.println("userInfoDTO = " + userInfoDTO);

        return ResponseEntity.ok("User info updated successfully.");
    }

    /* 파일 업로드 */
    @PostMapping("/uploadProfileImage")
    @ResponseBody // JSON 응답을 위한 어노테이션 추가
    public ResponseEntity<String> uploadProfileImage(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("userNo") int userNo,
                                                     RedirectAttributes redirectAttributes) {
        System.out.println("====================MyPageController ProfileImage====================");
        try {
            myPageService.uploadProfileImage(file, userNo);
            return ResponseEntity.ok().body("프로필 사진이 업로드되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("프로필 사진 업로드 중 오류가 발생했습니다.");
        }
    }

    /* 이미지 출력 */
    @GetMapping("/latest-profile-image")
    public ResponseEntity<String> getLatestProfileImage() {
        // 현재 사용자의 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authentication 객체에서 현재 사용자의 정보 가져오기
        String userNo = String.valueOf(((UserInfoDTO) authentication.getPrincipal()).getUserNo());

        UserProfileImageDTO latestImage = myPageService.getLatestProfileImage(userNo);
        System.out.println("userNo = " + userNo);
        if (latestImage != null) {
            System.out.println("================latest-profile-image Controller==================");
            String imageUrl = "/uploadFiles/user/mypage/" + latestImage.getProfileImageSaveName();
            return ResponseEntity.ok(imageUrl);
        } else {
            return ResponseEntity.ok("/images/user/mypage/myPageProfile02.png");
        }
    }

    /* 회원탈퇴 */
    @PostMapping("/deleteUserInfo")
    public String deleteUserInfo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userId =  auth.getName();

        System.out.println("탈퇴하는 userId : " + userId);
        myPageService.deleteUserInfo(userId);
        return "redirect:/main";
    }

    /* 메이커 페이지 이동 */
    @GetMapping("/mypageMain_Maker")
    public void mypageMain_MakerPage() {}

    /* 메이커 나의 프로젝트 이동 */
    /* 프로젝트 리스트 출력 */
    @GetMapping("/mypage_MyprojectList")
    public String mypage_MyprojectListPage(Model model) {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();

        String userId = authentication.getName();

        System.out.println("projectList userId = " + userId);

        List<ProjectDTO> projectDTO = myPageService.getProjectByUserId(userId);

        model.addAttribute("projectDTO", projectDTO);

        return "/user/mypage/mypage_MyprojectList";
    }

    /* 메이커 나의 프로젝트 상세화면 이동 */
    // 프로젝트 세부 정보 가져오기 컨트롤러
    @GetMapping("/mypage_MyprojectList_Detail")
    public String getProjectDetail(@RequestParam("projectNo") int projectNo, Model model) {
        // 프로젝트 번호를 사용하여 DB에서 프로젝트 상세 정보를 가져옴
        ProjectDTO projectDTO = myPageService.getProjectDetail(projectNo);
        // 모델에 프로젝트 정보 추가
        model.addAttribute("projectDTO", projectDTO);
        // 상세 페이지로 이동
        return "/user/mypage/mypage_MyprojectList_Detail";
    }

    /* 메이커 나의 프로젝트 상세페이지 결제현황 이동 */
    @GetMapping("/payment_Status")
    public String getPaymentStatus(@RequestParam("projectNo") String projectNo, Model model) {
        // Retrieve payment information from the database
        List<PaymentDTO> paymentDTO = myPageService.getPaymentInfo(projectNo);

        // Pass the payment information to the view
        model.addAttribute("paymentDTO", paymentDTO);

        return "/user/mypage/payment_Status";
    }

    /* 메이커 나의 프로젝트 상세페이지 정산정보 이동 */
    @GetMapping("/settlement_Info")
    public String settlementInfo(Model model, @RequestParam("projectNo") String projectNo) {

        List<SettlementDTO> settlementDTO = myPageService.getSettlementInfo(projectNo);
        model.addAttribute("settlementDTO", settlementDTO);
        return "/user/mypage/settlement_Info";
    }

    /* 메이커 나의 프로젝트 상세페이지 정산정보 -> 정산내역 이동 */
    @GetMapping("/settlement_Info_Details")
    public String settlementInfoDetails(@RequestParam("settlementNo") int settlementNo, Model model) {
        SettlementDTO settlementDTO = myPageService.getSettlementDTO(settlementNo);

        System.out.println("settlementNo = " + settlementNo);
        System.out.println("myPageService.getSettlementDTO(settlementNo) = " + myPageService.getSettlementDTO(settlementNo));
        model.addAttribute("settlementDTO", settlementDTO);
        return "/user/mypage/settlement_Info_Details";
    }

    /* 메이커 나의 프로젝트 상세페이지 새소식 이동 */
    @GetMapping("/news")
    public String newsPage(@RequestParam("projectNo")int projectNo, Model model) {

        List<NewsDTO> newsDTO = myPageService.getNewsList(projectNo);

        model.addAttribute("projectNo", projectNo);
        model.addAttribute("newsDTO", newsDTO);

        return "/user/mypage/news";
    }

    /* 새소식 등록 페이지 이동 */
    @GetMapping("/registNews")
    public void registNewsPage(@RequestParam("projectNo") int projectNo, Model model) {

        model.addAttribute("projectNo", projectNo);
    }

    /* 새소식 등록 */
    @PostMapping("/registNews")
    public String mypageInsertNews(NewsDTO newsDTO, @AuthenticationPrincipal UserInfoDTO userInfoDTO, @RequestParam("projectNo") int projectNo, Model model) {


        int userNo = userInfoDTO.getUserNo();
        newsDTO.setUserNo(userNo);

        myPageService.mypageInsertNews(newsDTO);

        System.out.println("@AuthenticationPrincipal UserInfoDTO userInfoDTO = " + userInfoDTO);
        System.out.println("newsDTO = " + newsDTO);
        System.out.println("userNo = " + userInfoDTO.getUserNo());

        model.addAttribute("projectNo", projectNo);

        return "redirect:/user/mypage/news?projectNo=" + projectNo;
    }

//    @RequestParam("newsCategory") String newsCategory,
//    @RequestParam("newsTitle") String newsTitle,
//    @RequestParam("newsContent") String newsContent,

    /* 새소식 조회 페이지 이동 */
    @GetMapping("/registNewsDetail")
    public String registNewsDetailPage(@RequestParam("newsNo") int newsNo, Model model) {

        NewsDTO newsDTO = myPageService.getRegistNewsDetail(newsNo);
//        model.addAttribute("newsDTO", newsDTO);

        System.out.println("newsNo = " + newsNo);

        model.addAttribute("newsDTO", newsDTO);
        log.info("newsDTO : {}", newsDTO);

        return "/user/mypage/registNewsDetail";
    }

    /* 새소식 수정 */
    @PostMapping("/updateNewsDetail/{newsNo}")
    public String updateNewsDetail(@RequestParam("newsNo") int newsNo,
                                   @RequestParam("projectNo") int projectNo,
                                   NewsDTO news,
                                   @AuthenticationPrincipal UserInfoDTO userInfoDTO) {

        //기존 DTO 정보 가져오기
        NewsDTO newsDTO = myPageService.getRegistNewsDetail(newsNo);
        log.info("newsDTO : {}", newsDTO);

        //새 DTO 만들어서 업데이트 하기
        NewsDTO newsTemp = newsDTO;
        newsTemp.setNewsTitle(news.getNewsTitle());
        newsTemp.setNewsContent(news.getNewsContent());
        newsTemp.setNewsCategory(news.getNewsCategory());
        log.info("newsTemp : {}", newsTemp);

        myPageService.updateNewsDetail(newsTemp);
        log.info("newDTO : {}", newsDTO);

        System.out.println("updateNewsDetail controller newsDTO = " + newsDTO);

        return "redirect:/user/mypage/news?projectNo=" + projectNo;
    }

    /* 새소식 삭제 */
    @PostMapping("/registNewsDelete")
    public String deleteNewsDetail(@RequestParam("projectNo") int projectNo, NewsDTO newsDTO) {

        int newsNo = newsDTO.getNewsNo();

        System.out.println("deleteNewsDetail controller newsDTO = " + newsDTO);
        System.out.println("deleteNewsDetail controller newsNo = " + newsNo);

        myPageService.mypageDeleteNews(newsNo, projectNo);
        return "redirect:/user/mypage/news?projectNo=" + projectNo;
    }

    /* 나의 프로젝트 상세 & 내가 후원한 프로젝트 상세 이미지 출력 */
//    @GetMapping("/inputProductImg/{projectNo}")
//    public ResponseEntity<String> getInputProductImg (@PathVariable @RequestParam("projectNo") int projectNo) {
//
//        String inputProductImg = myPageService.getInputProductImg(projectNo);
//
//        return new ResponseEntity<>(inputProductImg, HttpStatus.OK);
//    }
}
