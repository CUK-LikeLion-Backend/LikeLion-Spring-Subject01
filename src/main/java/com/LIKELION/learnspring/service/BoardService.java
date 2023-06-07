package com.LIKELION.learnspring.service;

import com.LIKELION.learnspring.entity.Board;
import com.LIKELION.learnspring.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {
    @Autowired //객체를 자동으로 주입할때, //스프링빈이 읽어와서 알아서 boardrepository 를 주입을 해줍니다. (Dependency Injection)
    private BoardRepository boardRepository;

    //게시글 작성 처리
    public void write(Board board){
        //entity 에 저장하는 코드를 작성해주세요.
        //이렇게 작성한 서비스는 컨트롤러에서 이용됩니다.
        //헷갈리신다면 MVC구조, 어노테이션 등에 대해 파악하고 풀어주시길 바랍니다.
        boardRepository.save(board);
    }

    //게시글 리스트
    public List<Board> boardList() {
        return boardRepository.findAll();
    }
    //게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    //게시글 삭제
    public void boardDelete(Integer id){
        //삭제 후 리스트 페이지로 리다이렉트 할 수 있게 return 해 주세요.
        boardRepository.deleteById(id);
    }
}