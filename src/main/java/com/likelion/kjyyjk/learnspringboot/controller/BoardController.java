package com.likelion.kjyyjk.learnspringboot.controller;

import com.likelion.kjyyjk.learnspringboot.entity.Board;
import com.likelion.kjyyjk.learnspringboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/write")
    public String boardWritePro(@ModelAttribute Board board){
        boardService.boardwrite(board);
        return "redirect:/board/view?id=" + board.getId();
    }

    @GetMapping("/list")
    public String boardList(Model model){
        List<Board> boardList = boardService.boardList();
        model.addAttribute("list", boardList);
        return "boardlist";
    }

    @GetMapping("/view")
    public String boardView(Model model,@RequestParam Integer id){
        Board board = boardService.boardView(id);
        model.addAttribute(board);
        return "boardview";
    }

    @GetMapping("/delete")
    public String boardDelete(@RequestParam Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String boardModify(@RequestParam Integer id,Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    //수정 반영
    @PostMapping("/update")
    public String boardUpdate(@RequestParam Integer id,Board board){
        boardService.boardUpdate(id, board);
        return "redirect:/board/view?id="+ id;
    }
}