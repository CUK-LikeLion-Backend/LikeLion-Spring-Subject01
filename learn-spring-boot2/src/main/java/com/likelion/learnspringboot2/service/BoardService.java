package com.likelion.learnspringboot2.service;

import com.likelion.learnspringboot2.entity.Board;
import com.likelion.learnspringboot2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired //스프링빈이 읽어와서 알아서 boardrepository 를 주입을 해줍니다. (Dependency Injection)
    //즉, @Autowired 어노테이션이 붙은 필드인 boardRepository에는
    // Spring이 BoardRepository 인터페이스를 구현한 클래스의 객체를 자동으로 주입
    private BoardRepository boardRepository;

    //게시글 작성 처리
        public void boardwrite(Board board){
            //entity 에 저장하는 코드를 작성해주세요.
            //이렇게 작성한 서비스는 컨트롤러에서 이용됩니다.
            //헷갈리신다면 MVC구조, 어노테이션 등에 대해 파악하고 풀어주시길 바랍니다.
            this.boardRepository.save(board);
        }

        //게시글 리스트 처리
        public List<Board> boardList()
        {
            //작성해주세요
            return this.boardRepository.findAll();
        }

        //특정 게시글 불러오기
        public Board boardView(Integer id){
            return this.boardRepository.findById(id).get();
        }

        //특정 게시글 삭제
        public void boardDelete(Integer id){
            //삭제 후 리스트 페이지로 리다이렉트 할 수 있게 return 해 주세요.
            this.boardRepository.deleteById(id);
        }


    }
