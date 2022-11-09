package sjht.erp.board.dto;


import lombok.Data;

@Data
public class NoticeRequest {

    private int bno;            // 번호(PK)
    private int empno;          // 사원번호(FK)
    private String title;       // 제목
    private String content;     // 내용
    private String boardtype;   // 게시판 타입

}
