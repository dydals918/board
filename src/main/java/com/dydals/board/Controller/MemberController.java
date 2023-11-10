package com.dydals.board.Controller;

import com.dydals.board.Dto.RequstMember;
import com.dydals.board.Dto.ResponseMember;
import com.dydals.board.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String inLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String getLogin(RequstMember requstUser, HttpServletRequest httpServletRequest, BindingResult bindingResult){
        ResponseMember resMem = memberService.login(requstUser);

        if(resMem != null){
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("loginNic", resMem.getMemberNick());
            session.setAttribute("loginGd", resMem.getGrade());
            return "redirect:/";
        } else {
            bindingResult.reject("loginFail", "아이디 또는 패스워드가 틀렸습니다.");
        }

        if (bindingResult.hasErrors()){
            return "login";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null){
            session.invalidate();
            return  "redirect:/";
        }
        return  "redirect:/";
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
