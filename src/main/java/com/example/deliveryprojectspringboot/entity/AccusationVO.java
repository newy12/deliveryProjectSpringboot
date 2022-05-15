package com.example.deliveryprojectspringboot.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AccusationVO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String accusationContent;

    //신고당한사람의아이디
    @ManyToOne
    @JoinColumn(name="reported_Id")
    private ComusermVO reportedId;

    //신고자
    @ManyToOne
    @JoinColumn(name = "comuserm_id")
    private ComusermVO comusermVO;









}
