package sjht.erp.board.dto;


import lombok.Data;

@Data
public class NoticeRequest {

    private int bno;
    private int empno;
    private String title;
    private String content;
    private String boardtype;

}
