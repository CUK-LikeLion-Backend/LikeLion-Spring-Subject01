package com.example.demo.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Data
public class Board {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotNull
    private String title;
    @Column
    @NotNull
    private String content;
    @Column
    private String filename;
    @Column
    private String filepath;

    public Board() {}
}
