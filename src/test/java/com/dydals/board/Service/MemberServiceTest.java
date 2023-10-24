package com.dydals.board.Service;

import com.dydals.board.Dto.RequstUser;
import com.dydals.board.Entity.Member;
import com.dydals.board.Repository.MemberRepositoryImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepositoryImpl memberRepository;

    @Test
    public void 회원가입(){
        RequstUser requstUser = new RequstUser();
        requstUser.setMemberId("회원1");
        requstUser.setMemberPw("qwe");
        requstUser.setMemberNick("회원2");
        System.out.println(requstUser.toString());

        Member member = Member.createMember(requstUser.getMemberId(), requstUser.getMemberPw(), requstUser.getMemberNick());
        System.out.println(member.getName() + member.getNickname());

        memberRepository.save(member);

    }

}