package com.dydals.board.Repository;

import com.dydals.board.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryImpl extends JpaRepository<Member, Long> {
}
