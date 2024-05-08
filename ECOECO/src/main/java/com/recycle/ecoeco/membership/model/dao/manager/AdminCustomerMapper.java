package com.recycle.ecoeco.membership.model.dao.manager;

import com.recycle.ecoeco.common.paging.SelectCriteria;

import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminCustomerMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<UserInfoDTO> findCustomerList(SelectCriteria selectCriteria);

    UserInfoDTO userListDetail(int userNo);

    void deleteCustomer(int userNo);

    int updateCustomerGrade(Map<String, Object> parameterMap);
}
