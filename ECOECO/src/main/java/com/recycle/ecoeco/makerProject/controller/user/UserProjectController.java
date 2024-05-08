package com.recycle.ecoeco.makerProject.controller.user;

import com.recycle.ecoeco.makerProject.model.dao.user.UserProjectMapper;
import com.recycle.ecoeco.makerProject.model.dto.*;
import com.recycle.ecoeco.makerProject.service.user.UserProjectService;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Slf4j
@Controller
@RequestMapping("/user/project")
public class UserProjectController {
    private final UserProjectService userProjectService;
    private final UserProjectMapper userProjectMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private static final Logger log = LoggerFactory.getLogger(UserProjectController.class);

    @Autowired
    public UserProjectController(UserProjectService userProjectService ,UserProjectMapper userProjectMapper) {
        this.userProjectService = userProjectService;
        this.userProjectMapper = userProjectMapper;
    }





    //프로젝트 정보 등록 페이지
    @GetMapping("/projectInfo/projectInfo")
    public void projectInfo(){
    }




    //프로젝트 정보 등록
    @PostMapping("/registProjectInfo")
    public String registProjectInfo(@ModelAttribute ProjectDTO projectDTO,
                                    MultipartFile projectThumbnail){

        int result= userProjectService.registProjectInfo(projectDTO);

//        System.out.println(projectDTO);

        if(result > 0) {

            String projectPath = IMAGE_DIR + "projectThumbnail";
            File dir = new File(projectPath);
            if(!dir.exists()) dir.mkdirs();
//            projectDTO.setProjectNo(projectNo);
            //이미지 정보 확인하고 처리
            //StoryRepImgDTO storyRepImg = null;

            List<ProjectThumbnailDTO> pjThumbNail = new ArrayList<>();
            try {
                if (projectThumbnail.getSize() > 0) {
                    // 이미지 파일이 업로드된 경우
                    String projectOriginFileName = projectThumbnail.getOriginalFilename();
                    log.info("originalFileName : {}", projectOriginFileName);

                    String ext = projectOriginFileName.substring(projectOriginFileName.lastIndexOf("."));
                    String projectSaveName = UUID.randomUUID() + ext;
                    String fileFullPath = Paths.get(projectPath, projectSaveName).toString();
                    log.info("savedFileName : {}", projectSaveName);

                    /* 서버의 설정 디렉토리에 파일 저장하기 */
                    //storyImg.transferTo(new File(storyPath + "/" + storySaveName));
                    // storyImg.transferTo(new File(storyPath));

                    System.out.println("projectImg 로그 찍어보기: "+ pjThumbNail);

                    try {
                        projectThumbnail.transferTo(new File(projectPath + "/" + projectSaveName));

                        System.out.println("프로젝트 파일 업로드 완료!");
                        System.out.println(fileFullPath);
                    } catch (IOException e) {
                        System.out.println("프로젝트 파일 업로드 실패!");
                        System.out.println(fileFullPath);
                    }
                    /* DB에 저장할 파일의 정보 설정 */
                    //storyRepImg = new StoryRepImgDTO();
//                    projectDTO.setProjectNo(projectNo);
                    ProjectThumbnailDTO fileInfo = new ProjectThumbnailDTO();
                    fileInfo.setProjectImageFileName(projectOriginFileName);
                    fileInfo.setProjectImageSaveName(projectSaveName);
                    fileInfo.setProjectImagePath("/uploadFiles/projectThumbnail/");
                    pjThumbNail.add(fileInfo);
                    System.out.println("프로젝트 대표 이미지 : "+ pjThumbNail);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            projectDTO.setProjectThumbnailDTO(pjThumbNail);

            // 게시글 서비스를 통해 게시글 작성
            userProjectService.projectBoard(projectDTO); // 이미지 파일도 함께 전달

            return "redirect:/user/project/maker/maker?projectNo=" + projectDTO.getProjectNo();
        } else {
            return "/templates/main";
        }
    }


    //메이커 등록 페이지
    @GetMapping("/maker/maker")
    public void maker(@RequestParam int projectNo, Model model){
        model.addAttribute("projectNo",projectNo);
//        return "/user/project/maker/maker";
    }

    //메이커 등록
    @PostMapping("/registMaker")
    public String registMakerInfo(@ModelAttribute MakerDTO makerDTO,
                                  @RequestParam int projectNo,
                                  MultipartFile makerProfile,
                                  @AuthenticationPrincipal UserInfoDTO userInfoDTO
    ){
//        System.out.println(projectNo);

        makerDTO.setUserNo(userInfoDTO.getUserNo());
//        int result= userProjectService.registMakerInfo(makerDTO);

//        if(result > 0) {
        String makerPath = IMAGE_DIR + "maker";
        File dir = new File(makerPath);
        if(!dir.exists()) dir.mkdirs();
//            projectDTO.setProjectNo(projectNo);
        //이미지 정보 확인하고 처리
        //StoryRepImgDTO storyRepImg = null;

        List<MakerProfileDTO> makerPrImg = new ArrayList<>();


        try {
            if (makerProfile.getSize() > 0) {
                // 이미지 파일이 업로드된 경우
                String makerOriginFileName = makerProfile.getOriginalFilename();
                log.info("originalFileName : {}", makerOriginFileName);

                String ext =makerOriginFileName.substring(makerOriginFileName.lastIndexOf("."));
                String makerSaveName = UUID.randomUUID() + ext;
                String fileFullPath = Paths.get(makerPath, makerSaveName).toString();
                log.info("savedFileName : {}", makerSaveName);

                /* 서버의 설정 디렉토리에 파일 저장하기 */
                //storyImg.transferTo(new File(storyPath + "/" + storySaveName));
                // storyImg.transferTo(new File(storyPath));

                System.out.println("projectImg 로그 찍어보기: "+ makerPrImg);


                try {
                    makerProfile.transferTo(new File(makerPath + "/" + makerSaveName));

                    System.out.println("프로젝트 파일 업로드 완료!");
                    System.out.println(fileFullPath);
                } catch (IOException e) {
                    System.out.println("프로젝트 파일 업로드 실패!");
                    System.out.println(fileFullPath);
                }
                /* DB에 저장할 파일의 정보 설정 */
                makerDTO.setProjectNo(projectNo);
                MakerProfileDTO fileInfo = new MakerProfileDTO();
                fileInfo.setMakerImageFileName(makerOriginFileName);
                fileInfo.setMakerImageSaveName(makerSaveName);
                fileInfo.setMakerImagePath("/uploadFiles/maker/");
                makerPrImg.add(fileInfo);
                System.out.println("프로젝트 대표 이미지 : "+ makerProfile);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        makerDTO.setMakerProfileDTOList(makerPrImg);
        // 게시글 서비스를 통해 게시글 작성
        userProjectService.makerBoard(makerDTO); // 이미지 파일도 함께 전달

        return "redirect:/user/project/reward/reward?projectNo=" + projectNo;
//        } else {
//            return "index";
//        }
    }

    //리워드 등록 페이지
    @GetMapping("/reward/reward")
    public void getAllReward(@RequestParam int projectNo, Model model){
        System.out.println("reward insert test:" + projectNo);
        List<RewardDTO> result = userProjectService.getAllRewards(projectNo);
        model.addAttribute("reward", result);  // 모델에 리워드 리스트 추가
        model.addAttribute("projectNo",projectNo);
    }

    @PostMapping("/rewardInsert")//리워드 등록
//    public String registRewardInfo(@ModelAttribute RewardDTO rewardDTO,@RequestParam int projectNo){
    public String registRewardInfo(RewardDTO rewardDTO, @RequestParam int projectNo){
        System.out.println("projectNo1 : " + projectNo);
        System.out.println("rewardDTO1 : " + rewardDTO);
        int result= userProjectService.registRewardInfo(rewardDTO);

        System.out.println("projectNo2 : " + projectNo);
        if(result > 0) {
            return "redirect:/user/project/reward/reward?projectNo=" + projectNo;

        } else {
            return "/templates/main";
        }
    }

    @PostMapping("/deleteReward")//리워드 삭제
    public String deleteReward(@RequestParam int rewardNo, @RequestParam int projectNo){
        System.out.println("rewardNo1 : " + rewardNo);
//        System.out.println("projectNo1 : " + projectNo);
        int result= userProjectService.deleteReward(rewardNo);
        System.out.println("rewardNo2 : " + rewardNo);
        if(result > 0) {
//            System.out.println("외않덴데");
            return "redirect:/user/project/reward/reward?projectNo=" + projectNo;
        } else {
            return "/user/project/reward/reward";
        }
    }



    @GetMapping("/terms/terms")
    public void terms(){}


    @GetMapping("/story/story")
    public void Story(@RequestParam int projectNo, Model model){
        model.addAttribute("projectNo", projectNo);

    }



    @PostMapping("/saveStory") // 스토리 컨트롤러
    public String saveStory(@ModelAttribute StoryDTO storyDTO,
                            @RequestParam int projectNo,
                            @RequestParam MultipartFile storyImg,
                            Model model){
//        int result = userProjectService.storyBoard(storyDTO);

        String storyPath = IMAGE_DIR + "story";
        File dir = new File(storyPath);
        if(!dir.exists()) dir.mkdirs();
        storyDTO.setProjectNo(projectNo);
        //이미지 정보 확인하고 처리
        //StoryRepImgDTO storyRepImg = null;

        List<StoryRepImgDTO> storyRepImg = new ArrayList<>();

        try {
            if (storyImg.getSize() > 0) {
                // 이미지 파일이 업로드된 경우
                String StoryOriginFileName = storyImg.getOriginalFilename();
                log.info("originalFileName : {}", StoryOriginFileName);

                String ext = StoryOriginFileName.substring(StoryOriginFileName.lastIndexOf("."));
                String storySaveName = UUID.randomUUID() + ext;
                String fileFullPath = Paths.get(storyPath, storySaveName).toString();
                log.info("savedFileName : {}", storySaveName);

                /* 서버의 설정 디렉토리에 파일 저장하기 */
                //storyImg.transferTo(new File(storyPath + "/" + storySaveName));
                // storyImg.transferTo(new File(storyPath));

                System.out.println("storyImg 로그 찍어보기: "+storyImg);


                try {
                    storyImg.transferTo(new File(storyPath + "/" + storySaveName));

                    System.out.println("파일 업로드 완료!");
                    System.out.println(fileFullPath);
                } catch (IOException e) {
                    System.out.println("파일 업로드 실패!");
                    System.out.println(fileFullPath);
                }
                /* DB에 저장할 파일의 정보 설정 */
                //storyRepImg = new StoryRepImgDTO();
                storyDTO.setProjectNo(projectNo);
                StoryRepImgDTO fileInfo = new StoryRepImgDTO();
                fileInfo.setStoryImageFileName(StoryOriginFileName);
                fileInfo.setStoryImageSaveName(storySaveName);
                fileInfo.setStoryImagePath("/uploadFiles/story/");
                storyRepImg.add(fileInfo);
                System.out.println("스토리 대표 이미지 : "+ storyRepImg);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        storyDTO.setStoryRepImg(storyRepImg);

        // 게시글 서비스를 통해 게시글 작성
        userProjectService.storyBoard(storyDTO); // 이미지 파일도 함께 전달
        model.addAttribute("storyInfo",storyDTO.getStoryInfo());
        System.out.println("이거맞음?: "+storyDTO.getStoryInfo());

        // 게시글 리스트로 리다이렉트
        //return "redirect:/user/project/story/story?projectNo=" + projectNo;
        return "/main";
    }


    @GetMapping("/detail/detail")
    public String detail(@ModelAttribute ProjectDTO projectDTO , @ModelAttribute RewardDTO rewardDTO,@RequestParam int projectNo, Model model){
        ProjectDTO projectResult = userProjectService.inquiryProjectInfo(projectNo);
        List<RewardDTO> rewardResult = userProjectService.getAllRewards(projectNo);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("inquiry",projectResult);
        model.addAttribute("reward",rewardResult);
        model.addAttribute("projectCategoryNo",projectDTO);
        return "/user/project/detail/detail";
    }

    //    임시 매핑입니다 추후 연결 예정입니다
    @GetMapping("/detail/detail-news")
    public void detailNews(){}

    @GetMapping("/detail/detail-refund")
    public void detailRefund(){}

    @GetMapping("/detail/detail-review")
    public void detailReview(){}

    @GetMapping("/detail/detail-reward")
    public String detailReward(@ModelAttribute ProjectDTO projectDTO , @ModelAttribute RewardDTO rewardDTO,@RequestParam int projectNo, Model model){
        ProjectDTO projectResult = userProjectService.inquiryProjectInfo(projectNo);
        List<RewardDTO> rewardResult = userProjectService.getAllRewards(projectNo);
        model.addAttribute("inquiry",projectResult);
        model.addAttribute("reward",rewardResult);
        model.addAttribute("projectCategoryNo",projectDTO);
        return "/user/project/detail/detail-reward";
    }
}
