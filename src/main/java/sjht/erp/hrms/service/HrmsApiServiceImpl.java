package sjht.erp.hrms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sjht.erp.common.vo.DepartmentCodeVO;
import sjht.erp.common.vo.EmployeeVO;
import sjht.erp.hrms.dto.HrmsDto;
import sjht.erp.hrms.dto.EmpInfoDto;
import sjht.erp.hrms.repository.HrmsMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HrmsApiServiceImpl implements HrmsApiService {

    private final HrmsMapper hrmsMapper;
    private final PasswordEncoder passwordEncoder;

    /* 사원 리스트 */
    @Override
    public List<HrmsDto> getEmpList() {
        // 사원 리스트 VO로 가져오기
        // VO -> DTO
        List<HrmsDto> hrmsDtoList = hrmsMapper.getEmpList()
                .stream()
                .map(employeeVOList -> new HrmsDto(employeeVOList))
                .collect(Collectors.toList());
        // 반환
        return hrmsDtoList;
    }

    /* 사원 등록 (회계,총무) */
    @Override
    public void registEmp(EmpInfoDto empInfoDto) {

        // 비밀 번호 등록시 인코딩
        empInfoDto.setPassword(passwordEncoder.encode(empInfoDto.getPassword()));

        empInfoDto.setEntrydate(LocalDateTime.now());
        empInfoDto.setRetiredate(null);
        // 비밀 번호는 핸드폰 뒷자리 4자리로 들어가게 하기

        // DB로 저장
        hrmsMapper.registEmp(empInfoDto);


    }

    /* 사원 정보 수정 */
    @Override
    public void updateEmp(EmpInfoDto empInfoDto) {

        // 널 처리 해주기

        // DB로 갱신
        hrmsMapper.updateEmp(empInfoDto);
    }

    /* 부서 리스트 */
    @Override
    public List<HrmsDto> getDepartmentList() {
        // 부서 리스트 불러오기
        // VO -> DTO
        List<HrmsDto> hrmsDtoList = hrmsMapper.getDepartmentList().stream()
                .map(departmentList -> new HrmsDto(departmentList))
                .collect(Collectors.toList());
        // 반환
        return hrmsDtoList;
    }

    /* 직급 리스트 */
    @Override
    public List<HrmsDto> getGradeList() {
        // 직급 리스트 불러오기
        // VO -> DTO
        List<HrmsDto> hrmsDtoList = hrmsMapper.getGradeList()
                .stream()
                .map(gradeList -> new HrmsDto(gradeList))
                .collect(Collectors.toList());
        // 반환
        return hrmsDtoList;
    }



}
