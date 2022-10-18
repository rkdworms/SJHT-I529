package sjht.erp.common.vo;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {
    private int empno; //사원 번호
    private String password; //비밀번호
    private BankCodeVO bankcd; // 은행 코드
    private DepartmentCodeVO departmentcd; // 부서 코드
    private GradeCodeVO gradecd; // 직급 코드
    private UserMasterVO usertype; // 접근 권한 코드
    private String name; // 사원 이름
    private String gender; // 성별
    private String mail; // 이메일
    private String tel; // 전화번호
    private String zipcode; // 우편번호
    private String addr; // 주소
    private String addrdetail; // 상세 주소
    private String regno; //주민등록번호
    private String birthday; //생년월일
    private String school; //학력
    private LocalDateTime entrydate; //입사 일자
    private LocalDateTime retiredate; //퇴사 일자
    private int yearsalary; // 연봉
    private int salary; //월급 = (연봉/12)
    private int retirementpay; //퇴직금
    private String account; // 계좌번호
    private String memo; //비고
    private String retireyn; // 퇴직여부 -- y -> 퇴직 n -> 퇴직x
    private String restyn; // 휴직여부
    private int privateinsurance; // 4대보험
    private String paymentst; // 지급여부 --
    private int totalal; // 총연차
    private int useal; // 사용연차
    private String retirenote; //퇴사 사유
}
