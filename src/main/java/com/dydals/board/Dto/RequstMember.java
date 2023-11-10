package com.dydals.board.Dto;

import com.dydals.board.Entity.Grade;
import lombok.Data;

@Data
public class RequstMember {

    private Long id;
    private String memberId;
    private String memberPw;
    private String memberNick;
    private Grade grade;

}
