package com.recycle.ecoeco.board.usGreen.model.dao.manager;

import com.recycle.ecoeco.board.usGreen.model.dto.UsGreenDTO;
import com.recycle.ecoeco.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminUsGreenMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<UsGreenDTO> selectUsGreenList(SelectCriteria selectCriteria);      // 우리가그린 게시글 리스트 조회

    UsGreenDTO selectUsGreenDetail(int comuNo);                             // 우리가그린 상세보기

    void deleteUsGreen(UsGreenDTO deleteUsGreen);                           // 우리가그린 삭제
}
