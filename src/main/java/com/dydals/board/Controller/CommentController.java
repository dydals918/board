package com.dydals.board.Controller;

import com.dydals.board.Dto.CommentDto;
import com.dydals.board.Service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/spr_board/{boardId}")
    public String comment(@PathVariable Long boardId, HttpServletRequest request, CommentDto commentDto){
        HttpSession session = request.getSession(false);
        if(session != null){
            String sessionId = (String)(session.getAttribute("loginId"));
            commentService.insert(boardId,sessionId,commentDto);

            return "redirect:/board/spr_board/{boardId}";
        }else{
            return "login";
        }
    }

}
