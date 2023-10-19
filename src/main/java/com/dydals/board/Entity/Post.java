package com.dydals.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member post_member;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    private String title;
    private String detail;
    private Date createDate;

    private Integer views;
    private Integer recommendation;
    private Integer unrecommendation;

}
