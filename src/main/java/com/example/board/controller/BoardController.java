package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.repository.JpaBoardRepository;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // board 등록하는 view 불러오는 컨트롤러
    @GetMapping("/board/write")
    public String writeForm(){return "boardwrite";}

    // board 등록하는 컨트롤러
    @PostMapping("/board/write")
    public String createBoard(BoardForm form){
        boardService.save(form);

        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String showList(Model model){
        List<Board> boards = boardService.showList();
        model.addAttribute("boards", boards);
        return "boardlist";
    }

    @GetMapping("/board/modify")
    public String modifyForm(@RequestParam(value="id") Long id, Model model){
        model.addAttribute("id", id);
        return "boardmodify";
    }

    @PostMapping("/board/modify")
    public String modifyBoard(@RequestParam(value="id") Long id, BoardForm form){
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        boardService.update(id, board);
        return "redirect:/board/list";
    }

    @GetMapping("/board/view")
    public String viewForm(@RequestParam(value = "id") Long id, Model model){
        // Optional 클래스는 객체 존재 여부를 나타내는 클래스
        Optional<Board> b = boardService.findById(id);
        if (b.isPresent()) {
            Board board = b.get();
            model.addAttribute("board", board);
        }
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String deleteForm(@RequestParam(value = "id") Long id){
        boardService.delete(id);
        return "redirect:/board/list";
    }
}
