package sjht.erp.hrms.repository;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.common.vo.*;
import sjht.erp.detailexpend.dto.request.FileRequestDto;
import sjht.erp.hrms.dto.UpdateDto;

import java.util.List;

@Mapper
public interface HrmsMapper {

    /* 사원 리스트 */
    List<EmployeeVO> getEmpList();

    /* 사원 조회 시 증명사진 불러오기 */
    List<FileVO> getIdPicture();

    /* 사원 등록 (회계,총무) */
    void registEmp(UpdateDto updateDto);

    /* 사원 등록 시 사번 보여주기 */
    int getEmpno();

    /* 사원 정보 수정 */
    void updateEmp(UpdateDto updateDto);

    /* 부서 리스트 */
    List<DepartmentCodeVO> getDepartmentList();

    /* 직급 리스트 */
    List<GradeCodeVO> getGradeList();

    /* 은행 리스트 */
    List<BankCodeVO> getBankList();

    /* 권한 리스트 */
    List<UserMasterVO> getUserMasterList();

    /* 퇴사 처리 */
    void retireEmp(UpdateDto updateDto);

    /* 승진 및 보직 관리 - employee update */
    void promotionManageEmployee(UpdateDto updateDto);

    /* 승진 및 보직 관리 - grade insert */
    void promotionManageGrade(UpdateDto updateDto);

    /* 파일 업데이트(사원 등록)*/
    int uploadFile(FileRequestDto fileDto);

    /* 파일 업데이트(사원 수정)*/
    int updateFile(FileRequestDto fileDto);

    List<FileVO> getFile();
}
