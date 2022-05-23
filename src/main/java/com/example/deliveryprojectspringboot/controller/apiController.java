package com.example.deliveryprojectspringboot.controller;

import com.example.deliveryprojectspringboot.entity.AccusationVO;
import com.example.deliveryprojectspringboot.entity.BoardVO;
import com.example.deliveryprojectspringboot.entity.ComusermVO;
import com.example.deliveryprojectspringboot.repository.AccusationVORepository;
import com.example.deliveryprojectspringboot.repository.BoardVORepository;
import com.example.deliveryprojectspringboot.repository.ComusermVORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class apiController {
    private final static Logger log = Logger.getGlobal();
    private final BoardVORepository boardVORepository;
    private final ComusermVORepository comusermVORepository;
    private final AccusationVORepository accusationVORepository;
    //게시글 목록
    @GetMapping("/boardList")
    public List<BoardVO> boardList(){
        return boardVORepository.findAll();
    }
    //게시글 상세페이지
    @GetMapping("/boardDetail")
    public BoardVO boardDetail(HttpServletRequest request){
        Long id = Long.valueOf(request.getParameter("id"));
        return boardVORepository.findById(id).get();
    }
    //게시글 작성
    @PostMapping("/boardWrite")
    public Boolean boardWrite(HttpServletRequest request, HttpSession session){
        if(request == null){
        return false;
        }
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle(title);
        boardVO.setContent(content);
        boardVO.setLocalDateTime(LocalDateTime.now());
        boardVO.setComusermVO((ComusermVO) session.getAttribute("member"));
        boardVORepository.save(boardVO);
        return true;
    }
    //게시글 수정
    @PutMapping("/boardEdit")
    public Boolean boardEdit(HttpServletRequest request){
        if(request == null){
            return false;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Optional<BoardVO> OptBoardVO = boardVORepository.findById(id);
        if(OptBoardVO.isPresent()){
            BoardVO boardVO = OptBoardVO.get();
            boardVO.setTitle(title);
            boardVO.setContent(content);
            boardVORepository.save(boardVO);
            return true;
        }
        return false;
    }
    //게시글 삭제
    @DeleteMapping("/boardDelete")
    public Boolean boardDelete(HttpServletRequest request){
        if(request == null){
            return false;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        boardVORepository.deleteById(id);
        return true;
    }
    //신고하기
    @PostMapping("/accusation")
    public Boolean accusation(HttpServletRequest request, HttpSession session){
        String accusationContent = request.getParameter("accusationContent");
        String reportedId = request.getParameter("reportedId");
        Optional<ComusermVO> OptComusermVO = comusermVORepository.findByUserId(reportedId);
        if(OptComusermVO.isPresent()){
            ComusermVO comusermVO = OptComusermVO.get();
            AccusationVO accusationVO = new AccusationVO();
            accusationVO.setAccusationContent(accusationContent);
            accusationVO.setComusermVO((ComusermVO) session.getAttribute("member"));
            accusationVO.setReportedId(comusermVO);
            accusationVORepository.save(accusationVO);
            log.info("신고등록완료.");
            comusermVO.setLev(comusermVO.getLev()+1);
            comusermVORepository.save(comusermVO);
            log.info("lev {}+1 값갱신"+comusermVO.getLev());
            return true;
        }
        log.info("신고할사람의 정보가 없음");
        return false;

    }


}
