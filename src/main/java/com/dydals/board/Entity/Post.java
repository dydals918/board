package com.dydals.board.Entity;

import com.dydals.board.Dto.PostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private boolean boardCategory;

    private String post_member;

    private String title;
    private String detail;

    @CreationTimestamp
    private Date createDate;

    private Long views;
    private Long recommendation;
    private Long unrecommendation;
    private Long boardCommentCnt;

    public static Post toPost(PostDto postDto){
        Post post = new Post();
        post.id = postDto.getId();
        post.boardCategory = postDto.isBoardCategory();
        post.post_member = postDto.getPost_member();
        post.createDate = postDto.getCreateDate();
        post.title = postDto.getTitle();
        post.detail = postDto.getDetail();
        post.views = postDto.getViews();
        post.recommendation = postDto.getRecommendation();
        post.unrecommendation = postDto.getUnrecommendation();
        post.boardCommentCnt = postDto.getBoardCommentCnt();

        return post;
    }

}
