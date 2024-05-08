package com.recycle.ecoeco.board.usGreen.model.dto;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class UsGreenDTO {

    private int comuNo;
    private String comuCategory;
    private String comuTitle;
    private String comuDetail;
    private LocalDate comuDate;
    private char comuStatus;
    private int userNo;
    private UserInfoDTO writer;
    private UsGreenGoodDTO like;
    private UsGreenCommentDTO comment;
    private UsGreenCommentGoodDTO commentLike;
}
