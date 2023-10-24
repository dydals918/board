package com.dydals.board.Service;

import com.dydals.board.Dto.RequstMember;
import com.dydals.board.Entity.Member;
import com.dydals.board.Repository.MemberRepositoryImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepositoryImpl memberRepository;

    @Test
    public void 회원가입(){
        RequstMember requstUser = new RequstMember();
        requstUser.setMemberId("회원1");
        requstUser.setMemberPw("qwe");
        requstUser.setMemberNick("회원2");
        System.out.println(requstUser.toString());

        Member member = Member.createMember(requstUser.getMemberId(), requstUser.getMemberPw(), requstUser.getMemberNick());
        System.out.println(member.getName() + member.getNickname());

        memberRepository.save(member);

    }

    @Test
    public void 로그인(){

        Member member = Member.createMember("회원1", "ㅁㄴㅇ", "회원1");
        memberRepository.save(member);

        Member findMember = memberRepository.findByName("회원1");
        System.out.println(findMember.toString());

        if (member.getPassword().equals(findMember.getPassword())){
            String msg = member.getName();
        } else {
            System.out.println("00");
        }

    }

}