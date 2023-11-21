package com.dydals.board.Dto;

import com.dydals.board.Entity.Comment;
import com.dydals.board.Entity.Grade;
import com.dydals.board.Entity.Member;
import com.dydals.board.Entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentDto {

    private Long commentId;
    private String commentContent;
    private Member memberWriter;
    private Date commentDate;
    private Post boardNumber;
    private Grade commentMemberGd;

    public static CommentDto toCommentDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getId());
        commentDto.setCommentContent(comment.getCommentDetail());
        commentDto.setCommentDate(comment.getCommentDate());
        commentDto.setMemberWriter(comment.getComment_member());
        commentDto.setBoardNumber(comment.getPost());
        commentDto.setCommentMemberGd(comment.getComment_member().getGrade());
        return commentDto;
    }

}
