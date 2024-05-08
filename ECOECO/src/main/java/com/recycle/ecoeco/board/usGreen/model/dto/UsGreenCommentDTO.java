package com.recycle.ecoeco.board.usGreen.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class UsGreenCommentDTO {

    private int commentNo;
    private String commentDetail;
    private LocalDate commentDate;
    private char commentStatus;
    private int comuNo;
    private int userNo;
}
