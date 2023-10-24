package com.dydals.board.Controller;

import com.dydals.board.Dto.RequstMember;
import com.dydals.board.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String inLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String getLogin(RequstMember requstUser){
        memberService.login(requstUser);
        return "index";
    }

    @GetMapping("/register")
    public String inRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String getRegister(RequstMember requstUser){
        log.info(requstUser.getMemberId());
        memberService.createUser(requstUser);
        return "index";

    }

}
