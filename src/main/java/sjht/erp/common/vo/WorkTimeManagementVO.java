package sjht.erp.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkTimeManagementVO {
    private int tano;
    private EmployeeVO empno;
    private String approver;
    private String approveryn;
    private LocalDateTime registdate;
    private String kindholiday;
    private String reasonholiday;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private int holidaycnt;
    private String canreason;

}
