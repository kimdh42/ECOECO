package com.recycle.ecoeco.makerProject.model.dao.user;

import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    // 인기 프로젝트 매퍼.xml 매핑
    List<ProjectDTO> popularList();

    // 신규 프로젝트 매퍼.xml 매핑
    List<ProjectDTO> newList();

    // 오픈예정 프로젝트 매퍼.xml 매핑
    List<ProjectDTO> openList();

    // 마감임박 프로젝트 매퍼.xml 매핑
    List<ProjectDTO> closeList();

    List<ProjectDTO> mainPopularList();

    List<ProjectDTO> mainNewList();

    List<ProjectDTO> mainOpenList();

    List<ProjectDTO> mainCloseList();

    List<ProjectDTO> livingList();

    List<ProjectDTO> applianceList();

    List<ProjectDTO> beautyList();

    List<ProjectDTO> clothingList();

    List<ProjectDTO> generalItemsList();

    List<ProjectDTO> goodsList();

    List<ProjectDTO> entertainmentList();

    List<ProjectDTO> otherList();
}
