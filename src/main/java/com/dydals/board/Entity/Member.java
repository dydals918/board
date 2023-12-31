package com.dydals.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String password;

    @Column(unique = true)
    private String nickname;

    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(columnDefinition = "int default 0")
    private int memberVisitCnt;

    @OneToMany(mappedBy = "comment_member")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post_member")
    private List<Post> postList = new ArrayList<>();

    public static Member createMember(String memberId, String pw, String nickname){
        Member creMember = new Member();
        creMember.memberId = memberId;
        creMember.password = pw;
        creMember.nickname = nickname;
        creMember.createDate = LocalDateTime.now();
        creMember.grade = Grade.BRONZE;
        creMember.memberVisitCnt = 0;
        return creMember;
    }
}
