package com.dydals.board.Repository;

import com.dydals.board.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryImpl extends JpaRepository<Post, Long> {
}
