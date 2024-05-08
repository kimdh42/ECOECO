package com.recycle.ecoeco.membership.service.manager;

import com.recycle.ecoeco.common.paging.Pagenation;
import com.recycle.ecoeco.common.paging.SelectCriteria;

import com.recycle.ecoeco.membership.model.dao.manager.AdminCustomerMapper;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminCustomerService {
    private final AdminCustomerMapper adminCustomerMapper;

    public AdminCustomerService(AdminCustomerMapper adminCustomerMapper) {
        this.adminCustomerMapper = adminCustomerMapper;
    }

    public UserInfoDTO userListDetail(int userNo) {
        return adminCustomerMapper.userListDetail(userNo);
    }

    public Map<String, Object> findCustomerList(Map<String, String> searchMap, int page) {
        int totalCount = adminCustomerMapper.selectTotalCount(searchMap);

        int limit = 10;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);

        List<UserInfoDTO> boardList = adminCustomerMapper.findCustomerList(selectCriteria);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;
    }

    public void deleteCustomer(int userNo) {
        adminCustomerMapper.deleteCustomer(userNo);
    }

    public void updateCustomerGrade(int userNo, int userGrade) {

        System.out.println("updateCustomerGrade_service");

        Map<String, Object> parameterMap = new HashMap<>();

        parameterMap.put("userNo", String.valueOf(userNo));
        parameterMap.put("userGrade", userGrade);

        System.out.println("userNo" + userNo);
        System.out.println("userGrade" + userGrade);

        int result = adminCustomerMapper.updateCustomerGrade(parameterMap);

        System.out.println(parameterMap);

        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }

//        // 사용자의 등급을 업데이트하기 전에 먼저 해당 사용자가 존재하는지 확인합니다.
//        UserInfoDTO userInfo = adminCustomerMapper.userListDetail(userNo);
//
//        // 사용자가 존재하지 않으면 등급을 업데이트할 수 없으므로 false를 반환합니다.
//        if (userInfo == null) {
//            return false;
//        }
//
//        // 사용자가 존재하는 경우, 선택한 등급으로 사용자의 등급을 업데이트합니다.
//        userInfo.setUserGrade(userGrade);
//
//        // 사용자의 등급을 업데이트하는 SQL을 실행합니다.
//        int rowsAffected = adminCustomerMapper.updateCustomerGrade(userInfo);
//
//        // 등급이 성공적으로 업데이트되었는지 여부를 반환합니다.
//        // 여기서는 업데이트된 행의 수를 기준으로 판단합니다.
//        return rowsAffected > 0;

    }

}
