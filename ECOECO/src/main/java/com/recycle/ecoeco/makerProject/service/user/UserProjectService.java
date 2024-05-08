package com.recycle.ecoeco.makerProject.service.user;

import com.recycle.ecoeco.makerProject.model.dao.user.UserProjectMapper;
import com.recycle.ecoeco.makerProject.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserProjectService {


    private final UserProjectMapper userProjectMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;


    @Autowired
    public UserProjectService(UserProjectMapper userProjectMapper) {
        this.userProjectMapper = userProjectMapper;
    }


    public int registProjectInfo(ProjectDTO projectDTO) {
        int result = userProjectMapper.registProjectInfo(projectDTO);
        return result;
    }

    public int registMakerInfo(MakerDTO makerDTO) {
        int result = userProjectMapper.registMakerInfo(makerDTO);

        return result;
    }

    public int makerBoard(MakerDTO makerDTO){

        int result = userProjectMapper.registMakerInfo(makerDTO);
        System.out.println("스토리 DTO 테스트 :" + makerDTO);
        int makerNo= makerDTO.getProjectNo();


        makerDTO.getMakerProfileDTOList().forEach(makerProfileDTO -> {
            makerProfileDTO.setMakerNo(makerNo);
            makerProfileDTO.setMakerImageFileName(makerProfileDTO.getMakerImageFileName());
            makerProfileDTO.setMakerImageSaveName(makerProfileDTO.getMakerImageSaveName());
            makerProfileDTO.setMakerImagePath(makerProfileDTO.getMakerImagePath());
            System.out.println("서비스 메서드1(storyRepImgDTO): " +  makerProfileDTO);

//            boardMapper.insertAttachment(attachmentDTO);
            userProjectMapper.registMakerProfileInfo(makerProfileDTO);
        });
        return result;
    }


    //    여기서 부터 리워드
    public int registRewardInfo(RewardDTO rewardDTO) {//리워드 등록
        int result = userProjectMapper.registRewardInfo(rewardDTO);
        return result;
    }

    //리워드 조회
    public List<RewardDTO> getAllRewards(int projectNo) { //리워드 조회
        return userProjectMapper.getAllRewards(projectNo);
    }

    public ProjectDTO inquiryProjectInfo(int projectNo){return userProjectMapper.inquiryProjectInfo(projectNo);}

    //리워드 삭제
    public int deleteReward(int rewardNo) {
//       if (!rewardExists(rewardNo)) {
//           throw new NoSuchElementException("Reward not found with rewardNo: " + rewardNo);
//       }
        System.out.println(rewardNo);
        return userProjectMapper.deleteReward(rewardNo);
    }


    // rewardNo에 해당하는 데이터가 존재하는지 확인하는 메서드
    private boolean rewardExists(int rewardNo) {
        // SubMapper에서 rewardNo에 해당하는 데이터 조회
        int count = userProjectMapper.countRewardByRewardNo(rewardNo);

        // 조회한 데이터의 개수가 1 이상인 경우 true 반환
        return count > 0;
    }


    //    프로젝트 썸네일
    public int projectBoard(ProjectDTO projectDTO){

        int result = userProjectMapper.registProjectInfo(projectDTO);
        System.out.println("스토리 DTO 테스트 :" + projectDTO);
        int projectNo = projectDTO.getProjectNo();


        projectDTO.getProjectThumbnailDTO().forEach(projectThumbnailDTO -> {
            projectThumbnailDTO.setProjectNo(projectNo);
            projectThumbnailDTO.setProjectImageFileName(projectThumbnailDTO.getProjectImageFileName());
            projectThumbnailDTO.setProjectImageSaveName(projectThumbnailDTO.getProjectImageSaveName());
            projectThumbnailDTO.setProjectImagePath(projectThumbnailDTO.getProjectImagePath());
            System.out.println("서비스 메서드1(storyRepImgDTO): " +  projectThumbnailDTO);

//            boardMapper.insertAttachment(attachmentDTO);
            userProjectMapper.registProjectThumbnail(projectThumbnailDTO);
        });
        return result;
    }



    //    스토리 대표 이미지
    public int storyBoard(StoryDTO storyDTO){

        int result = userProjectMapper.registStoryInfo(storyDTO);
        System.out.println("스토리 DTO 테스트 :" + storyDTO);
        int storyNo = storyDTO.getStoryNo();


        storyDTO.getStoryRepImg().forEach(storyRepImgDTO -> {
            storyRepImgDTO.setStoryNo(storyNo);
            storyRepImgDTO.setStoryImageFileName(storyRepImgDTO.getStoryImageFileName());
            storyRepImgDTO.setStoryImageSaveName(storyRepImgDTO.getStoryImageSaveName());
            storyRepImgDTO.setStoryImagePath(storyRepImgDTO.getStoryImagePath());
            System.out.println("서비스 메서드1(storyRepImgDTO): " +  storyRepImgDTO);

//            boardMapper.insertAttachment(attachmentDTO);
            userProjectMapper.registStoryRepImage(storyRepImgDTO);
        });
        return result;
    }

}

