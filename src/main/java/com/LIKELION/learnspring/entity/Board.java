package com.LIKELION.learnspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //DB 에 있는 테이블을 의미하는 어노테이션
@Data //get,set 메소드 이용가능하게 하는 어노테이션
public class Board {
    //테이블을 토대로 작성해주시면됩니다.
    //@Id //해당 클래스의 주요 식별자(primary key)를 나타내는 어노테이션
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA에서 기본 키를 자동으로 생성할 때 사용하는 방법 중 하나
    private Integer id;
    private String title;
    private String content;
}