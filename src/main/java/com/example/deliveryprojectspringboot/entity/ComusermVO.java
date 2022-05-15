package com.example.deliveryprojectspringboot.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ComusermVO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userId;
	private String password;
	private String name;
	private String tel;
	private String birth;
	private String gender;
	private String address1;
	private String address2;
	//신고당한 횟수
	private Integer lev;
	@OneToMany(mappedBy = "comusermVO")
	private List<BoardVO> boardVOList = new ArrayList<>();

	@OneToMany(mappedBy = "comusermVO")
	private List<AccusationVO> accusationVOList =new ArrayList<>();

	@OneToMany(mappedBy = "reportedId")
	private List<AccusationVO> reportedList = new ArrayList<>();

	@OneToOne(mappedBy = "comusermVO")
	private BlackListVO blackListVO;


	
	
}
