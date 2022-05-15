package com.example.deliveryprojectspringboot.repository;

import com.example.deliveryprojectspringboot.entity.ComusermVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComusermVORepository extends JpaRepository<ComusermVO,Long> {
    ComusermVO findByIdAndPassword(Long id, String password);

    Optional<ComusermVO> findByUserId(String userId);


    ComusermVO findByUserIdAndPassword(String userId, String pw);

    List<ComusermVO> findAllByUserId(String userId);
}
