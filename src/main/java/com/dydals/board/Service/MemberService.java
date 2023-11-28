package com.dydals.board.Service;

import com.dydals.board.Dto.MemberDto;
import com.dydals.board.Entity.Member;
import com.dydals.board.Repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryImpl memberRepository;

    public String createUser(MemberDto requstUser) {

        Member member = Member.createMember(requstUser.getMemberId(), requstUser.getPassword(), requstUser.getNickname());

        Optional<Member> duplMember = memberRepository.findByMemberId(member.getMemberId());
        if (duplMember.isPresent()) {
            return "이미 사용중인 아이디 입니다.";
        } else {
            memberRepository.save(member);
            return "회원가입 성공";
        }

    }

    public MemberDto login(MemberDto requstUser) {

        Optional<Member> findMember = memberRepository.findByMemberId(requstUser.getMemberId());
        if (findMember.isPresent()) {
            MemberDto resMem = MemberDto.toMemberDto(findMember.get());
            if (requstUser.getPassword().equals(resMem.getPassword())) {
                return resMem;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public MemberDto findByMemberid(String memberId) {
        Optional<Member> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if(optionalMemberEntity.isPresent()){
            Member member = optionalMemberEntity.get();
            return MemberDto.toMemberDto(member);
        }
        return null;
    }

    public int findMemberVisitCnt(String memberId) {
        Optional<Member> findmember = memberRepository.findByMemberId(memberId);
        if (findmember.isPresent()){
            Member member = findmember.get();
            return member.getMemberVisitCnt();
        }
        return 0;
    }
}
