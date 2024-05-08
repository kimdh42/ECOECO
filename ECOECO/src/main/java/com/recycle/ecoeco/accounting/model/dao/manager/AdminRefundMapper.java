//package com.recycle.ecoeco.manager.payment.model.dao;
//
//import com.recycle.ecoeco.manager.payment.model.dto.RefundDTO;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface RefundMapper {
//    static List<RefundDTO> findRefundList() {
//        return null;
//    }
//}


package com.recycle.ecoeco.accounting.model.dao.manager;

import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.RefundDTO;
import com.recycle.ecoeco.common.paging.SelectCriteria;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminRefundMapper {
//    List<RefundDTO> findRefundList();

    RefundDTO refundListDetail(int refundNo);

    int selectTotalCount(Map<String, String> searchMap);

    List<OrderDTO> findRefundList(SelectCriteria selectCriteria);

    // 환불 상태 변경
    int updateRefund(Map<String, Object> parameterMap);
}
