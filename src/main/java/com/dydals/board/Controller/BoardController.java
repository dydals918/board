package com.dydals.board.Controller;

import com.dydals.board.Dto.PostDto;
import com.dydals.board.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final PostService postService;

    @GetMapping("/spr_board")
    public String boardForm(Model model){
        List<PostDto> postlist = postService.getAllPost();
        model.addAttribute("boardList", postlist);
        return "spr_board";
    }

    //게시판 뷰 페이지 이동
    @GetMapping("/spr_board/{boardId}")
    public String sprBoardView(@PathVariable Long boardId, Model model) {
        PostDto boardDTO = postService.findByBoardId(boardId);
        model.addAttribute("board",boardDTO);
        return "spr_board_view";
    }

    @GetMapping("/write")
    public String boardWrite(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            return "write";
        }
        return "login";
    }

    @PostMapping("/write")
    public String boardWriteForm(PostDto postDto, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String mbNic = (String)(session.getAttribute("loginNic"));
        postService.write(postDto, mbNic);
        return "redirect:/board/spr_board";
    }

    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable Long id, PostDto postDto){
        postService.update(id, postDto);
        return "redirect:/spr_board";
    }

}
