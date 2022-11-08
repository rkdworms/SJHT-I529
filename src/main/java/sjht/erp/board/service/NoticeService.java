package sjht.erp.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sjht.erp.board.dto.NoticeRequest;
import sjht.erp.board.dto.NoticeResponse;
import sjht.erp.board.mapper.NoticeMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<NoticeResponse> noticeList() {

        return noticeMapper.noticeList();
    }

    /**
     * 조회수
     * @param bno - PK
     * @return 조회수
     */
    public int noticeCount(int bno) {
        return noticeMapper.noticeCount(bno);
    }

    /**
     * 게시글 상세정보 조회
     * @param bno - PK
     * @return 게시글 상세정보
     */
    public NoticeResponse noticeDetail(int bno) {
        return noticeMapper.noticeDetail(bno);
    }

    /**
     * 게시글 저장
     * @param request - 게시글 정보
     * @return Generated PK
     */
    public int noticeInsert(NoticeRequest request) {
        noticeMapper.noticeInsert(request);
        return request.getBno();

    }

    /**
     * 게시글 수정
     * @param request - 게시글 정보
     * @return PK
     */

    public int noticeUpdate(NoticeRequest request) {
        noticeMapper.noticeUpdate(request);
        return request.getBno();

    }

    /**
     * 게시글 삭제
     * @param bno - PK
     * @return PK
     */
    public int noticeDelete(int bno) {
        noticeMapper.noticeDelete(bno);
        return bno;
    }


}