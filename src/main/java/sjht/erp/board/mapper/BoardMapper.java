package sjht.erp.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.board.dto.BoardRequest;
import sjht.erp.board.dto.BoardResponse;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시글 리스트 조회
     * @param boardtype
     * @return 게시글 리스트
     */
    List<BoardResponse> boardList(String boardtype);

    /**
     * 조회수
     * @param bno - PK
     */
    int boardCount(int bno);

    /**
     * 게시글 상세정보 조회
     * @param bno - PK
     * @return 게시글 상세정보
     */
    BoardResponse boardDetail(int bno);

    /**
     * 게시글 저장
     * @param request - 게시글 정보
     */
    void boardInsert(BoardRequest request);

    /**
     * 게시글 수정
     * @param request - 게시글 정보
     */
    void boardUpdate(BoardRequest request);

    /**
     * 게시글 삭제
     * @param bno - PK
     */
    void boardDelete(int bno);

    /**
     * 게시글 검색
     * @param search
     */
    List<BoardResponse> searchBoardList(HashMap<String, String> search);
}

