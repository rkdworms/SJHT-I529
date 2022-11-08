package sjht.erp.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sjht.erp.board.dto.BoardResponse;
import sjht.erp.board.service.BoardService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 리스트
    @GetMapping("/board")
    public String boardlist() {
        return "board/boardlist";
    }

    // 게시글 상세
    @GetMapping("/boarddetail")
    public String boarddetail(@RequestParam int bno, Model model, HttpServletRequest request) {
        boardService.boardCount(bno);
        request.getAttribute("empNo");
        BoardResponse response = boardService.boardDetail(bno);
        model.addAttribute("detail", response);
        return "board/boarddetail";
    }

    // 게시글 작성
    @GetMapping("/boardwrite")
    public String boardwrite() {
        return "board/boardwrite";
    }

   // 게시글 수정
    @GetMapping("/boardmodify/{bno}")
    public String boardmodify(@PathVariable("bno") int bno,
                              Model model, HttpServletRequest request) {
        request.getAttribute("empNo");
        boardService.boardCount(bno);
        BoardResponse response = boardService.boardDetail(bno);

        model.addAttribute("detail", response);

        return "board/boardmodify"; }
}
