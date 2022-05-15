package com.example.deliveryprojectspringboot.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ApplyVO {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //전엔 seq
	private String apply_name;
	private String apply_tel;
	private String start_address1;
	private String start_address2;
    private String end_address1;
    private String end_address2;
    private String departureTime;
    private String arriveTime;
    private String exchangeItem;
	private String applyPrice;
    private String arriveName;
    private String arriveTel;
    private String applyId;
    private String deliveryId;
    private String deliveryYn;

    
}
