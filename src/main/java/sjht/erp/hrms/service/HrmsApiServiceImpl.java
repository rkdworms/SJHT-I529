package sjht.erp.hrms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sjht.erp.common.vo.EmployeeVO;
import sjht.erp.common.vo.FileVO;
import sjht.erp.detailexpend.dto.request.FileRequestDto;
import sjht.erp.hrms.dto.SelectDto;
import sjht.erp.hrms.dto.UpdateDto;
import sjht.erp.hrms.repository.HrmsMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HrmsApiServiceImpl implements HrmsApiService {

    private final HrmsMapper hrmsMapper;
    private final PasswordEncoder passwordEncoder;

    /* 사원 리스트 */
    @Override
    public List<SelectDto> getEmpList() {
        // 사원 리스트 VO로 가져오기
        // VO -> DTO
//        List<SelectDto> selectDtoList = hrmsMapper.getEmpList()
//                .stream()
//                .map(employeeVOList -> new SelectDto(employeeVOList))
//                .collect(Collectors.toList());
//        // 반환
//        return selectDtoList;






//        // 사원 리스트 VO로 가져오기
//        List<EmployeeVO> employeeVOList = hrmsMapper.getEmpList();
//        // 사원 사진 VO로 가져오기
//        List<FileVO> fileVOList = hrmsMapper.getIdPicture();
//        // empno이 같은 자료 추출하기
//        // VO -> DTO
//        List<SelectDto> selectDtoList =  employeeVOList.stream().filter(emp -> fileVOList.stream().anyMatch(file -> emp.getEmpno() == file.getEmpno() ))
//                .map( (employeeVO, fileVO) -> new SelectDto(employeeVO, fileVO))
//                .collect(Collectors.toList());
//        // 반환
//        return selectDtoList;




        // 사원 리스트 VO로 가져오기
        List<EmployeeVO> employeeVOList = hrmsMapper.getEmpList();

        // 사원 사진 VO로 가져오기
        List<FileVO> fileVOList = hrmsMapper.getIdPicture();
        // empno가 같은 자료 추출하기
        // VO -> DTO
        List<SelectDto> selectDtoList = new ArrayList<SelectDto>();

        for (int i=0; i<employeeVOList.size(); i++) {
           if ( employeeVOList.get(i).getEmpno() == fileVOList.get(i).getEmpno() ) {
               selectDtoList.add(new SelectDto(employeeVOList.get(i), fileVOList.get(i)));
           }
        }
        // 반환
        return selectDtoList;
    }

    /* 사원 등록 (회계,총무) */
    @Override
    public void registEmp(UpdateDto updateDto, MultipartFile[] file) throws IOException {
        // 비밀 번호 등록시 인코딩
        updateDto.setPassword(passwordEncoder.encode(updateDto.getPassword()));

        //사원 정보 등록
        hrmsMapper.registEmp(updateDto);

        // 이미지 파일 저장할 경로(없으면 생성)
        Path directory = Paths.get("src/main/resources/static/images").toAbsolutePath().normalize();
        Files.createDirectories(directory);

        String filename = StringUtils.cleanPath(Objects.requireNonNull(file[0].getOriginalFilename()));

        String substring = filename.substring(filename.length() - 4);

        Path targetPath = directory.resolve(updateDto.getEmpno()+ substring).normalize();

        FileRequestDto fileDto = new FileRequestDto(
                updateDto.getEmpno()+ substring,
                targetPath.toString(),
                Math.toIntExact(file[0].getSize()),
                updateDto.getEmpno(),
                2,
                "/images/",
                0
        );

        //증명사진 업로드
        if(hrmsMapper.uploadFile(fileDto) != 0){
            file[0].transferTo(targetPath);
        }
    }

    /* 사원 등록 시 사번 보여주기 */
    public int getEmpno() {
        // 사번 요청 및 반환
        return hrmsMapper.getEmpno();
    }

    /* 사원 정보 수정 */
    @Override
    public void updateEmp(UpdateDto updateDto, MultipartFile[] file) throws IOException  {

        if(file[0] != null){
            // 이미지 파일 저장할 경로(없으면 생성)
            Path directory = Paths.get("src/main/resources/static/images").toAbsolutePath().normalize();
            Files.createDirectories(directory);

            String filename = StringUtils.cleanPath(Objects.requireNonNull(file[0].getOriginalFilename()));

            String substring = filename.substring(filename.length() - 4);

            Path targetPath = directory.resolve(updateDto.getEmpno()+ substring).normalize();

            FileRequestDto fileDto = new FileRequestDto(
                    updateDto.getEmpno()+ substring,
                    targetPath.toString(),
                    Math.toIntExact(file[0].getSize()),
                    updateDto.getEmpno(),
                    2,
                    "/images/",
                    0
            );

            if(hrmsMapper.updateFile(fileDto) != 0){
                file[0].transferTo(targetPath);
            }
        }
        System.out.println(updateDto.getSchool());
        // DB로 갱신
        hrmsMapper.updateEmp(updateDto);


    }

    /* 부서 리스트 */
    @Override
    public List<SelectDto> getDepartmentList() {
        // 부서 리스트 불러오기
        // VO -> DTO
        List<SelectDto> selectDtoList = hrmsMapper.getDepartmentList()
                .stream()
                .map(departmentList -> new SelectDto(departmentList))
                .collect(Collectors.toList());
        // 반환
        return selectDtoList;
    }

    /* 직급 리스트 */
    @Override
    public List<SelectDto> getGradeList() {
        // 직급 리스트 불러오기
        // VO -> DTO
        List<SelectDto> selectDtoList = hrmsMapper.getGradeList()
                .stream()
                .map(gradeList -> new SelectDto(gradeList))
                .collect(Collectors.toList());
        // 반환
        return selectDtoList;
    }

    /* 은행 리스트 */
    @Override
    public List<SelectDto> getBankList() {
        // 은행 리스트 불러오기
        // VO -> DTO
        List<SelectDto> selectDtoList = hrmsMapper.getBankList()
                .stream()
                .map(bankList -> new SelectDto(bankList))
                .collect(Collectors.toList());
        // 반환
        return selectDtoList;
    }

    /* 권한 리스트 */
    @Override
    public List<SelectDto> getUserMasterList() {
        // 권한 리스트 불러오기
        // VO -> DTO
        List<SelectDto> selectDtoList = hrmsMapper.getUserMasterList()
                .stream()
                .map(userMasterList -> new SelectDto(userMasterList))
                .collect(Collectors.toList());
        // 반환
        return selectDtoList;
    }

    /* 퇴사 처리 */
    @Override
    public void retireEmp(UpdateDto updateDto) {
        // 사원 퇴사 처리 요청
        hrmsMapper.retireEmp(updateDto);
    }

    /* 승진 및 보직 관리 */
    @Override
    public void promotionManage(UpdateDto updateDto) {
        // 사원 직급 및 부서 변경 요청
        System.out.println("냥"+updateDto.getAppempno());
        hrmsMapper.promotionManageEmployee(updateDto);

        hrmsMapper.promotionManageGrade(updateDto);
    }
}
