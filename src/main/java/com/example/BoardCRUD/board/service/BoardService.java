package com.example.BoardCRUD.board.service;

import com.example.BoardCRUD.board.entity.Board;
import com.example.BoardCRUD.board.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    //스프링빈이 읽어와서 알아서 boardrepository 를 주입을 해줍니다. (Dependency Injection)
    private BoardRepository boardRepository;
    private Logger logger= LoggerFactory.getLogger(BoardService.class);

    @Autowired // 생성자가 하나라면 생략가능
    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    //게시글 작성 처리
    public void boardwrite(Board board){
        boardRepository.save(board);
    }



    //게시글 리스트 처리
    public List<Board> boardList()
    {
        logger.info(boardRepository.findAll().toString());
        return boardRepository.findAll();
    }

    //특정 게시글 불러오기
    public Optional<Board> boardView(Integer id){
        return boardRepository.findById(id);
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }




}
