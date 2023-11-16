package com.dydals.board.Service;

import com.dydals.board.Entity.Comment;
import com.dydals.board.Repository.CommentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepositoryImpl commentRepository;

//    public List<Comment> findByPostId(Long id){
//
//        Optional<List<Comment>> commentList = commentRepository.findByPostid(id);
//        List<Comment> getCommentList = commentList.get();
//        return getCommentList;
//
//    }

}
