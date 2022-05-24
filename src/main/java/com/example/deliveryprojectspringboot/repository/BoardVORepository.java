package com.example.deliveryprojectspringboot.repository;

import com.example.deliveryprojectspringboot.entity.BoardVO;
import com.example.deliveryprojectspringboot.entity.ComusermVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardVORepository extends JpaRepository<BoardVO,Long> {
    List<BoardVO> findAllByComusermVO(ComusermVO comusermVO);

}
