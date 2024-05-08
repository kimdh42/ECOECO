package com.recycle.ecoeco.makerProject.service.manager;

import com.recycle.ecoeco.common.paging.Pagenation;
import com.recycle.ecoeco.common.paging.SelectCriteria;
import com.recycle.ecoeco.makerProject.model.dao.manager.AdminProjectMapper;
import com.recycle.ecoeco.makerProject.model.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminProjectService {

    private final AdminProjectMapper adminProjectMapper;

    public AdminProjectService(AdminProjectMapper adminProjectMapper) {
        this.adminProjectMapper = adminProjectMapper;
    }

    // 프로젝트 신규 리스트
    public Map<String, Object> findProjectNewList(Map<String, String> searchMap, int page, String listType) {
//        System.out.println("project_new_list_service");
//        System.out.println("searchMap : " + searchMap);

        // 게시글 수 확인용 searchMap2 생성
        Map<String, String> searchMap2 = new HashMap<>();
        searchMap2.put("listType", listType);

        // searchMap의 모든 요소를 searchMap2에 추가
        searchMap2.putAll(searchMap);

        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminProjectMapper.selectTotalCount(searchMap2);
//        log.info("boardList totalCount : {}", totalCount);
//        System.out.println("totalCount : " + totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap, listType);
//        log.info("boardList selectCriteria : {}", selectCriteria);
//        System.out.println("selectCriteria : " + selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<ProjectDTO> boardList = adminProjectMapper.findProjectNewList(selectCriteria);
//        log.info("boardList : {}", boardList);
//        System.out.println("boardList : " + boardList);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;
    }

    // 프로젝트 진행 리스트
    public Map<String, Object> findProjectNowList(Map<String, String> searchMap, int page, String listType) {
        System.out.println("project_now_list_service");
        System.out.println("searchMap : " + searchMap);

        // 게시글 수 확인용 searchMap2 생성
        Map<String, String> searchMap2 = new HashMap<>();
        searchMap2.put("listType", listType);

        // searchMap의 모든 요소를 searchMap2에 추가
        searchMap2.putAll(searchMap);

        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminProjectMapper.selectTotalCount(searchMap2);
//        log.info("boardList totalCount : {}", totalCount);
        System.out.println("totalCount : " + totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap, listType);
//        log.info("boardList selectCriteria : {}", selectCriteria);
        System.out.println("selectCriteria : " + selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<ProjectDTO> boardList = adminProjectMapper.findProjectNowList(selectCriteria);
//        log.info("boardList : {}", boardList);
        System.out.println("boardList : " + boardList);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;
    }

    // 프로젝트 종료 리스트
    public Map<String, Object> findProjectEndList(Map<String, String> searchMap, int page, String listType) {

        //        System.out.println("project_end_list_service");
                System.out.println("searchMap : " + searchMap);

        // 게시글 수 확인용 searchMap2 생성
        Map<String, String> searchMap2 = new HashMap<>();
        searchMap2.put("listType", listType);

        // searchMap의 모든 요소를 searchMap2에 추가
        searchMap2.putAll(searchMap);

        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminProjectMapper.selectTotalCount(searchMap2);
//        log.info("boardList totalCount : {}", totalCount);
        System.out.println("totalCount : " + totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap, listType);
//        log.info("boardList selectCriteria : {}", selectCriteria);
//        System.out.println("selectCriteria : " + selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<ProjectDTO> boardList = adminProjectMapper.findProjectEndList(selectCriteria);
//        log.info("boardList : {}", boardList);
        System.out.println("boardList : " + boardList);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;
    }

    // 프로젝트 반려 리스트
    public Map<String, Object> findProjectReturnList(Map<String, String> searchMap, int page, String listType) {
        //        System.out.println("project_return_list_service");
//        System.out.println("searchMap : " + searchMap);

        // 게시글 수 확인용 searchMap2 생성
        Map<String, String> searchMap2 = new HashMap<>();
        searchMap2.put("listType", listType);

        // searchMap의 모든 요소를 searchMap2에 추가
        searchMap2.putAll(searchMap);

        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminProjectMapper.selectTotalCount(searchMap2);
//        log.info("boardList totalCount : {}", totalCount);
//        System.out.println("totalCount : " + totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap, listType);
//        log.info("boardList selectCriteria : {}", selectCriteria);
//        System.out.println("selectCriteria : " + selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<ProjectDTO> boardList = adminProjectMapper.findProjectReturnList(selectCriteria);
//        log.info("boardList : {}", boardList);
//        System.out.println("boardList : " + boardList);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;
    }

    // 프로젝트 정보 상세 페이지
    public ProjectDTO projectDetail(int projectNo) {
        return adminProjectMapper.projectDetail(projectNo);
    }

    // 프로젝트 정보 수정 페이지
    public ProjectDTO projectModify(int projectNo) {
        return adminProjectMapper.projectModify(projectNo);
    }

    // 프로젝트 정보 수정 페이지 카테고리 리스트
    public List<CategoryDTO> findCategoryList() {
        return adminProjectMapper.findCategoryList();
    }

    // 프로젝트 정보 수정
    @Transactional
    public void modifyProject(ProjectDTO projectDTO, MakerDTO makerDTO) {

        Map<String, Object> parameterMap = new HashMap<>();

        parameterMap.put("projectNo", String.valueOf(projectDTO.getProjectNo()) != null ? projectDTO.getProjectNo() : 0);
        parameterMap.put("categoryCode", String.valueOf(projectDTO.getCategoryCode()) != null ? projectDTO.getCategoryCode() : 0);
        //parameterMap.put("projectStatus", projectDTO.getProjectStatus() != null ? projectDTO.getProjectStatus() : 0);
        parameterMap.put("projectStatus", projectDTO.getProjectStatus());
        parameterMap.put("startDate", projectDTO.getStartDate());
        parameterMap.put("endDate", projectDTO.getEndDate());
        parameterMap.put("phone", makerDTO.getPhone());
        parameterMap.put("email", makerDTO.getEmail());

        System.out.println("-------------------------------------------------");
        System.out.println("projectNo : " + parameterMap.get("projectNo"));
        System.out.println("categoryCode : " + parameterMap.get("categoryCode"));
        System.out.println("projectStatus : " + parameterMap.get("projectStatus"));
        System.out.println("startDate : " + parameterMap.get("startDate"));
        System.out.println("endDate : " + parameterMap.get("endDate"));
        System.out.println("phone : " + parameterMap.get("phone"));
        System.out.println("email : " + parameterMap.get("email"));
        System.out.println("-------------------------------------------------");

        int result = adminProjectMapper.modifyProject(parameterMap);

//            System.out.println("parameterMap : " + parameterMap);

        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }

    // 프로젝트 새소식
    public List<NewsDTO> projectNews(int projectNo) {
        return adminProjectMapper.projectNews(projectNo);
    }

    // 프로젝트 새소식 삭제
    public void deleteNews(List<Integer> selectedNews) {
        int result =  adminProjectMapper.deleteNews(selectedNews);

            System.out.println("selectedNews : " + selectedNews);

        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }

    // 프로젝트 리뷰
    public List<ReviewDTO> projectReview(int projectNo) {
        System.out.println("projectReview_service");

        List<ReviewDTO> boardList = adminProjectMapper.projectReview(projectNo);

        System.out.println("boardList : " + boardList);

        return boardList;
    }

    // 프로젝트 리뷰 삭제
    public void deleteReview(List<Integer> selectedReviews) {
        int result =  adminProjectMapper.deleteReview(selectedReviews);

        System.out.println("selectedNews : " + selectedReviews);

        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }

    // 프로젝트 삭제
    @Transactional
    public String deleteProject(int projectNo) {

        System.out.println("projectNo : " + projectNo);

        Map<String, String> find =  adminProjectMapper.findProjectDeleteDB(projectNo);
        System.out.println("find : " + find);

        if (find != null) {
            try {
                    Object settlementNo = find.get("settlementNo");
                    Object newsNO = find.get("newsNO");
                    Object supportReviewsNo = find.get("supportReviewsNo");
                    Object projectNoObject = find.get("projectNo");
                    Object makerNo = find.get("makerNo");
                    Object storyNo = find.get("storyNo");
                    Object rewardNo = find.get("rewardNo");

                    if (settlementNo != null && !String.valueOf(settlementNo).equals("0")) {
                        System.out.println("settlementNo : " + settlementNo);
//                        System.out.println("정산 내역이 있는 프로젝트는 삭제할 수 없습니다.");
                        String message = "정산 내역이 있는 프로젝트는 삭제할 수 없습니다.";
                        return message;
                    }
                    if (newsNO != null && !String.valueOf(newsNO).equals("0")) {
//                        System.out.println("메이커 새소식이 있는 프로젝트는 삭제할 수 없습니다.");
                        String message = "메이커 새소식이 있는 프로젝트는 삭제할 수 없습니다.";
                        return message;
                    }
                    if (supportReviewsNo != null && !String.valueOf(supportReviewsNo).equals("0")) {
//                        System.out.println("응원/리뷰가 있는 프로젝트는 삭제할 수 없습니다.");
                        String message = "응원/리뷰가 있는 프로젝트는 삭제할 수 없습니다.";
                        return message;
                    }

                    int result1 = 0, result2 = 0, result3 = 0, result4 = 0;

                    // 프로젝트 정보 삭제
                    if(projectNoObject != null) {
                        result1 =  adminProjectMapper.deleteProjectInfo(projectNo);
                    }
                    // 프로젝트 메이커 삭제
                    if(makerNo != null) {
                        result2 =  adminProjectMapper.deleteProjectMaker(projectNo);
                    }
                    // 프로젝트 스토리 삭제
                    if(storyNo != null) {
                        result3 =  adminProjectMapper.deleteProjectStory(projectNo);
                    }
                    // 프로젝트 리워드 삭제
                    if(rewardNo != null) {
                        result4 =  adminProjectMapper.deleteProjectReward(projectNo);
                    }

                    // 모든 삭제 작업이 성공적으로 수행되었는지 확인
                    if (result1 >= 0 && (makerNo == null || result2 >= 0) && (storyNo == null || result3 >= 0) && (rewardNo == null || result4 >= 0)) {
                        System.out.println("모든 삭제 작업이 성공적으로 수행되었습니다.");
                        String message = "모든 삭제 작업이 성공적으로 수행되었습니다.";
                        return message;
                    } else {
                        throw new RuntimeException("삭제 작업 중 오류가 발생하여 롤백됩니다.");
                    }
            } catch (Exception e) {
                System.out.println("삭제 작업 실패: " + e.getMessage());
                throw e; // 예외를 전파하여 롤백 처리
            }
        } else {
            System.out.println("프로젝트를 찾을 수 없습니다.");
            String message = "프로젝트를 찾을 수 없습니다.";
            return message;
        }
    }
}
