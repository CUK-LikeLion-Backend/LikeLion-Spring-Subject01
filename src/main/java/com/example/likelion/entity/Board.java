package com.example.likelion.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity //객체와 테이블 매핑
@Builder
@Table(name = "Board") //테이블 지정
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // JPA에서 lazy관련 에러 날 경우 사용
public class Board {

    @Column(name = "id")
    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //기본키 생성을 데이터베이스에 위임, 즉 id값이 null이면 DB가 알아서 increment함
    @NotNull
    private int id;

    @Column(name = "title", length = 45)
    @NotNull
    private String title;

    @Column(name = "content")
    @NotNull
    private String content;

}
