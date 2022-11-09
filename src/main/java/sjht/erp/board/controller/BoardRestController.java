package sjht.erp.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sjht.erp.board.dto.BoardRequest;
import sjht.erp.board.dto.BoardResponse;
import sjht.erp.board.service.BoardService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    // 자유게시판 리스트
    @PostMapping("/api/boardlist")
    public ResponseEntity<List<BoardResponse>> boardList(@RequestBody HashMap<String, String> boardtype) {

        List<BoardResponse> response = boardService.boardList(boardtype.get("boardtype"));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 자유게시판 검색 결과 리스트
    @PostMapping("/api/searchboardlist")
    public ResponseEntity<List<BoardResponse>> boardSearchList(@RequestBody HashMap<String, String> search){

        List<BoardResponse> response = boardService.searchBoardList(search);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    // 자유게시판 작성
    @PostMapping("/api/boardinsert")
    public boolean boardInsert(@RequestBody HashMap<String, String> map,
                              HttpServletRequest request) {
        String title = map.get("title");
        String content = map.get("content");
        BoardRequest boardRequest = new BoardRequest();
        boardRequest.setTitle(title);
        boardRequest.setContent(content);
        EmployeeDto empDto = (EmployeeDto) request.getAttribute("empNo");
        boardRequest.setEmpno(empDto.getEmpno());

        if(boardService.boardInsert(boardRequest) != 0 ) {
            return true;
        }
            return false;
    }


    // 자유게시판 글 삭제
    @PostMapping("/api/boarddelete/{bno}")
    public boolean boardDelete(@PathVariable int bno) {
        if (boardService.boardDelete(bno) != 0){
            return true;
        }
            return false;
    }

    // 자유게시판 글 수정
    @PostMapping("/api/boardupdate")
    public boolean boardUpdate(@RequestBody BoardRequest boardRequest,
                               HttpServletRequest request) {
        request.getAttribute("empNo");
        if (boardService.boardUpdate(boardRequest) != 0 ) {
            return true;
        }
            return false;
    }

}