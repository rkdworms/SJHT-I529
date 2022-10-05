package sjht.erp.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpendHistoryVO {
    private int eno; // 지출내역 번호 pk
    private ExpendInformationVO dvno; // fk
    private PayRollHistoryVO paysequence; // fk
    private AccountCodeVO divcd; // 대분류 코드 fk
    private EmployeeVO empno; // fk
    private int dvamt;
    private LocalDateTime edate;
}
