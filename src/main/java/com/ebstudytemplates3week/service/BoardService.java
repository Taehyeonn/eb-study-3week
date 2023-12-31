package com.ebstudytemplates3week.service;

import com.ebstudytemplates3week.exception.EntityNotFoundException;
import com.ebstudytemplates3week.vo.Board;
import com.ebstudytemplates3week.vo.Pagination;
import com.ebstudytemplates3week.vo.SearchFilter;
import com.ebstudytemplates3week.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글의 총 갯수를 구한다
     *
     * @param searchFilter 카테고리, 키워드, 시작일, 종료일
     * @return 게시글의 총 갯수
     */
    public int getTotalCount(SearchFilter searchFilter) {
        return boardMapper.getTotalCount(searchFilter);
    }

    /**
     * 게시글 리스트 조회
     *
     * @param searchFilter 카테고리, 키워드, 시작일, 종료일
     * @param pagination   페이징 시작 번호, 한 페이지의 게시글 최대 갯수
     * @return 게시글 리스트
     */
    public List<Board> getBoardList(SearchFilter searchFilter, Pagination pagination) {
        return boardMapper.getBoardList(searchFilter, pagination);
    }

    /**
     * 게시글 단일 조회
     * 만약 게시글 번호와 일치하는 데이터가 없다면 EntityNotFoundException 예외 발생
     *
     * @param id 게시글 번호
     * @return board
     */
    public Board getBoardById(String id) {
        return boardMapper.getBoardById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    /**
     * id에 해당하는 조회수 증가
     *
     * @param id board_id
     */
    public void increaseViewCount(String id) {
        boardMapper.increaseViewCount(id);
    }

    /**
     * 입력받은 board 파라미터를 이용해 게시글 작성
     *
     * @param board category_id, writer, password, title, content
     */
    public void writeBoard(Board board) {
        boardMapper.writeBoard(board);
    }

    /**
     * 게시글 수정
     *
     * @param board writer, title, content
     */
    public void modifyBoard(Board board) {
        boardMapper.modifyBoard(board);
    }

    /**
     * 게시글 삭제
     *
     * @param id 삭제할 게시글 번호
     */
    public void deleteBoard(String id) {
        boardMapper.deleteBoard(id);
    }

    /**
     * 암호화된 비밀번호 조회
     *
     * @param id 게시글 번호
     * @return 비밀번호
     */
    public String getPassword(String id) {
        return boardMapper.getPassword(id);
    }
}