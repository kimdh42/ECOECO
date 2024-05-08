package com.recycle.ecoeco.accounting.model.dao.manager;

import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import com.recycle.ecoeco.common.paging.SelectCriteria;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminPaymentMapper {
//    List<PaymentDTO> findPaymentList();

    PaymentDTO paymentListDetail(int paymentNo);

    int selectTotalCount(Map<String, String> searchMap);

    List<OrderDTO> findPaymentList(SelectCriteria selectCriteria);
}
