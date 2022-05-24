package com.example.deliveryprojectspringboot.controller;

import com.example.deliveryprojectspringboot.entity.AccusationVO;
import com.example.deliveryprojectspringboot.entity.BoardVO;
import com.example.deliveryprojectspringboot.entity.ComusermVO;
import com.example.deliveryprojectspringboot.repository.AccusationVORepository;
import com.example.deliveryprojectspringboot.repository.BoardVORepository;
import com.example.deliveryprojectspringboot.repository.ComusermVORepository;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class apiController {
    private final static Logger log = Logger.getGlobal();
    private final BoardVORepository boardVORepository;
    private final ComusermVORepository comusermVORepository;
    private final AccusationVORepository accusationVORepository;

    //게시글 목록
    @RequestMapping("/boardList")
    public Object boardList() {
        List<BoardVO> boardVOList = boardVORepository.findAll();
        Map<String, String> data = new HashMap<>();
        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONArray sortArray = new JSONArray();
        for (int i = 0; i < boardVOList.size(); i++) {
            jsonObject.put("id", String.valueOf(boardVOList.get(i).getId()));
            jsonObject.put("title", boardVOList.get(i).getTitle());
            jsonObject.put("content", boardVOList.get(i).getContent());
            jsonObject.put("localDateTime", String.valueOf(boardVOList.get(i).getLocalDateTime()));
            jsonObject.put("member", boardVOList.get(i).getComusermVO().getUserId());
            array.add(jsonObject);
            jsonObject = new JSONObject();
        }
        return array;
}




    //게시글 상세페이지
    @RequestMapping("/boardDetail")
    public Map<String, String> boardDetail(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        BoardVO boardVO = boardVORepository.findById(id).get();
        Map<String,String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("title",boardVO.getTitle());
        map.put("content",boardVO.getContent());
        map.put("localDateTime", String.valueOf(boardVO.getLocalDateTime()));
        map.put("member",boardVO.getComusermVO().getUserId());

        System.out.println("id = " + id);
        return map;
    }

    //게시글 작성
    @RequestMapping("/boardWrite")
    public Boolean boardWrite(HttpServletRequest request, HttpSession session) {
        if (request == null) {
            return false;
        }
        String comusermVO = (String) session.getAttribute("member");
        ComusermVO comusermVO1 = comusermVORepository.findByUserId(comusermVO);

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle(title);
        boardVO.setContent(content);
        boardVO.setLocalDateTime(LocalDateTime.now());
        boardVO.setComusermVO(comusermVO1);
        boardVORepository.save(boardVO);
        return true;
    }

    //게시글 수정
    @RequestMapping("/boardEdit")
    public Boolean boardEdit(HttpServletRequest request) {
        if (request == null) {
            return false;
        }

        Long id = Long.valueOf(request.getParameter("id"));
        System.out.println("id = " + id);
        String title = request.getParameter("title");
        System.out.println("title = " + title);
        String content = request.getParameter("content");
        System.out.println("content = " + content);
        Optional<BoardVO> OptBoardVO = boardVORepository.findById(id);
        if (OptBoardVO.isPresent()) {
            BoardVO boardVO = OptBoardVO.get();
            boardVO.setTitle(title);
            boardVO.setContent(content);
            boardVO.setLocalDateTime(LocalDateTime.now());
            boardVO.setComusermVO(OptBoardVO.get().getComusermVO());
            boardVORepository.save(boardVO);
            return true;
        }
        return false;
    }

    //게시글 삭제
    @RequestMapping("/boardDelete")
    public Boolean boardDelete(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        boardVORepository.deleteById(id);
        return true;
    }

    //신고하기
    @PostMapping("/accusation")
    public Boolean accusation(HttpServletRequest request, HttpSession session) {

        String accusationContent = request.getParameter("accusationContent");
        String reportedId = request.getParameter("reportedId");
        ComusermVO OptComusermVO = comusermVORepository.findByUserId(reportedId);
        ComusermVO comusermVO = OptComusermVO;
        AccusationVO accusationVO = new AccusationVO();
        accusationVO.setAccusationContent(accusationContent);
        accusationVO.setComusermVO((ComusermVO) session.getAttribute("member"));
        accusationVO.setReportedId(comusermVO);
        accusationVORepository.save(accusationVO);
        log.info("신고등록완료.");
        comusermVO.setLev(comusermVO.getLev() + 1);
        comusermVORepository.save(comusermVO);
        log.info("lev {}+1 값갱신" + comusermVO.getLev());
        return true;

   /*     log.info("신고할사람의 정보가 없음");
        return false;*/

    }


}
