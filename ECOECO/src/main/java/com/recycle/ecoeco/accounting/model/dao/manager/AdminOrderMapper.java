
package com.recycle.ecoeco.accounting.model.dao.manager;

import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.common.paging.SelectCriteria;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminOrderMapper {
//    List<OrderDTO> findorderList();

    OrderDTO orderListDetail(int orderNo);

    List<OrderDTO> findorderList(SelectCriteria selectCriteria);

    int selectTotalCount(Map<String, String> searchMap);
}

