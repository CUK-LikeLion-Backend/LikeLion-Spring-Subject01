package com.example.likelion.controller;

import com.example.likelion.entity.Board;
import com.example.likelion.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/write")
    public String showWriteForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/write";
    }

    @PostMapping("/write")
    public String createBoard(@ModelAttribute Board board) {
        boardService.createBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("list", boards);
        return "board/list";
    }

    @GetMapping("/view/{id}")
    public String getBoardById(@PathVariable int id, Model model) {
        Optional<Board> optionalBoard = boardService.getBoardById(id);
        if (optionalBoard.isPresent()) {
            model.addAttribute("board", optionalBoard.get());
            return "board/view";
        }
        return "redirect:/board/list";
    }

    @GetMapping("/modify/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Optional<Board> optionalBoard = boardService.getBoardById(id);
        if (optionalBoard.isPresent()) {
            model.addAttribute("board", optionalBoard.get());
            return "board/modify";
        }
        return "redirect:/board/list";
    }

    @PostMapping("/update/{id}")
    public String updateBoard(@PathVariable int id, @ModelAttribute Board updatedBoard) {
        boardService.updateBoard(id, updatedBoard);
        return "redirect:/board/view/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }
}
