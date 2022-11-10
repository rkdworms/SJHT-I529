package sjht.erp.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeResponse {

    private int bno;                    // 번호(PK)
    private int empno;                  // 사원번호(FK)
    private String title;               // 제목
    private String content;             // 내용
    private String name;                // 이름
    private LocalDateTime createtime;   // 작성시간
    private String boardtype;           // 게시판 타입
    private int boardcount;             // 조회수

}
