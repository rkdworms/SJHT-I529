package sjht.erp.common.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExpendInformationVO {
    private String dvno;
    private EmployeeVO empno;
    private AccountCodeVO divcd;
    private FileVO fileno;
    private LocalDateTime dvregdate;
    private LocalDateTime dvusedate;
    private int dvamt;
    private String dvappyn;
    private LocalDateTime dvappdate;
    private String dvappname;
    private String dvmemo;
    private String dvreturn;
}
