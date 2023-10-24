package com.dydals.board.Controller;

import com.dydals.board.Dto.RequstUser;
import com.dydals.board.Service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class MemberController {

    private MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String inRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String getRegister(@RequestBody RequstUser requstUser){

        return "index";

    }

}
