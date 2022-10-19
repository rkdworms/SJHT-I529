package sjht.erp.hrms.repository;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.common.vo.DepartmentCodeVO;
import sjht.erp.common.vo.EmployeeVO;
import sjht.erp.common.vo.GradeCodeVO;
import sjht.erp.hrms.dto.EmpInfoDto;
import sjht.erp.hrms.dto.HrmsDto;

import java.util.List;

@Mapper
public interface HrmsMapper {

    /* 사원 리스트 */
    List<EmployeeVO> getEmpList();

    /* 사원 등록 (회계,총무) */
    void registEmp(EmpInfoDto empInfoDto);

    /* 사원 정보 수정 */
    void updateEmp(EmpInfoDto empInfoDto);

    /* 부서 리스트 */
    List<DepartmentCodeVO> getDepartmentList();

    /* 직급 리스트 */
    List<GradeCodeVO> getGradeList();


}
