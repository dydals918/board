package com.dydals.board.Service;

import com.dydals.board.Dto.RequstMember;
import com.dydals.board.Entity.Member;
import com.dydals.board.Repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryImpl memberRepository;

    public String createUser(RequstMember requstUser){

        Member member = Member.createMember(requstUser.getMemberId(), requstUser.getMemberPw(), requstUser.getMemberNick());

        Member duplMember = memberRepository.findByName(member.getName());
        if (duplMember != null){
            return "이미 사용중인 아이디 입니다.";
        } else {
            memberRepository.save(member);
            return "회원가입 성공";
        }

    }

    public String login(RequstMember requstUser) {

        Member findMember = memberRepository.findByName(requstUser.getMemberId());
        if (requstUser.getMemberPw().equals(findMember.getPassword())){
            String msg = requstUser.getMemberId();
            return msg;
        } else {
            return "없는 아이디 입니다.";
        }

    }

}
