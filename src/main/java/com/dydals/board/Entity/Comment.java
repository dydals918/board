package com.dydals.board.Entity;

import com.dydals.board.Dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "nickname")
    private Member comment_member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String commentDetail;

    @CreationTimestamp
    private Date commentDate;

    public static Comment toCommentEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.id = commentDto.getCommentId();
        comment.commentDetail = commentDto.getCommentContent();
        comment.commentDate = commentDto.getCommentDate();
        comment.post = commentDto.getBoardNumber();
        comment.comment_member = commentDto.getMemberWriter();

        return comment;
    }

}
