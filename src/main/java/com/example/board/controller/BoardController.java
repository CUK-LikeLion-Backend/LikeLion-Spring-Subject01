package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService; // 게시판과 관련된 비즈니스 로직을 처리하는 서비스

    // localhost:8090/board/write 주소로 접속하면 board write 페이지 보여주는 설정
    @GetMapping("/board/write") // "/board/write" 주소로 접속하면 boardwrite 페이지를 보여줌
    public String boardWriteForm() {
        return "boardwrite";
    }

    // boardwrite.html에서의 요청이 넘어왔는지 확인하는 메소드
    @PostMapping("/board/write")
    public String boardWritePro(Board board) {
        boardService.boardwrite(board);
        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String getBoardList(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("list", boardList);
        return "boardlist";
    }


    @GetMapping("/board/view/{id}")
    public String boardView(@PathVariable("id") Integer id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "boardview";
    }


    // D : delete 기능
    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    // "/board/modify/{id}" 주소로 접속하면 해당 게시글을 수정하기 위한 페이지를 보여줌
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "boardmodify";
    }

    // 수정 반영
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {
        Board existingBoard = boardService.getBoard(id);

        if (existingBoard != null) {
            existingBoard.setTitle(board.getTitle());
            existingBoard.setContent(board.getContent());

            boardService.boardUpdate(existingBoard);
        }

        return "redirect:/board/list";
    }
}
