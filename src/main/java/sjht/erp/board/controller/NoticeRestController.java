package sjht.erp.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sjht.erp.board.dto.BoardResponse;
import sjht.erp.board.dto.NoticeRequest;
import sjht.erp.board.dto.NoticeResponse;
import sjht.erp.board.service.NoticeService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeRestController {

    private final NoticeService noticeService;

    // 공지사항 리스트
    @PostMapping("/api/noticelist")
    public List<NoticeResponse> noticeList(@RequestBody HashMap<String, String> boardtype) {
        List<NoticeResponse> response = noticeService.noticeList(boardtype.get("boardtype"));
        System.out.println(boardtype.get("boardtype"));
        return response;
    }

    // 자유게시판 검색 결과 리스트
    @PostMapping("/api/searchnoticelist")
    public ResponseEntity<List<NoticeResponse>> noticeSearchList(@RequestBody HashMap<String, String> search){

        List<NoticeResponse> response = noticeService.searchNoticeList(search);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    // 공지사항 글 작성
    @PostMapping("/api/noticeinsert")
    public boolean noticeInsert(@RequestBody HashMap<String, String> map,
                              HttpServletRequest request) {
        String title = map.get("title");
        String content = map.get("content");
        NoticeRequest noticeRequest = new NoticeRequest();
        noticeRequest.setTitle(title);
        noticeRequest.setContent(content);
        EmployeeDto empDto = (EmployeeDto) request.getAttribute("empNo");
        noticeRequest.setEmpno(empDto.getEmpno());

        if(noticeService.noticeInsert( noticeRequest ) != 0 ) {
            return true;
        }
            return false;
    }


    // 공지사항 글 삭제
    @PostMapping("/api/noticedelete/{bno}")
    public boolean noticeDelete(@PathVariable int bno) {

        if (noticeService.noticeDelete(bno) != 0){
            return true;
        }
            return false;
    }

    // 공지사항 글 수정
    @PostMapping("/api/noticeupdate")
    public boolean noticeUpdate(
                               @RequestBody NoticeRequest noticeRequest,
                               HttpServletRequest request) {
        request.getAttribute("empNo");

        if (noticeService.noticeUpdate(noticeRequest) != 0 ) {
            return true;
        }
            return false;
    }

}
