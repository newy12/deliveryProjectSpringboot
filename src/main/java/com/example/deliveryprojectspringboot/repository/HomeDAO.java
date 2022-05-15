package com.example.deliveryprojectspringboot.repository;

import com.example.deliveryprojectspringboot.entity.ApplyVO;
import com.example.deliveryprojectspringboot.entity.ComusermVO;
import com.example.deliveryprojectspringboot.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HomeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	private final ApplyVORepository applyVORepository;
	private final ComusermVORepository comusermVORepository;
	
	
	public void register() {
		//String nowDate = mybatis.selectOne("HomeMapper.selectNow");
		SimpleDateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String currentDate = nowDate.format(now);

	}
	
	public void register2() {
		//List<ComusermVO> comusermVO= mybatis.selectList("HomeMapper.register");
		comusermVORepository.findAll();
	}
	
	public ComusermVO signUp(Map<String, String> reqeustMap) {
		//int result = mybatis.insert("HomeMapper.signup", reqeustMap);
		ComusermVO comusermVO = new ComusermVO();
		comusermVO.setUserId(reqeustMap.get("id"));
		comusermVO.setPassword(reqeustMap.get("password"));
		comusermVO.setName(reqeustMap.get("name"));
		comusermVO.setTel(reqeustMap.get("tel"));
		comusermVO.setBirth(reqeustMap.get("birth"));
		comusermVO.setGender(reqeustMap.get("gender"));
		comusermVO.setAddress1(reqeustMap.get("address1"));
		comusermVO.setAddress2(reqeustMap.get("address2"));

		return comusermVORepository.save(comusermVO);
	}
	
	public ComusermVO checkEmail (Map<String, String> reqeustMap) {
		boolean result ;
		/*List<ComusermVO> comusermVO= mybatis.selectList("HomeMapper.checkEmail", reqeustMap);*/
		/*if(comusermVO != null) { // select 값있음 > 중복
			result = false;
		}else {
			result = true;
		}*/
		return comusermVORepository.findByUserId(reqeustMap.get("id")).get();
	}
	
	public ComusermVO checkLogin (Map<String, String> reqeustMap) {
		//ComusermVO comusermVO = mybatis.selectOne("HomeMapper.checkLogin", reqeustMap);


		
//		if(comusermVO != null) { // select 값있음 > 중복 
//			result = false;
//		}else {
//			result = true;
//		}
		
		return comusermVORepository.findByUserIdAndPassword(reqeustMap.get("id"),reqeustMap.get("pw"));
	}
	
	public ApplyVO apply (Map<String, String> reqeustMap) {
		/*int result = mybatis.insert("HomeMapper.apply", reqeustMap);*/
/*		String startAddress1 = request.getParameter("startAddress1");
		String startAddress2 = request.getParameter("startAddress2");
		String endingAddress1 = request.getParameter("endingAddress1");
		String endingAddress2 = request.getParameter("endingAddress2");
		String applyName = request.getParameter("applyName");
		String applyTel = request.getParameter("applyTel");
		String applyItem = request.getParameter("applyItem");
		String applyPrice = request.getParameter("applyPrice");
		String arriveName = request.getParameter("arriveName");
		String arriveTel = request.getParameter("arriveTel");
		String startingDate = request.getParameter("startingDate");
		String endingDate = request.getParameter("endingDate");*/
		ApplyVO applyVO = new ApplyVO();
		applyVO.setApply_name(reqeustMap.get("applyName"));
		applyVO.setApply_tel(reqeustMap.get("applyTel"));
		applyVO.setStart_address1(reqeustMap.get("startAddress1"));
		applyVO.setDepartureTime(reqeustMap.get("startingDate"));
		applyVO.setArriveTime(reqeustMap.get("endingDate"));
		applyVO.setExchangeItem(reqeustMap.get("applyItem"));
		applyVO.setApplyPrice(reqeustMap.get("applyPrice"));
		applyVO.setArriveName(reqeustMap.get("arriveName"));
		applyVO.setArriveTel(reqeustMap.get("arriveTel"));
		applyVO.setEnd_address1(reqeustMap.get("endingAddress1"));
		applyVO.setStart_address2(reqeustMap.get("startAddress2"));
		applyVO.setEnd_address2(reqeustMap.get("endingAddress2"));
		applyVO.setDeliveryYn("N");
		applyVO.setDeliveryId("ND");

		return applyVORepository.save(applyVO);
	}
	
	public List<ApplyVO> applyList() {
		/*List<ApplyVO> list = mybatis.selectList("HomeMapper.applylist");*/

		return applyVORepository.findByDeliveryYn("N");
	}
	
	public List<ApplyVO> myApplyList(String userId) {
		/*List<ApplyVO> list = mybatis.selectList("HomeMapper.myapplylist", userId);*/
		return applyVORepository.findByApplyId(userId);
	}
	
	public List<ApplyVO> myDelivery(String userId) {
		/*List<ApplyVO> list = mybatis.selectList("HomeMapper.mydelivery", userId);*/
		return applyVORepository.findByDeliveryId(userId);
	}
	
	public ApplyVO applyView(Long id) {
		/*ApplyVO applyVO = mybatis.selectOne("HomeMapper.applyview", seq);*/
		return applyVORepository.findById(id).get();
	}
	
	public List<ComusermVO> checkBox(String userId) {
		/*List<ComusermVO> list = mybatis.selectList("HomeMapper.checkbox", userId);*/
		List<ComusermVO> list = comusermVORepository.findAllByUserId(userId);
		return list;
	}
	
	public void delteDelivery(Long id) {
		/*int result = mybatis.delete("HomeMapper.deletedelivery", seq);*/
		applyVORepository.deleteById(id);
	}
	
	public ApplyVO deliver(Map<String, Object> parameterMap) {
		/*int result = mybatis.update("HomeMapper.deliver", parameterMap);*/
		/*parameterMap.put("seq", seq);
		parameterMap.put("userId", (String) session.getAttribute("member"));*/
		Optional<ApplyVO> OptApplyVO = applyVORepository.findById((Long) parameterMap.get("seq"));
		if(OptApplyVO.isPresent()){
			ApplyVO applyVO = OptApplyVO.get();
			applyVO.setDeliveryYn("P");
			applyVO.setDeliveryId((String) parameterMap.get("userId"));
			return applyVORepository.save(applyVO);
		}
		return null;
	}
	
	public ApplyVO deliverySuc(Map<String, Object> parameterMap) {
		/*int result = mybatis.update("HomeMapper.deliverysuc", parameterMap);*/
		/*parameterMap.put("seq", id);
		parameterMap.put("userId", (String) session.getAttribute("member"));*/
		Optional<ApplyVO> OptApplyVO = applyVORepository.findByIdAndDeliveryId(parameterMap.get("seq"),parameterMap.get("userId"));
		if(OptApplyVO.isPresent()){
			ApplyVO applyVO = OptApplyVO.get();
			applyVO.setDeliveryYn("Y");
			return applyVORepository.save(applyVO);
		}
		return null;
	} 
	
	public ComusermVO userInfo(Map<String, String> parameterMap) {
		/*int result = mybatis.update("HomeMapper.userinfo", parameterMap);*/
		/*requestMap.put("name", name);
		requestMap.put("tel", tel);
		requestMap.put("address1", address1);
		requestMap.put("address2", address2);
		requestMap.put("userId", (String) session.getAttribute("member"));*/
		ComusermVO comusermVO = comusermVORepository.findByUserId(parameterMap.get("userId")).get();
		if(comusermVO.getUserId() != null){
			comusermVO.setName(parameterMap.get("name"));
			comusermVO.setTel(parameterMap.get("tel"));
			comusermVO.setAddress1(parameterMap.get("address1"));
			comusermVO.setAddress2(parameterMap.get("address2"));
			return comusermVORepository.save(comusermVO);
		}
		return null;
	}
	
}