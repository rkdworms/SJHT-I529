package sjht.erp.hrms.service;

import org.springframework.web.multipart.MultipartFile;
import sjht.erp.hrms.dto.SelectDto;
import sjht.erp.hrms.dto.UpdateDto;

import java.io.IOException;
import java.util.List;

public interface HrmsApiService {

    /* 사원 리스트 */
    List<SelectDto> getEmpList();

    /* 사원 등록 (회계,총무) */
    void registEmp(UpdateDto updateDto, MultipartFile[] file) throws IOException;

    /* 사원 등록 시 사번 보여주기 */
    int getEmpno();

    /* 사원 정보 업데이트 */
    void updateEmp(UpdateDto updateDto, MultipartFile[] file) throws IOException;

    /* 부서 리스트 */
    List<SelectDto> getDepartmentList();

    /* 직급 리스트 */
    List<SelectDto> getGradeList();

    /* 은행 리스트 */
    List<SelectDto> getBankList();

    /* 권한 리스트 */
    List<SelectDto> getUserMasterList();

    /* 퇴사 처리 */
    void retireEmp(UpdateDto updateDto);

    /* 승진 및 보직관리 */
    void promotionManage(UpdateDto updateDto);

}
