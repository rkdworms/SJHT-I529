package sjht.erp.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.board.dto.NoticeRequest;
import sjht.erp.board.dto.NoticeResponse;

import java.util.List;

@Mapper
public interface NoticeMapper {

    /**
     * 게시글 리스트 조회
     * @param boardtype
     * @return 게시글 리스트
     */
    List<NoticeResponse> noticeList(String boardtype);

    /**
     * 조회수
     * @param bno - PK
     */
    int noticeCount(int bno);



    /**
     * 게시글 상세정보 조회
     * @param bno - PK
     * @return 게시글 상세정보
     */
    NoticeResponse noticeDetail(int bno);

    /**
     * 게시글 저장
     * @param request - 게시글 정보
     */
    void noticeInsert(NoticeRequest request);

    /**
     * 게시글 수정
     * @param request - 게시글 정보
     */
    void noticeUpdate(NoticeRequest request);

    /**
     * 게시글 삭제
     * @param bno - PK
     */
    void noticeDelete(int bno);





}

