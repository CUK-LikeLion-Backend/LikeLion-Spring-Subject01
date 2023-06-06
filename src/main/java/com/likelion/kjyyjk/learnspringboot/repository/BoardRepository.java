package com.likelion.kjyyjk.learnspringboot.repository;

import com.likelion.kjyyjk.learnspringboot.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {
}
