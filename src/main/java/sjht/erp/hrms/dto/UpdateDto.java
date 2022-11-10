package sjht.erp.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {

    /* Employee */
    private int empno;  // pk 사원번호
    private String password;  // 비밀번호
    private String bankcd;  // fk 은행코드
    private String departmentcd; // fk 부서코드
    private String gradecd; // fk 직급코드
    private String usertype; // fk 권한코드
    private String name; // 사원이름
    private String gender; // 성별
    private String mail; // 이메일
    private String tel; // 전화번호
    private String zipcode; // 우편번호
    private String addr; // 주소
    private String addrdetail; // 상세주소
    private String regno; // 주민둥록번호
    private String school; // 학력
    private String entrydate; // 입사일자
    private String retiredate; // 퇴사일자
    private int yearsalary; // 연봉
    private int salary; // 월급
    private int retirementpay; // 퇴직금
    private String account; // 계좌번호
    private String memo; // 비고
    private String retireyn; // 퇴직여부 -- y -> 퇴직 n -> 퇴직x
    private String restyn; // 휴직여부
    private int privateinsurance; // 보험
    private Date paymentst; // 월급 지급 여부
    private int totalal; // 총연차
    private int useal; // 사용연차
    private String retirenote; // 퇴사사유

    /* File */
    private int fileno; // pk 파일번호
    private String filename; // 파일이름
    private String physicalpath; // 물리경로
    private String relatedpath; // 상대경로
    private int filesize; // 파일사이즈
    private String kind; // 파일종류
    private int dno; // 결제번호

    /* Grade */
    private int gradesequence; // pk 승진 번호
    private Date promotiondate; // 승진날짜
    private String prevgradecd; // 변경전 직급코드
    private String prevdepartmentcd; // 변경전 부서코드
    private int prevyearsalary; // 변경전 연봉
    private int appempno; // 승인 담당자 사번
}
