package sjht.erp.expendInformation.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultEIDto {
    private String dvno; //결의번호
    private int empno;  //사번
    private String name; //이름
    private String departmentname;  //부서명
    private LocalDate dvRegDate;    //신청일자
    private String divname;         //분류이름
    private int dvamt;              //금액
    private String dvappyn;         //승인여부
    private String dvappname;       //승인자
    private String dvmemo;          //비고
    private String dvUseDate;       //사용일자
    private String fileno;          //파일 번호

}
