package com.example.BoardCRUD.board.controller;

import com.example.BoardCRUD.board.entity.Board;
import com.example.BoardCRUD.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {


    private BoardService boardService;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService=boardService;
    }

    //localhost:8090/board/wirte 주소로 접속하면 board write 페이지 보여주는 설정
    @GetMapping("/board/write")
    public String boardwriteForm(){
        return "boardwrite";
    }



    //boardwrite.html 에서의 요청이 넘어왔는지 확인하는 메소드
    @PostMapping("/board/create")
    public String boardWritePro(@ModelAttribute Board board){ //html 에서지정한 이름대로 매개변수가 Board 클래스에 담겨서 들어옴
        logger.info(String.valueOf(board));
        boardService.boardwrite(board);
        return "redirect:/board/list";
    }



    //R : 리스트 기능 구현
    @GetMapping("/board/list")
    public String boardList(Model model){
        List<Board> boardList=boardService.boardList();
        model.addAttribute(boardList);
        return "boardList";
    }


    //R : 상세페이지 기능 구현
    @GetMapping("/board/view") //localhost:8090/board/view?id=1 하면, 1 이 id 값이된다.
    //이렇듯 값을 전달하는  path variable 과query parameter 방식에 대해서도 알아보시길 바랍니다.
    public String boardView(@RequestParam("boardId") int boardId, Model model){
        logger.info(String.valueOf(boardId));
        Optional<Board> boardview = boardService.boardView(boardId);
        Board board=boardview.orElse(null);
        model.addAttribute("board", board);
        return "boardView";
    }



    //D : delete 기능
    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("boardId") int boardId){
        boardService.boardDelete(boardId);
        return "redirect:/board/list";
    }



    //U : 수정기능 - 수정 버튼 동작
    //path variable 을 이용한 get 방식입니다.
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,  Model model)
    {
        Optional<Board> boardview=boardService.boardView(id);
        Board board=boardview.orElse(null);
        model.addAttribute("board", board);
        return "boardModify";
    }


    //수정 반영
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, @ModelAttribute Board board){
        Optional<Board> boardOptional=boardService.boardView(id);
        if(boardOptional.isPresent()){
            board.setBoardId(id);
            boardService.boardwrite(board);
        }
        return "redirect:/board/list";
    }



}