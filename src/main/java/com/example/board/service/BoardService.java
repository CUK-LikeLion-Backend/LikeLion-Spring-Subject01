package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    // 게시글 작성
    public void boardwrite(Board board) {
        boardRepository.save(board);
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
    // 게시글 목록 조회
    public List<Board> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }
    // 게시글 수정
    public void boardUpdate(Board board) {
        boardRepository.save(board);
    }
    // 특정 게시글 조회
    public Board getBoard(Integer id) {
        return boardRepository.findById(id).orElseThrow();
    }
}
