package sjht.erp.expendInformation.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectResultFileEIDto {
    private String dvno;                //결의번호
    private int fileno;                 //파일 번호
    private String filename;            //파일 이름
    private String physicalpath;        //절대 경로
    private int filesize;               //파일 사이즈
    private int kind;                   //파일 종류
    private String relatedpath;         //상대 경로
    private int dno;                    //결의 번호에 따른 세부 파일 번호
}
