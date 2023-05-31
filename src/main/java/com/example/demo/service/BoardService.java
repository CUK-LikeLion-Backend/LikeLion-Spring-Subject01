package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(Board board){

        boardRepository.save(board);
    }


    public Board get(Long id) {
        return  boardRepository.findById(id).get();

    }

    public List<Board> getList(){
        return boardRepository.findAll();

    }


    @Transactional
    public void modify(Long id, Board update) throws IOException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board doesn't exist"));

        board.modify(update);

    }

    public void delete(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));


        boardRepository.delete(board);
    }
}
