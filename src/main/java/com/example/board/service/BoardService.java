package com.example.board.service;

import com.example.board.controller.BoardForm;
import com.example.board.entity.Board;
import com.example.board.repository.JpaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BoardService {

    private JpaBoardRepository boardRepository;

    public BoardService(JpaBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(BoardForm form){
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        boardRepository.save(board);
    };

    public List<Board> showList(){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Optional<Board> findById(Long id) {
        Optional<Board> b = boardRepository.findById(id);
        return b;
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public void update(Long id, Board b){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        board.update(b);
    }
}
