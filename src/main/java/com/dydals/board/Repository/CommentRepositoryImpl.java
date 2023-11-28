package com.dydals.board.Repository;

import com.dydals.board.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepositoryImpl extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost_Id(Long id);

    List<Comment> findAllByCommentMember_Nickname(String nickname);
}
