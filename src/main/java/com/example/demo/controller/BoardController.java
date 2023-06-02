package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", boardService.index());
        return "index";
    }

    @GetMapping("board/list")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }

    @GetMapping("board/write")
    public String boardWrite() {
        return "boardwrite";
    }

    @PostMapping("board/write")
    public String boardWrite(Board board, @RequestParam("file") MultipartFile file) throws IOException {
        boardService.boardWrite(board, file);
        return "redirect:/board/list";
    }

    @GetMapping("board/view")
    public String boardView(Model model, @RequestParam("id") Integer id) {
        Optional<Board> ret = boardService.boardView(id);
        if (ret.isEmpty()) {
            return "redirect:/board/list";
        }
        model.addAttribute("board", ret.get());
        return "boardview";
    }

    @GetMapping("board/modify")
    public String boardModify(Model model, @RequestParam("id") Integer id) {
        Optional<Board> ret = boardService.boardView(id);
        if (ret.isEmpty()) {
            return "redirect:/board/view?id=" + id;
        }
        model.addAttribute("board", ret.get());
        return "boardmodify";
    }

    @PostMapping("board/modify")
    public String boardModify(@RequestParam("id") Integer id, Board board, @RequestParam("file") MultipartFile file) throws IOException {
        boardService.boardModify(id, board, file);
        return "redirect:/board/view?id=" + id;
    }

    @GetMapping("board/delete")
    public String boardDelete(@RequestParam("id") Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("board/download")
    public void boardDownload(@RequestParam("filepath") String filepath,
                              @RequestParam("id") Integer id,
                              HttpServletResponse response) throws Exception {
        try {
            boardService.boardDownload(filepath, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            response.setHeader ("Location", "localhost:8090/board/view?id=" + id);
            response.setStatus(301);
            response.getOutputStream().close();
        }
    }
}
