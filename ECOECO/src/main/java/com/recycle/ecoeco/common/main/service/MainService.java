package com.recycle.ecoeco.common.main.service;

import com.recycle.ecoeco.makerProject.model.dao.user.MainMapper;
import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    // 서비스 클래스에서 필요시 사용할 DAO(MainMapper) 초기화
    private final MainMapper mainMapper;

    // @Autowired : 의존성 주입
    // MainMapper(dao) 타입의 객체를 MainService 가 주입받음
    // MainService 클래스가 MainMapper(dao)클래스에 의존함
    @Autowired
    public MainService(MainMapper mainMapper) {
        this.mainMapper = mainMapper;
    }
    // 메인 페이지 헤더 부분 section
    // 인기 프로젝트 매퍼 메소드 호출
    public List<ProjectDTO> popularList() {
        System.out.println("popularList_service");
        List<ProjectDTO> popularList = mainMapper.popularList();
        System.out.println("popularList : " + popularList);
        return popularList;
    }

    // 신규 프로젝트 매퍼 메소드 호출
    public List<ProjectDTO> newList() {
        System.out.println("newList_service");
        List<ProjectDTO> newList = mainMapper.newList();
        System.out.println("newList : " + newList);
        return newList;
    }

    // 오픈예정 프로젝트 매퍼 메소드 호출
    public List<ProjectDTO> openList() {
        System.out.println("openList_service");
        List<ProjectDTO> openList = mainMapper.openList();
        System.out.println("openList" + openList);
        return openList;
    }

    // 마감임박 프로젝트 매퍼 메소드 호출
    public List<ProjectDTO> closeList() {
        System.out.println("closeList_service");
        List<ProjectDTO> closeList = mainMapper.closeList();
        System.out.println("closeList" + closeList);
        return closeList;
    }

    // 메인 페이지 헤더 부분 category
    // 리빙
    public List<ProjectDTO> livingList() {
        System.out.println("livingList_service");
        List<ProjectDTO> livingList = mainMapper.livingList();
        System.out.println("livingList : " + livingList);
        return livingList;
    }
    // 가전
    public List<ProjectDTO> applianceList() {
        System.out.println("applianceList_service");
        List<ProjectDTO> applianceList = mainMapper.applianceList();
        System.out.println("applianceList : " + applianceList);
        return applianceList;
    }
    // 뷰티
    public List<ProjectDTO> beautyList() {
        System.out.println("beautyList_service");
        List<ProjectDTO> beautyList = mainMapper.beautyList();
        System.out.println("openList" + beautyList);
        return beautyList;
    }
    // 의류
    public List<ProjectDTO> clothingList() {
        System.out.println("clothingList_service");
        List<ProjectDTO> clothingList = mainMapper.clothingList();
        System.out.println("clothingList" + clothingList);
        return clothingList;
    }
    // 잡화
    public List<ProjectDTO> generalItemsList() {
        System.out.println("generalItemsList_service");
        List<ProjectDTO> generalItemsList = mainMapper.generalItemsList();
        System.out.println("generalItemsList" + generalItemsList);
        return generalItemsList;
    }
    // 굿즈
    public List<ProjectDTO> goodsList() {
        System.out.println("goodsList_service");
        List<ProjectDTO> goodsList = mainMapper.goodsList();
        System.out.println("goodsList" + goodsList);
        return goodsList;
    }
    // 영화/공연
    public List<ProjectDTO> entertainmentList() {
        System.out.println("entertainmentList_service");
        List<ProjectDTO> entertainmentList = mainMapper.entertainmentList();
        System.out.println("entertainmentList" + entertainmentList);
        return entertainmentList;
    }
    // 기타
    public List<ProjectDTO> otherList() {
        System.out.println("otherList_service");
        List<ProjectDTO> otherList = mainMapper.otherList();
        System.out.println("otherList" + otherList);
        return otherList;
    }

    // 메인페이지에서 바디부분 프로젝트
    public List<ProjectDTO> mainPopularList() {
        System.out.println("mainPopularList_service");
        List<ProjectDTO> mainPopularList = mainMapper.mainPopularList();
        System.out.println("mainPopularList : " + mainPopularList);
        return mainPopularList;
    }

    public List<ProjectDTO> mainNewList() {
        System.out.println("mainNewList_service");
        List<ProjectDTO> mainNewList = mainMapper.mainNewList();
        System.out.println("mainNewList : " + mainNewList);
        return mainNewList;
    }

    public List<ProjectDTO> mainOpenList() {
        System.out.println("mainOpenList_service");
        List<ProjectDTO> mainOpenList = mainMapper.mainOpenList();
        System.out.println("mainOpenList" + mainOpenList);
        return mainOpenList;
    }

    public List<ProjectDTO> mainCloseList() {
        System.out.println("mainCloseList_service");
        List<ProjectDTO> mainCloseList = mainMapper.mainCloseList();
        System.out.println("mainCloseList" + mainCloseList);
        return mainCloseList;
    }
}
