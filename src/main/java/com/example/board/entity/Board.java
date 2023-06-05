package com.example.board.entity;


import lombok.Data;


import javax.persistence.*;


@Entity //DB 에 있는 테이블을 의미하는 어노테이션
@Data //get,set 메소드 이용가능하게 하는 어노테이션
@Table(name = "board")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
}


