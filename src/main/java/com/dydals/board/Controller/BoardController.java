package com.dydals.board.Controller;

import com.dydals.board.Dto.CommentDto;
import com.dydals.board.Dto.PostDto;
import com.dydals.board.Service.CommentService;
import com.dydals.board.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/spr_board")
    public String boardForm(Model model){
        List<PostDto> postlist = postService.getAllPost();
        model.addAttribute("boardList", postlist);
        return "spr_board";
    }

    //게시판 뷰 페이지 이동
    @GetMapping("/spr_board/{boardId}")
    public String sprBoardView(@PathVariable Long boardId, Model model, HttpServletRequest request) {
        PostDto postDto = postService.findByBoardId(boardId);
        List<CommentDto> commentDTO = commentService.findByPostId(boardId);
        postDto.setBoardCommentCnt((long)(commentDTO.size()));
        postService.updateViewCnt(boardId);

        HttpSession session = request.getSession(false);
        boolean sessionChk = false;
        if(session != null){
            sessionChk = true;
        }

        model.addAttribute("commentList",commentDTO);
        model.addAttribute("board",postDto);
        model.addAttribute("sessionChk",sessionChk);
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

    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable Long id, HttpServletRequest request){
        PostDto boardDTO = postService.findByBoardId(id);
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("loginNic").equals(boardDTO.getPost_member())){
                postService.deleteByBoardDTO(boardDTO);
                return "redirect:/board/spr_board";
            }else {
                return "redirect:/board/write";
            }
        }else{
            return "redirect:/board/login";
        }
    }

    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable Long id, PostDto postDto){
        postService.update(id, postDto);
        return "redirect:/spr_board";
    }

}
