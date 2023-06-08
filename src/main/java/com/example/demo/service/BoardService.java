package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 저장
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    // 모든 게시글 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 특정 ID를 가진 게시글 조회
    public Board findById(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        return boardOptional.orElse(null);
    }

    // 게시글 수정
    public Board update(Board board) {
        return boardRepository.save(board);
    }

    // 게시글 삭제
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
