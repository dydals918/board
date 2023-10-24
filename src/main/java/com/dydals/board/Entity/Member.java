package com.dydals.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;
    private String nickname;
    private LocalDate createDate;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "post_member")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "comment_member")
    private List<Comment> commentList = new ArrayList<>();

    public static Member createMember(String name, String pw, String nickname){
        Member creMember = new Member();
        creMember.name = name;
        creMember.password = pw;
        creMember.nickname = nickname;
        creMember.createDate = LocalDate.now();
        creMember.grade = Grade.BRONZE;
        return creMember;
    }
}
