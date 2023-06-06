package com.likelion.kjyyjk.learnspringboot.service;

import com.likelion.kjyyjk.learnspringboot.entity.Board;
import com.likelion.kjyyjk.learnspringboot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void boardwrite(Board board){
        boardRepository.save(board);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    public Board boardView(Integer id){
        return boardRepository.findById(id).orElse(null);
    }

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

    public void boardUpdate(Integer id, Board board){
        Board findBoard = boardRepository.findById(id).orElse(null);
        findBoard.setContent(board.getContent());
        findBoard.setTitle(board.getTitle());
        boardwrite(findBoard);
    }
}