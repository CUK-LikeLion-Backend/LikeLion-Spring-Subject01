package com.example.board.entity;

import com.example.board.controller.BoardForm;

import javax.persistence.*;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


    public Board(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public void update(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
