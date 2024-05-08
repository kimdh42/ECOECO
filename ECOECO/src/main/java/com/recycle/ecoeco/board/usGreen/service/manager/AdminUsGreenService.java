package com.recycle.ecoeco.board.usGreen.service.manager;

import com.recycle.ecoeco.board.usGreen.model.dao.manager.AdminUsGreenMapper;
import com.recycle.ecoeco.board.usGreen.model.dto.UsGreenDTO;
import com.recycle.ecoeco.common.paging.Pagenation;
import com.recycle.ecoeco.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class AdminUsGreenService {

    private final AdminUsGreenMapper adminUsGreenMapper;

    public AdminUsGreenService(AdminUsGreenMapper adminUsGreenMapper) {
        this.adminUsGreenMapper = adminUsGreenMapper;
    }

    public Map<String, Object> selectUsGreenList(Map<String, String> searchMap, int page) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminUsGreenMapper.selectTotalCount(searchMap);
        log.info("usGreenList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("usGreenList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<UsGreenDTO> usGreenList = adminUsGreenMapper.selectUsGreenList(selectCriteria);
        log.info("usGreenList : {}", usGreenList);

        Map<String, Object> usGreenListAndPaging = new HashMap<>();
        usGreenListAndPaging.put("paging", selectCriteria);
        usGreenListAndPaging.put("usGreenList", usGreenList);

        return usGreenListAndPaging;
    }

    // 우리가그린 상세보기
    public UsGreenDTO selectUsGreenDetail(int comuNo) {
        return adminUsGreenMapper.selectUsGreenDetail(comuNo);
    }

    // 우리가그린 삭제
    public void deleteUsGreen(UsGreenDTO deleteUsGreen) {
        adminUsGreenMapper.deleteUsGreen(deleteUsGreen);
    }
}
