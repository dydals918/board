package com.dydals.board.Service;

import com.dydals.board.Dto.CommentDto;
import com.dydals.board.Entity.Comment;
import com.dydals.board.Entity.Member;
import com.dydals.board.Repository.CommentRepositoryImpl;
import com.dydals.board.Repository.MemberRepositoryImpl;
import com.dydals.board.Repository.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepositoryImpl commentRepository;
    private final PostRepositoryImpl postRepository;
    private final MemberRepositoryImpl memberRepository;

    public void insert(Long boardId, String sessionId, CommentDto commentDto) {
        Member me = memberRepository.findByMemberId(sessionId).orElse(null);
        commentDto.setBoardNumber(postRepository.findById(boardId).orElse(null));
        commentDto.setMemberWriter(me);

        commentRepository.save(Comment.toCommentEntity(commentDto));
    }


    public List<CommentDto> findByPostId(Long postId) {
        List<Comment> commentEntityList = commentRepository.findAllByPost_Id(postId);
        List<CommentDto> commentDTOS = new ArrayList<>();

        for(Comment commentEntity : commentEntityList){
            commentDTOS.add(CommentDto.toCommentDTO(commentEntity));
        }

        return commentDTOS;
    }

    public int findByCommentWriteCnt(String nickname) {

        List<Comment> commentList = commentRepository.findAllByCommentMember_Nickname(nickname);
        return commentList.size();

    }
}
