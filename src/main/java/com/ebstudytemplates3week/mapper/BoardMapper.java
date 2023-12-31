package com.ebstudytemplates3week.mapper;

import com.ebstudytemplates3week.vo.Board;
import com.ebstudytemplates3week.vo.Pagination;
import com.ebstudytemplates3week.vo.SearchFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    /**
     * 총 게시글 수 조회
     * @param searchFilter 검색 조건
     * @return 총 게시글 수
     */
    int getTotalCount(@Param("searchFilter") SearchFilter searchFilter);

    /**
     * 게시글 리스트 조회
     * @param searchFilter 검색 조건
     * @param pagination 페이징
     * @return 게시글 리스트
     */
    List<Board> getBoardList(@Param("searchFilter") SearchFilter searchFilter, @Param("pagination") Pagination pagination);

    /**
     * 게시글 단일 조회
     * @param id 게시글 번호
     * @return board
     */
    Optional<Board> getBoardById(String id);

    /**
     * id의 조회수 증가
     * @param id
     */
    void increaseViewCount(String id);

    /**
     * 게시글 작성
     * @param board category_id, writer, password, title, content
     */
    void writeBoard(Board board);

    /**
     * 게시글 수정
     * @param board id, writer, title, content
     */
    void modifyBoard(Board board);

    /**
     * 암호화된 비밀번호 조회
     * @param id 게시글 번호
     * @return 비밀번호
     */
    String getPassword(String id);

    /**
     * 게시글 삭제
     * @param id 삭제할 게시글 번호
     */
    void deleteBoard(String id);
}
