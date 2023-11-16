package com.dydals.board.Dto;

import com.dydals.board.Entity.Grade;
import lombok.Data;

@Data
public class ResponseMember {

    private Long id;
    private String memberId;
    private String password;
    private String nickname;
    private Grade grade;

}
