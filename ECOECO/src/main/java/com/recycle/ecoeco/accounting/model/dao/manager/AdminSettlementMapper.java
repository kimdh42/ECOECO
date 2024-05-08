//package com.recycle.ecoeco.manager.payment.model.dao;
//
//import com.recycle.ecoeco.manager.payment.model.dto.SettlementDTO;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface SettlementMapper {
//    static List<SettlementDTO> findSettlementList() {
//        return null;
//    }
//}
//


package com.recycle.ecoeco.accounting.model.dao.manager;

import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.SettlementDTO;
import com.recycle.ecoeco.common.paging.SelectCriteria;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminSettlementMapper {
//    List<SettlementDTO> findSettlementList();

    SettlementDTO settlementListDetail(int settlementNo);

    int selectTotalCount(Map<String, String> searchMap);

    List<OrderDTO> findSettlementList(SelectCriteria selectCriteria);

    //정산 진행(정산 상태 변경)
    int updateSettlement(Map<String, Object> parameterMap);
}
