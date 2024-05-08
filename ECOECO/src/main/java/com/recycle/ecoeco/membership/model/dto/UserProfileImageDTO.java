package com.recycle.ecoeco.membership.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

@Getter @Setter @ToString
public class UserProfileImageDTO {

    private int profileImageNo;     // 프로필 이미지 번호
    private String profileImageFileName;        // 프로필 이미지 원본명
    private String profileImageSaveName;        // 프로필 이미지 저장명
    private String profileImagePath;        // 프로필 이미지 경로명
    private int userNo;     // 회원번호

    public UserProfileImageDTO() {}

    public UserProfileImageDTO(int profileImageNo, String profileImageFileName, String profileImageSaveName, String profileImagePath, int userNo) {
        this.profileImageNo = profileImageNo;
        this.profileImageFileName = profileImageFileName;
        this.profileImageSaveName = profileImageSaveName;
        this.profileImagePath = profileImagePath;
        this.userNo = userNo;
    }
}
