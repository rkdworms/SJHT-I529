package sjht.erp.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import sjht.erp.board.dto.NoticeResponse;
import sjht.erp.board.service.NoticeService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 게시글 리스트
    @GetMapping("/notice")
    public String noticelist() {
        return "board/noticelist";
    }

    // 게시글 상세
    @GetMapping("/noticedetail")
    public String noticedetail(@RequestParam int bno, Model model, HttpServletRequest request) {

        request.getAttribute("empNo");

        noticeService.noticeCount(bno);
        NoticeResponse response = noticeService.noticeDetail(bno);
        model.addAttribute("detail", response);
        return "board/noticedetail";
    }

    // 게시글 작성
    @GetMapping("/noticewrite")
    public String noticewrite() {
        return "board/noticewrite";
    }

   // 게시글 수정
    @GetMapping("/noticemodify/{bno}")
    public String noticemodify(@PathVariable("bno") int bno,
                              Model model, HttpServletRequest request) {
        request.getAttribute("empNo");
        NoticeResponse response = noticeService.noticeDetail(bno);
        model.addAttribute("detail", response);

        return "board/noticemodify"; }
}
