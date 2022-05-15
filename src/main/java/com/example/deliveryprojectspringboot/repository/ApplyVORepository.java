package com.example.deliveryprojectspringboot.repository;

import com.example.deliveryprojectspringboot.entity.ApplyVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyVORepository extends JpaRepository<ApplyVO,Long> {
    List<ApplyVO> findByDeliveryYn(String n);

    List<ApplyVO> findByApplyId(String userId);

    List<ApplyVO> findByDeliveryId(String userId);

    Optional<ApplyVO> findByIdAndDeliveryId(Object seq, Object userId);
}
