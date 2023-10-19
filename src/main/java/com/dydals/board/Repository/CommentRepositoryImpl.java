package com.dydals.board.Repository;

import com.dydals.board.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryImpl extends JpaRepository<Comment, Long> {

}
