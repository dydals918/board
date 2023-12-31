package com.dydals.board.Repository;

import com.dydals.board.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepositoryImpl extends JpaRepository<Post, Long> {

    List<Post> findAllByPostMember_Nickname(String name);
}
