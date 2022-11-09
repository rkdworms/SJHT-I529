package sjht.erp.detailexpend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.UpdateMyDetailExpendRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.detailexpend.dto.response.MyExpendListResponseDto;
import sjht.erp.detailexpend.service.ExpendInsertService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    // 파일 업로드 하는 컨트롤러
    @PostMapping("api/uploadFile")
    public boolean uploadFile(
            HttpServletRequest request,
            @RequestPart(name = "file") MultipartFile multipartFile,
            @RequestParam(name = "dvno") String dvno
    ) throws IOException {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        int empno = employeeDto.getEmpno();
        return expendInsertService.fileInput(multipartFile, empno, dvno);
    }

    // detailexpend정보를 리스트로 출력하기 위해서 사용하는 컨트롤러
    @PostMapping("api/showMyDetailExpendList")
    public List<DetailResponseDto> showListDetail(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        String dvno = map.get("dvnoOne");
        System.out.println(dvno);
        return expendInsertService.selectDetailExpend(dvno);
    }

    // 지출결의서를 입력해줄때 하나의 dvno에 여러개의 detailexpend 데이터가 들어갈수 있음
    @PostMapping("/api/insertDetail")
    public boolean inputDETAIL(
            HttpServletRequest request,
            @RequestBody InsertRequestDto insertRequestDto
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.inputDetail(insertRequestDto);
    }
}
