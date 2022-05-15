package com.example.deliveryprojectspringboot.repository;

import com.example.deliveryprojectspringboot.entity.BoardVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardVORepository extends JpaRepository<BoardVO,Long> {
}
