package com.example.deliveryprojectspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BoardVO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "comuserm_id")
    @JsonIgnore
    private ComusermVO comusermVO;
}
