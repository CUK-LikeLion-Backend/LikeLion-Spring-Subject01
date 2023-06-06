package com.likelion.learnspringboot2.controller;

import com.likelion.learnspringboot2.entity.Board;
import com.likelion.learnspringboot2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    /**
     * 역슬래시 경로로 들어왔을때 helloworld 를 리턴합니다.
     * @GetMapping("/") GetMapping : 어떤 url 로 접근할 것인지 지정해주는 어노테이션
     *     @ResponseBody
     *     public String main(){
     *         return "Hello World";
     *     }
     *
     * */


    //localhost:8090/board/wirte 주소로 접속하면 board write 페이지 보여주는 설정
    @GetMapping("/board/write")
    public String boardwriteForm(){
        return "boardwrite";    //반환한 값을 view resolver가 화면을 찾아 처리
    }

    //boardwrite.html 에서의 요청이 넘어왔는지 확인하는 메소드
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){ //html 에서지정한 이름대로 매개변수가 Board 클래스에 담겨서 들어옴
        //구현하시면됩니다.
        /**
         * 힌트 : 서비스에 작성한 코드를 이용하여 적절한 코드를 작성하세요
         **/
        boardService.boardwrite(board);
        return "";  //반환값이 없을 땐 요청 경로(/board/writepro)기반으로 뷰 이름 결정하여 반환

    }

    //R : 리스트 기능 구현
    @GetMapping("/board/list")
    public String boardList(Model model){
        List<Board> boardList;
        boardList = boardService.boardList();  //게시글 리스트를 가져옴
        model.addAttribute("list", boardList);
        //모델에 "boardList"라는 이름으로 게시글 리스트(boardList)를 모델에 추가
        //모델에 속성을 추가하면 해당 속성은 뷰에서 사용가능(컨드롤러에서 뷰로 데이터 전달)
        //해당 데이터를 참조하는 데 사용
        //html 파일에 변수 list로 설정되어있었기에 속성 이름을 "list"로 설정해줌
        return "boardlist";
    }

    //R : 상세페이지 기능 구현
    @GetMapping("/board/view") //localhost:8090/board/view?id=1 하면, 1 이 id 값이된다.
    //이렇듯 값을 전달하는  path variable 과query parameter 방식에 대해서도 알아보시길 바랍니다.
    public String boardView(Model model,Integer id){
        Board board;
        board = boardService.boardView(id);  //id로 특정 게시물 조회
        model.addAttribute("board", board);

        return "boardview";
    }

    //D : delete 기능
    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);   //삭제 후
        return "redirect:/board/list";  //사용자를 게시물 목록 페이지로 이동
    }

    //U : 수정기능 - 수정 버튼 동작 (수정 폼을 보여주는 역할)
    //path variable 을 이용한 get 방식입니다.
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        //ex) /board/modify/123 이면 id 매개변수에 123을 전달받게됨.
        Board board;
        board = boardService.boardView(id);    //수정할 게시물을 조회하여 저장
        model.addAttribute("board", board);

        return "boardmodify";
    }

    //boardModify에서 입력받은 수정된 내용을 받아와 실제 수정 기능
    //수정 반영
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board){
        Board currentboard;
        currentboard = boardService.boardView(id);

        currentboard.setTitle(board.getTitle());    //매개변수로 받아온 board의 제목으로 수정
        currentboard.setContent(board.getContent());
        boardService.boardwrite(currentboard);      //수정된 게시물 저장

        /**
         * board로 받아온 값을 get,set 을 이용해 새로 수정한 뒤에
         * 리스트 페이지로 리 다이렉트 한 코드를 작성해주면 됩니다.
         */

        return "redirect:/board/list";  //수정 완료 후 사용자를 리스트 페이지로 리다이렉트
    }

}
