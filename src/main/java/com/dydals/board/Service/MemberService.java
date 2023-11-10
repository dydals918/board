package com.dydals.board.Service;

import com.dydals.board.Dto.RequstMember;
import com.dydals.board.Dto.ResponseMember;
import com.dydals.board.Entity.Member;
import com.dydals.board.Repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryImpl memberRepository;

    public String createUser(RequstMember requstUser) {

        Member member = Member.createMember(requstUser.getMemberId(), requstUser.getMemberPw(), requstUser.getMemberNick());

        Optional<Member> duplMember = memberRepository.findByName(member.getName());
        if (duplMember.isPresent()) {
            return "이미 사용중인 아이디 입니다.";
        } else {
            memberRepository.save(member);
            return "회원가입 성공";
        }

    }

    public ResponseMember login(RequstMember requstUser) {

        Optional<Member> findMember = memberRepository.findByName(requstUser.getMemberId());
        if (findMember.isPresent()) {
            ResponseMember resMem = chageDto(findMember.get());
            if (requstUser.getMemberPw().equals(resMem.getMemberPw())) {
                return resMem;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private ResponseMember chageDto(Member member) {
        ResponseMember resMem = new ResponseMember();
        resMem.setId(member.getId());
        resMem.setMemberId(member.getName());
        resMem.setMemberPw(member.getPassword());
        resMem.setMemberNick(member.getNickname());
        resMem.setGrade(member.getGrade());
        return resMem;
    }

}
