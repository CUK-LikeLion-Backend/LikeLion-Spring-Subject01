package com.example.BoardCRUD.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //DB 에 있는 테이블을 의미하는 어노테이션
@Data //get,set 메소드 이용가능하게 하는 어노테이션
@NoArgsConstructor
public class Board {

    //테이블을 토대로 작성해주시면됩니다.
    @Id
    //해당 클래스의 주요 식별자(primary key)를 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)//JPA에서 기본 키를 자동으로 생성할 때 사용하는 방법 중 하나
    @Column(name="boardId", unique = true, nullable = false)
    private int boardId;

    @Column(name = "title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="userId")
    private int userId;

    @Builder
    public Board(String title, String content, int userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }


}