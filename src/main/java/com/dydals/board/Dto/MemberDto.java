package com.dydals.board.Dto;

import com.dydals.board.Entity.Grade;
import com.dydals.board.Entity.Member;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String memberId;
    private String password;
    private String nickname;
    private Grade grade;
    private int memberVisitCnt;

    public static MemberDto toMemberDto(Member member) {
        MemberDto resMem = new MemberDto();
        resMem.setId(member.getId());
        resMem.setMemberId(member.getMemberId());
        resMem.setPassword(member.getPassword());
        resMem.setNickname(member.getNickname());
        resMem.setGrade(member.getGrade());
        resMem.setMemberVisitCnt(member.getMemberVisitCnt());
        return resMem;
    }
}
