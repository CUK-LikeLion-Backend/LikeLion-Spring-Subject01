package com.LIKELION.learnspring.controller;

import com.LIKELION.learnspring.entity.Board;
import com.LIKELION.learnspring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    //localhost:8090/board/wirte 주소로 접속하면 board write 페이지 보여주는 설정
    @GetMapping("/board/write")
    public String boardwriteForm(){
        return "boardwrite";
    }

    //boardwrite.html 에서의 요청이 넘어왔는지 확인하는 메소드
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) { //html 에서지정한 이름대로 매개변수가 Board 클래스에 담겨서 들어옴
        boardService.write(board);
        return "";
    }

    //R : 리스트 기능 구현
    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }

    //R : 상세페이지 기능 구현
    @GetMapping("/board/view") //localhost:8090/board/view?id=1 하면, 1 이 id 값이된다.
    //이렇듯 값을 전달하는  path variable 과query parameter 방식에 대해서도 알아보시길 바랍니다.
    public String boardView(Model model,Integer id){
        model.addAttribute("board",boardService.boardView(id));
        return "boardview";
    }

    //D : delete 기능
    @GetMapping("/board/delete")
    public String boardView(Integer id){

        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    //U : 수정기능 - 수정 버튼 동작
    //path variable 을 이용한 get 방식입니다.
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,Model model)
    {
        model.addAttribute("board",boardService.boardView(id));
        return "boardmodify";
    }

    //수정 반영
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,Board board){
        /**
         * board로 받아온 값을 get,set 을 이용해 새로 수정한 뒤에
         * 리스트 페이지로 리 다이렉트 한 코드를 작성해주면 됩니다.
         */
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);
        return "redirect:/board/list";
    }
}