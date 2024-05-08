package com.recycle.ecoeco.makerProject.model.dto;

public class CategoryDTO {
    private int categoryCode;
    private String categoryName;
    private ProjectDTO projectDTO;


    public CategoryDTO(){}

    public CategoryDTO(int categoryCode, String categoryName, ProjectDTO projectDTO) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.projectDTO = projectDTO;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", projectDTO=" + projectDTO +
                '}';
    }
}
