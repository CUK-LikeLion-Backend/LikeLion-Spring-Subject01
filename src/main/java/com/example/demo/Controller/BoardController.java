package com.example.demo.Controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 작성 폼 페이지 보여주기
    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    // 게시글 작성 처리
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {
        // 게시글 저장 로직
        boardService.save(board);
        return "redirect:/board/list";
    }

    // 게시글 리스트 조회
    @GetMapping("/board/list")
    public String boardList(Model model) {
        // 모든 게시글 조회 로직
        model.addAttribute("boards", boardService.findAll());
        return "boardlist";
    }

    // 특정 게시글 상세 조회
    @GetMapping("/board/view/{id}")
    public String boardView(Model model, @PathVariable("id") Long id) {
        // 특정 ID를 가진 게시글 조회 로직
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "boardview";
    }

    // 게시글 삭제
    @GetMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable("id") Long id) {
        // 게시글 삭제 로직
        boardService.delete(id);
        return "redirect:/board/list";
    }

    // 게시글 수정 폼 페이지 보여주기
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model) {
        // 특정 ID를 가진 게시글 조회 로직
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "boardmodify";
    }

    // 게시글 수정 처리
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Board board) {
        // 게시글 수정 로직
        board.setId(id);
        boardService.update(board);
        return "redirect:/board/list";
    }
}
