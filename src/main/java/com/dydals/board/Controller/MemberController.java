package com.dydals.board.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/board/login")
    public String login(){
        return "login";
    }

}
