package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    /**
     * 역슬래시 경로로 들어왔을때 helloworld 를 리턴합니다.
     * @GetMapping("/") GetMapping : 어떤 url 로 접근할 것인지 지정해주는 어노테이션
     *     @ResponseBody
     *     public String main(){
     *         return "Hello World";
     *     }
     *
     * */

    private final BoardService boardService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    //localhost:8090/board/wirte 주소로 접속하면 board write 페이지 보여주는 설정
    @GetMapping("/board/write")
    public String boardwriteForm(){
        return "boardwrite";
    }

    //boardwrite.html 에서의 요청이 넘어왔는지 확인하는 메소드
    @PostMapping("/board/write")
    public String boardWritePro(Board board){ //html 에서지정한 이름대로 매개변수가 Board 클래스에 담겨서 들어옴
        boardService.write(board);
        return "redirect:/board/list";
    }


    //R : 리스트 기능 구현
    @GetMapping("/board/list")
    public String boardList(Model model){
        List<Board> boardList=boardService.getList();
        model.addAttribute("list",boardList);
        return "boardlist";
    }

    //R : 상세페이지 기능 구현
    @GetMapping("/board/view/{boardId}") //localhost:8090/board/view?id=1 하면, 1 이 id 값이된다.
    //이렇듯 값을 전달하는  path variable 과query parameter 방식에 대해서도 알아보시길 바랍니다.
    public String boardView(Model model,@PathVariable Long boardId){
        Board board = boardService.get(boardId);
        model.addAttribute("board", board);
        return "boardview";
    }

    //D : delete 기능
    @GetMapping("/board/delete")
    public String boardDelete(Long id){
        boardService.delete(id);
        return "boardlist";
    }

    //U : 수정기능 - 수정 버튼 동작
    //path variable 을 이용한 get 방식입니다.
    @GetMapping("/board/modify/{boardId}")
    public String boardModify(@PathVariable Long boardId,Model model)
    {
        Board board=boardService.get(boardId);
        model.addAttribute("board", board);
        return "boardmodify";
    }

    //수정 반영
    @PostMapping("/board/update/{boardId}")
    public String boardUpdate(@PathVariable Long boardId, @ModelAttribute Board updatedBoard) throws IOException {
        boardService.modify(boardId,updatedBoard);
        return "redirect:/board/list";
    }
}