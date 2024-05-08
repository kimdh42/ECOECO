package com.recycle.ecoeco.makerProject.model.dao.manager;

import com.recycle.ecoeco.common.paging.SelectCriteria;
import com.recycle.ecoeco.makerProject.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminProjectMapper {

    // 리스트 총 개수
    int selectTotalCount(Map<String, String> searchMap2);

    // 프로젝트 신규 리스트
    List<ProjectDTO> findProjectNewList(SelectCriteria selectCriteria);

    // 프로젝트 진행 리스트
    List<ProjectDTO> findProjectNowList(SelectCriteria selectCriteria);

    // 프로젝트 종료 리스트
    List<ProjectDTO> findProjectEndList(SelectCriteria selectCriteria);

    // 프로젝트 반려 리스트
    List<ProjectDTO> findProjectReturnList(SelectCriteria selectCriteria);


    // 프로젝트 정보 상세 페이지
    ProjectDTO projectDetail(int projectNo);

    // 프로젝트 정보 수정 페이지
    ProjectDTO projectModify(int projectNo);

    // 프로젝트 정보 수정 카테고리 리스트
    List<CategoryDTO> findCategoryList();

    // 프로젝트 정보 수정
    int modifyProject(Map<String, Object> parameterMap);

    // 프로젝트 새소식
    List<NewsDTO> projectNews(int projectNo);

    // 프로젝트 새소식 삭제
    int deleteNews(List<Integer> selectedNews);

    // 프로젝트 리뷰
    List<ReviewDTO> projectReview(int projectNo);

    // 프로젝트 리뷰 삭제
    int deleteReview(List<Integer> selectedReviews);

    // 프로젝트 삭제 시작
    // 프로젝트 정보 삭제
    int deleteProjectInfo(int projectNo);
    // 프로젝트 메이커 삭제
    int deleteProjectMaker(int projectNo);
    // 프로젝트 스토리 삭제
    int deleteProjectStory(int projectNo);
    // 프로젝트 리워드 삭제
    int deleteProjectReward(int projectNo);

    // 프로젝트 삭제 시 연결 DB 검색
    Map<String, String> findProjectDeleteDB(int projectNo);
}
