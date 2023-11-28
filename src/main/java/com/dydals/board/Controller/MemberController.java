package com.dydals.board.Controller;

import com.dydals.board.Dto.MemberDto;
import com.dydals.board.Dto.PostDto;
import com.dydals.board.Service.CommentService;
import com.dydals.board.Service.MemberService;
import com.dydals.board.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/login")
    public String inLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String getLogin(MemberDto requstUser, HttpServletRequest httpServletRequest, BindingResult bindingResult){
        MemberDto resMem = memberService.login(requstUser);

        if(resMem != null){
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("loginNic", resMem.getNickname());
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
    public String getRegister(MemberDto requstUser){
        log.info(requstUser.getMemberId());
        memberService.createUser(requstUser);
        return "index";

    }

    /*
        하던중
     */
    @GetMapping("/mypage")
    public String mypage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            String memberId = (String)session.getAttribute("loginId");
            String memberNic = (String)session.getAttribute("loginNic");
            MemberDto memberDTO = memberService.findByMemberid(memberId);
            List<PostDto> postDtoList = postService.findByPostMember(memberNic);

            int postWriteCnt = postService.findByPostCount(memberNic);
            int commentWriteCnt = commentService.findByCommentWriteCnt(memberNic);

            //방문횟수 가져오기
            int memberVisitCnt = memberService.findMemberVisitCnt(memberId);

            model.addAttribute("visitCnt",memberVisitCnt);
            model.addAttribute("commentWriteCnt",commentWriteCnt);
            model.addAttribute("writeCnt",postWriteCnt);
            model.addAttribute("writeList",postDtoList);
            model.addAttribute("member",memberDTO);
        }
        return "mypage";
    }

}
