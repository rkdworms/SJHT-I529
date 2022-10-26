package sjht.erp.expendInformation.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectResultEIDto {
    private String dvno;            //결의번호
    private int empno;              //사번
    private String name;            //이름
    private String departmentname;  //부서명
    private LocalDate dvRegDate;    //신청일자
    private String divname;         //분류이름
    private int dvamt;              //총 금액
    private String dvappyn;         //승인여부
    private String dvappname;       //승인자
    private String dvmemo;          //비고
    private LocalDate dvUseDate;    //사용일자
    private String fileno;          //파일 번호
    private String dvappdate;       //승인날짜
    private String detailuse;       //상세 내역
    private int price;              //각 금액
}
