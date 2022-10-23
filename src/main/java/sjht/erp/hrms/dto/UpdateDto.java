package sjht.erp.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {

    /* Employee */
    private String password;
    private String bankcd; // fk
    private String departmentcd; // fk
    private String gradecd; // fk
    private String usertype; // fk
    private int fileno;
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
    private String entrydate;
    private String retiredate;
    private int yearsalary;
    private int salary;
    private int retirementpay;
    private String account;
    private String memo;
    private String retireyn; // 퇴직여부 -- y -> 퇴직 n -> 퇴직x
    private String restyn; // 휴직여부
    private int privateinsurance; // 보험
    private String paymentst; // 지급여부 --
    private int totalal; // 총연차
    private int useal; // 사용연차
    private String retirenote; // 퇴사사유

    /* File */
    private String filename;
    private String filepath;
    private int filesize;


}
