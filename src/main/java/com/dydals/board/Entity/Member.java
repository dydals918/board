package com.dydals.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String nickname;
    private Date createDate;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "post_member")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "comment_member")
    private List<Comment> commentList = new ArrayList<>();
}
