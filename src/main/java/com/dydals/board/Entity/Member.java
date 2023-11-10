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
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;
    private String nickname;

    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private Grade grade;


    public static Member createMember(String name, String pw, String nickname){
        Member creMember = new Member();
        creMember.name = name;
        creMember.password = pw;
        creMember.nickname = nickname;
        creMember.createDate = LocalDateTime.now();
        creMember.grade = Grade.BRONZE;
        return creMember;
    }
}
