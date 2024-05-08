package com.recycle.ecoeco.makerProject.model.dao.user;

import com.recycle.ecoeco.makerProject.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProjectMapper {

    //상세페이지 번호?
    ProjectDTO findById(int projectNo);

    // 프로젝트 정보 등록
    int registProjectInfo(ProjectDTO projectDTO);
    //프로젝트 이미지 등록
    int registProjectThumbnail(ProjectThumbnailDTO projectThumbnailDTO);


    //프로젝트 조회(뷰에 뿌려줌)
    ProjectDTO inquiryProjectInfo(int projectNo);


    // 메이커 정보 등록
    int registMakerInfo(MakerDTO makerDTO);
    //메이커 프로필
    int registMakerProfileInfo(MakerProfileDTO makerProfileDTO);

    int registRewardInfo(RewardDTO rewardDTO);
    List<RewardDTO> getAllRewards(int projectNo); //리워드 조회
    int deleteReward(int rewardNo); // 리워드 삭제

    int registStoryInfo(StoryDTO storyDTO);//스토리매퍼

    int registStoryRepImage(StoryRepImgDTO storyRepImgDTO);//스토리 대표이미지 매퍼

    int countRewardByRewardNo(int rewardNo);//리워드 삭제 테스트

}
