package sjht.erp.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sjht.erp.home.dto.NoticeDto;
import sjht.erp.home.service.HomeServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final HomeServiceImpl homeServiceImpl;

    // home dashboard에 표시할 공지사항 불러오기
    @GetMapping("/api/home/notice")
    public ResponseEntity getNotice(){

        //공지사항 요약 불러오기
        List<NoticeDto> noticeDto = homeServiceImpl.getNoticeList();

        return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
    }
}
