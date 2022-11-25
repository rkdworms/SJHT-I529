package sjht.erp.detailexpend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.UpdateMyDetailExpendRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.detailexpend.dto.response.FileResponseDto;
import sjht.erp.detailexpend.dto.response.MyExpendListResponseDto;
import sjht.erp.detailexpend.service.ExpendInsertService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@RestController
public class ExpendInsertApiController {

    @Autowired
    ExpendInsertService expendInsertService;


    // 지출결의서를 작성하기 위해서 먼저 expendInformation 테이블의 dvno를 먼저 입력해주는 컨트롤러
    @PostMapping("/api/insertDVNO")
    public String inputEXPENDDVNO(
            HttpServletRequest request
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.inputExpend();
    }

    // detailexpend 테이블에 데이터를 입력하고 난 다음, expendinformation테이블의 해당 Dvno를 입력한 데이터를 토대로 계산한 값을 업데이트 해줌
    @PostMapping("/api/updateExpendInformation")
    public boolean updateExpendInfo(HttpServletRequest request, @RequestBody HashMap<String, String> map) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        String dvno = map.get("dvnoOne");
        boolean isUpdate = expendInsertService.updateExpend(employeeDto.getEmpno(), dvno);
        return isUpdate;
    }

    // expendinformation 테이블의 dvno의 데이터를 지울때 사용한다. 지울때 Detailexpend 테이블의 데이터도 같이 지워짐
    @PostMapping("api/deleteData")
    public boolean deleteExpendData(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map

    ) {
        String dvno = map.get("dvnoOne");
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.deleteData(dvno);
    }

    // expendinformation 테이블의 dvno의 데이터를 지울때 사용한다. 지울때 Detailexpend 테이블의 데이터도 같이 지워짐
    @PostMapping("api/deleteDetailData")
    public boolean deleteExpendDetailData(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        String dvno = map.get("dvnoOne");
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.deleteDetailData(dvno);
    }

    // detailexpend 테이블의 dno의 데이터를 지우고 싶을 때 사용함
    @PostMapping("api/deleteDetailDataOne")
    public boolean deleteExpendDetailDataOne(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        int dno = Integer.parseInt(map.get("dnum"));
        return expendInsertService.deleteOneData(dno);
    }

    // 로그인한 대상의 부서정보를 출력하기 위해 사용하는 컨트롤러
    @PostMapping("api/getDepartment")
    public String getDepartmentName(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.getDepartment(employeeDto.getEmpno());
    }

    // expendinformtaion 정보를 리스트로 출력하기 위해서 사용하는 컨트롤러
    @PostMapping("api/showMyExpendList")
    public List<MyExpendListResponseDto> getMyExpendList(
            HttpServletRequest request
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.selectExpendInfo(employeeDto.getEmpno());
    }


    // detailexpend 테이블을 수정하기 위해 사용하는 컨트롤러 기존의 데이터를 화면상에 뿌려주기 위해 사용함
    @PostMapping("api/chooseMyDetailExpend")
    public DetailResponseDto update(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        int dno = Integer.parseInt(map.get("dno"));//그리스 물음표
        return expendInsertService.chooseDetailExpend(dno);
    }

    // detailexpend테이블의 데이터를 입력해주고 수정하기 버튼을 클릭시 수정하는 컨트롤러
    @PostMapping("api/updateMyDetailExpend")
    public boolean updateMyDetailExpend(
            HttpServletRequest request,
            @RequestBody UpdateMyDetailExpendRequestDto updateMyDetailExpendRequestDto
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.updateMyDetailExpend(updateMyDetailExpendRequestDto);
    }


    // detailexpend정보를 리스트로 출력하기 위해서 사용하는 컨트롤러
    @PostMapping("api/showMyDetailExpendList")
    public List<DetailResponseDto> showListDetail(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        String dvno = map.get("dvnoOne");
        return expendInsertService.selectDetailExpend(dvno);
    }

    // 지출결의서를 입력해줄때 하나의 dvno에 여러개의 detailexpend 데이터가 들어갈수 있음
    @PostMapping("api/insertDetail")
    public int inputDETAIL(
            HttpServletRequest request,
            @RequestBody InsertRequestDto insertRequestDto
    ) {
        System.out.println(insertRequestDto);
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.inputDetail(insertRequestDto);
    }

    @PostMapping("api/selectFile")
    public List<FileResponseDto> selectFile(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        String dvno = map.get("dvno");
        return expendInsertService.selectFile(dvno);
    }

    @PostMapping("api/selectOneFile")
    public FileResponseDto selectOneFile(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        int dno = Integer.parseInt(map.get("dno"));
        return expendInsertService.selectOneFile(dno);
    }

    @PostMapping("api/downloadFile")
    public ResponseEntity<Object> downloadFile(
            @RequestBody HashMap<String, String> map,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        String physicalpath = map.get("ps");
        String filename = map.get("fn");
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");

        try {
            Path filePath = Paths.get(physicalpath);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기

            File file = new File(physicalpath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

            return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }
    }

    // 지출결의 정보의 파일 수정
    @PostMapping("api/updateOneFile")
    public boolean updateOneFile(
            HttpServletRequest request,
            @RequestPart(name = "file") MultipartFile multipartFile,
            @RequestParam(name = "dno") int dno
    ) throws IOException {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        Path directory = Paths.get("/erp/file/expend").toAbsolutePath().normalize();
        Files.createDirectories(directory);
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(filename);
        Path targetPath = directory.resolve(filename).normalize();
        if (expendInsertService.updateOneFile(multipartFile, targetPath, dno)) {
            multipartFile.transferTo(targetPath);
            return true;
        }
        return false;
    }

    // 파일 업로드 하는 컨트롤러
    @PostMapping("api/uploadFile")
    public boolean uploadFile(
            HttpServletRequest request,
            @RequestPart(name = "file") MultipartFile multipartFile,
            @RequestParam(name = "dvno") String dvno
    ) throws IOException {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        int empno = employeeDto.getEmpno();
        Path directory = Paths.get("/erp/file/expend").toAbsolutePath().normalize();
        Files.createDirectories(directory);
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(filename);
        Path targetPath = directory.resolve(filename).normalize();
        System.out.println(targetPath.toAbsolutePath());
        if (expendInsertService.fileInput(multipartFile, empno, dvno, targetPath)) {
            multipartFile.transferTo(targetPath);
            return true;
        }
        return false;
    }
}
