package sjht.erp.common.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class EmployeeVO {
    private int empno;
    private String password;
    private BankCodeVO bankcd; // fk
    private DepartmentCodeVO departmentcd; // fk
    private GradeCodeVO gradecd; // fk
    private UserMasterVO usertype; // fk
    private FileVO fileno;
    private String name;
    private String gender;
    private String mail;
    private String tel;
    private String zipcode;
    private String addr;
    private String addrdetail;
    private String regno;
    private String birthday;
    private String school;
    private LocalDateTime entrydate;
    private LocalDateTime retiredate;
    private int yearsalary;
    private int salary;
    private int retirementpay;
    private String account;
    private String memo;
    private String retireyn; // 퇴직여부 -- y -> 퇴직 n -> 퇴직x
    private String restyn;
    private int privateinsurance;
    private String paymentst; // 지급여부 --
    private int totalal; // 총연차
    private int useal; // 사용연차
    private String retirenote;
}
