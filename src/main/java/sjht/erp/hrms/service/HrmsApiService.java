package sjht.erp.hrms.service;

import sjht.erp.hrms.dto.HrmsDto;
import sjht.erp.hrms.dto.EmpInfoDto;

import java.util.List;

public interface HrmsApiService {

    /* 사원 리스트 */
    List<HrmsDto> getEmpList();

    /* 사원 등록 (회계,총무) */
    void registEmp(EmpInfoDto empInfoDto);

    /* 사원 정보 업데이트 */
    void updateEmp(EmpInfoDto empInfoDto);

    /* 부서 리스트 */
    List<HrmsDto> getDepartmentList();

    /* 직급 리스트 */
    List<HrmsDto> getGradeList();

}
