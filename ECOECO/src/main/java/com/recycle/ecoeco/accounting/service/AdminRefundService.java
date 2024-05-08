//package com.recycle.ecoeco.manager.payment.service;
//
//import com.recycle.ecoeco.manager.payment.model.dao.RefundMapper;
//import com.recycle.ecoeco.manager.payment.model.dto.RefundDTO;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RefundService {
//    public List<RefundDTO> findRefundList() {
//        return RefundMapper.findRefundList();
//    }
//}

package com.recycle.ecoeco.accounting.service;

import com.recycle.ecoeco.accounting.model.dao.manager.AdminRefundMapper;
import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.RefundDTO;
import com.recycle.ecoeco.common.paging.Pagenation;
import com.recycle.ecoeco.common.paging.SelectCriteria;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminRefundService {
    private final AdminRefundMapper refundMapper;

    public AdminRefundService(AdminRefundMapper refundMapper) {
        this.refundMapper = refundMapper;
    }

//    public List<RefundDTO> findRefundList() {
//        return refundMapper.findRefundList();
//    }

    public RefundDTO refundListDetail(int refundNo) {
        return refundMapper.refundListDetail(refundNo);
    }

    public Map<String, Object> findRefundList(Map<String, String> searchMap, int page) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = refundMapper.selectTotalCount(searchMap);
//        log.info("boardList totalCount : {}", totalCount);
        System.out.println("totalCount : " + totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
//        log.info("boardList selectCriteria : {}", selectCriteria);
        System.out.println("selectCriteria : " + selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<OrderDTO> boardList = refundMapper.findRefundList(selectCriteria);
//        log.info("boardList : {}", boardList);
        System.out.println("boardList : " + boardList);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;
    }

    //환불 상태 변경
    public void updateRefund(int refundNo, String refundStatus) {

//        System.out.println("updateRefund_service");

        Map<String, Object> parameterMap = new HashMap<>();

        parameterMap.put("refundNo", String.valueOf(refundNo));
        parameterMap.put("refundStatus", refundStatus);

//        System.out.println("refundNo" + refundNo);
//        System.out.println("refundStatus" + refundStatus);

        int result = refundMapper.updateRefund(parameterMap);

//        System.out.println(parameterMap);

        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }
}
