package com.ebstudytemplates3week.controller;

import com.ebstudytemplates3week.domain.*;
import com.ebstudytemplates3week.service.BoardService;
import com.ebstudytemplates3week.service.CategoryService;
import com.ebstudytemplates3week.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board/free")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    /**
     * 검색 조건을 이용해 게시글 목록과 페이지 네비게이션 출력
     *
     * @param sf 검색 조건(시작일, 종료일, 카테고리, 키워드)
     * @param pg 페이지네이션(pageNum)
     * @param model searchFilter, pagination, boardList, categoryList
     * @return list
     */
    @GetMapping("/list")
    public String getList(@ModelAttribute("searchFilter") SearchFilter sf,
                          @ModelAttribute("pagination") Pagination pg,
                          Model model) {

        //검색 필터 셋팅
        SearchFilter searchFilter = boardService.getSearchFilter(sf);
        model.addAttribute("searchFilter", searchFilter);

        //페이지네이션
        Pagination pagination = new Pagination();
        pagination.setPageNum(pg.getPageNum());
        pagination.setTotalCount(boardService.getTotalCount(searchFilter));

        model.addAttribute("pagination", pagination);

        //게시글 목록
        model.addAttribute("boardList", boardService.getBoardList(searchFilter, pagination));

        //카테고리 목록
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);

        return "list";
    }
    //todo: 쿼리랑 파라미터를 나눠서 전달,

    /**
     * 게시글 상세 조회
     * @param model
     * @param id board_id
     * @param pageNum
     * @return view
     */
    @GetMapping("/view")
    public String getViewInfo(
            Model model,
            @RequestParam("id") String id,
            @RequestParam("pageNum") String pageNum) {

        boardService.increaseViewCount(id);

        model.addAttribute("board", boardService.getBoardById(id));
        model.addAttribute("commentList", commentService.getCommentByBoardId(id));
        model.addAttribute("id", id); //todo 굳이 필요한지
        model.addAttribute("pageNum", pageNum);

        return "view";
    }
    //todo: 잘못된 id,pageNum 예외처리

    /**
     * 글 작성 페이지 출력
     * @param pageNum
     * @param model
     * @return write
     */
    @GetMapping("/write")
    public String getWriteInfo(
            @RequestParam("pageNum") String pageNum,
            Model model) {

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("pageNum", pageNum);

        return "write";
    }

    /**
     * 글 등록 버튼 클릭시 유효성 검사 후 글 작성
     * @param board category_id, writer, password, title, content
     * @return redirect:/board/free/list
     */
    @PostMapping("/write")
    public String writeBoard(
            @ModelAttribute("board") @Valid Board board){

        log.info("board ={}", board);

        boardService.writeBoard(board);

        return "redirect:/board/free/list";
    }

    /**
     * 글 작성 폼에 필요한 요소들 출력
     * @param model
     * @param id board 번호
     * @param pageNum
     * @return modify
     */
    @GetMapping("/modify")
    public String getModifyInfo(
            Model model,
            @RequestParam("id") String id,
            @RequestParam("pageNum") String pageNum) {

        model.addAttribute("board", boardService.getBoardById(id));
        model.addAttribute("id", id); //todo 굳이 필요한지
        model.addAttribute("pageNum", pageNum);

        return "modify";
    }

    /**
     *  게시글 수정 버튼 클릭시 유효성 검사 후 수정
     * @param board writer, title, content
     * @return redirect:/board/free/list
     */
    @PostMapping("/modify")
    public String modifyBoard(
            @ModelAttribute("board") @Valid Board board){

        log.info("board ={}", board);

        // boardId와 password가 일치하는지
//        boolean isNotPassword = boardService.passwordCheck(board) == 0;

//        if (isNotPassword) {
//            return "400";
//        }

        boardService.modifyBoard(board);

        return "redirect:/board/free/list";
    }

}