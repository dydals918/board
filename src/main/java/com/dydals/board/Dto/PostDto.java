package com.dydals.board.Dto;

import com.dydals.board.Entity.Post;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {

    private Long id;
    private boolean boardCategory;
    private String post_member;
    private String title;
    private String detail;
    private Date createDate;
    private Long views;
    private Long recommendation;
    private Long unrecommendation;
    private Long boardCommentCnt;

    public static PostDto toRequstPost(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setBoardCategory(post.isBoardCategory());
        postDto.setPost_member(post.getPost_member());
        postDto.setCreateDate(post.getCreateDate());
        postDto.setTitle(post.getTitle());
        postDto.setDetail(post.getDetail());
        postDto.setViews(post.getViews());
        postDto.setRecommendation(post.getRecommendation());
        postDto.setUnrecommendation(post.getUnrecommendation());
        postDto.setBoardCommentCnt(post.getBoardCommentCnt());

        return postDto;
    }
}
