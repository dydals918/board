package com.dydals.board.Service;

import com.dydals.board.Dto.RequstUser;
import com.dydals.board.Entity.Member;
import com.dydals.board.Repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryImpl memberRepository;

    public void createUser(RequstUser requstUser){

        Member member = Member.createMember(requstUser.getMemberId(), requstUser.getMemberPw(), requstUser.getMemberNick());
        memberRepository.save(member);

    }

}
