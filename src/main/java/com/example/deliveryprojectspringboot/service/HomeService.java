package com.example.deliveryprojectspringboot.service;

import com.example.deliveryprojectspringboot.entity.ComusermVO;
import com.example.deliveryprojectspringboot.controller.HomeController;
import com.example.deliveryprojectspringboot.entity.ApplyVO;
import com.example.deliveryprojectspringboot.repository.HomeDAO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class HomeService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

	private final HomeDAO homeDAO;
	
	public void android() {
		logger.info("android2 service");
		homeDAO.register();
		homeDAO.register2();
	}
	
//	회원가입
	public ComusermVO signUp(Map<String, String> reqeustMap) {
		return homeDAO.signUp(reqeustMap);
	}
	
// 아이디 중복확인
	public Boolean checkEmail(Map<String, String> requestMap) {

		Boolean result ;
		ComusermVO comusermVO = homeDAO.checkEmail(requestMap);
		if(comusermVO.getUserId() == null){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
//	아이디 로그인
	public ComusermVO checkLogin(Map<String, String> requestMap) {
		return homeDAO.checkLogin(requestMap);
	}
//	신청 하기
	public ApplyVO apply(Map<String, String> requestMap) {
		return homeDAO.apply(requestMap);
	}
//  전체 직거래
	public List<ApplyVO> applyList(){
		return homeDAO.applyList();
	}
//	내 직거래 신청 목
	public List<ApplyVO> myApplyList(String userId){
		return homeDAO.myApplyList(userId);
	}
//	내가 배달중 직거래 
	public List<ApplyVO> myDelivery(String userId){
		return homeDAO.myDelivery(userId);
	}
//	신청 리스트 클릭
	public ApplyVO applyView(Long id){
		return homeDAO.applyView(id);
	}
//	checkbox 클릭
	public List<ComusermVO> checkBox(String userId){
		return homeDAO.checkBox(userId);
	}
//	배달삭제
/*	public int delteDelivery(Long id){
		return homeDAO.delteDelivery(id);
	}*/
//	배달하기
	public ApplyVO deliver(Map<String, Object> parameterMap){
		return homeDAO.deliver(parameterMap);
	}
//	배달완료
	public ApplyVO deliverySuc(Map<String, Object> parameterMap){
		return homeDAO.deliverySuc(parameterMap);
	}
//	정보수정
	public ComusermVO userInfo(Map<String, String> parameterMap){
		return homeDAO.userInfo(parameterMap);
	}
}