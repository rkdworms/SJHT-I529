package sjht.erp.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sjht.erp.board.dto.BoardRequest;
import sjht.erp.board.dto.BoardResponse;
import sjht.erp.board.mapper.BoardMapper;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<BoardResponse> boardList() {
        return boardMapper.boardList();
    }

    /**
     * 조회수
     * @param bno - PK
     * @return 조회수
     */
    public int boardCount(int bno) {
        return boardMapper.boardCount(bno);
    }

    /**
     * 게시글 상세정보 조회
     * @param bno - PK
     * @return 게시글 상세정보
     */
    public BoardResponse boardDetail(int bno) {
        return boardMapper.boardDetail(bno);
    }

    /**
     * 게시글 저장
     * @param request - 게시글 정보
     * @return Generated PK
     */
    public int boardInsert(BoardRequest request) {
        boardMapper.boardInsert(request);
        return request.getBno();

    }

    /**
     * 게시글 수정
     * @param request - 게시글 정보
     * @return PK
     */
    public int boardUpdate(BoardRequest request) {
        boardMapper.boardUpdate(request);
        return request.getBno();

    }

    /**
     * 게시글 삭제
     * @param bno - PK
     * @return PK
     */
    public int boardDelete(int bno) {
        boardMapper.boardDelete(bno);
        return bno;
    }


    public List<BoardResponse> searchBoardList(HashMap<String, String> search) {

        return boardMapper.searchBoardList(search);
    }
}