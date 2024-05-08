//package com.recycle.ecoeco.manager.payment.service;
//
//import com.recycle.ecoeco.manager.payment.model.dao.SettlementMapper;
//import com.recycle.ecoeco.manager.payment.model.dto.SettlementDTO;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SettlementService {
//    private final SettlementMapper settlementMapper;
//
//    public SettlementService(SettlementMapper settlementMapper){
//        this.settlementMapper = settlementMapper;
//    }
//
//    public List<SettlementDTO> findSettlementList(){
//        return SettlementMapper.findSettlementList();
//    }
//}

package com.recycle.ecoeco.accounting.service;

import com.recycle.ecoeco.accounting.model.dao.manager.AdminSettlementMapper;
import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.SettlementDTO;
import com.recycle.ecoeco.common.paging.Pagenation;
import com.recycle.ecoeco.common.paging.SelectCriteria;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminSettlementService {
    private final AdminSettlementMapper settlementMapper;

    public AdminSettlementService(AdminSettlementMapper settlementMapper){
        this.settlementMapper = settlementMapper;
    }

//    public List<SettlementDTO> findSettlementList(){
//        return settlementMapper.findSettlementList();
//    }

    public SettlementDTO settlementListDetail(int settlementNo) {
        SettlementDTO result = settlementMapper.settlementListDetail(settlementNo);
        System.out.println("result : " + result);
        return result;
    }

    public Map<String, Object> findSettlementList(Map<String, String> searchMap, int page) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = settlementMapper.selectTotalCount(searchMap);
//        log.info("boardList totalCount : {}", totalCount);
        System.out.println("totalCount : " + totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
//        log.info("boardList selectCriteria : {}", selectCriteria);
        System.out.println("selectCriteria : " + selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<OrderDTO> boardList = settlementMapper.findSettlementList(selectCriteria);
//        log.info("boardList : {}", boardList);
        System.out.println("boardList : " + boardList);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;
    }

    //정산 진행(정산 상태 변경)
    public void updateSettlement(int settlementNo, String settlementStatus) {
        System.out.println("updateSettlement_service");

        Map<String, Object> parameterMap = new HashMap<>();

        parameterMap.put("settlementNo", String.valueOf(settlementNo));
        parameterMap.put("settlementStatus", settlementStatus);

        System.out.println("settlementNo" + settlementNo);
        System.out.println("settlementStatus" + settlementStatus);

        int result = settlementMapper.updateSettlement(parameterMap);

        System.out.println(parameterMap);

        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }
}
