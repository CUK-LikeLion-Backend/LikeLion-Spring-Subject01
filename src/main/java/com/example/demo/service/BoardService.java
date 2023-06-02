package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.Content;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private String projectPath;
    private List<Content> contentList;

    public BoardService(BoardRepository boardRepository) {
        this.projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\";
        this.boardRepository = boardRepository;
        contentList = new ArrayList<>();
        contentList.add(new Content("Board list", "board/list"));
        contentList.add(new Content("Board write", "board/write"));
    }

    public List<Content> index() {
        return contentList;
    }

    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    public void boardWrite(Board board, MultipartFile file) throws IOException {
        UUID uuid = UUID.randomUUID(); //식별자
        String filename = uuid + "_" + file.getOriginalFilename(); //파일이름 생성
        if (!file.isEmpty() && (board.getFilename() == null || board.getFilename().equals(""))) {
            File saveFile = new File(projectPath,filename); //객체 생성
            file.transferTo(saveFile); //
            board.setFilename(filename);
            board.setFilepath(projectPath + filename);
        }
        boardRepository.save(board);
    }

    public void boardModify(Integer id, Board board, MultipartFile file) throws IOException {
        board.setId(id);
        Optional<Board> ret = boardRepository.findById(id);
        if (ret.isEmpty()) {
            return;
        }
        Board original = ret.get();
        board.setFilename(original.getFilename());
        board.setFilepath(original.getFilepath());
        boardWrite(board, file);
    }

    public Optional<Board> boardView(Integer id) {
        return boardRepository.findById(id);
    }

    public void boardDelete(Integer id) {
        Optional<Board> ret = boardRepository.findById(id);
        if (ret.isEmpty()) {
            return ;
        }
        Board board = ret.get();
        if (board.getFilename() != null) {
            File saveFile = new File(board.getFilepath()); //객체 생성
            saveFile.delete();
        }
        boardRepository.deleteById(id);
    }

    public void boardDownload(String filepath, HttpServletResponse response) throws Exception {
        try {
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file); // 파일 읽어오기
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "utf-8")); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
            OutputStream out = response.getOutputStream();

            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                out.write(buffer, 0, read);
            }
            fileInputStream.close();
        } catch (Exception e) {
            throw new Exception("download error");
        }
    }
}
