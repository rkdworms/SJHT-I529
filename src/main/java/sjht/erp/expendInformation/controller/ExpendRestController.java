package sjht.erp.expendInformation.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import sjht.erp.expendInformation.dto.SelectParameterEIDto;
import sjht.erp.expendInformation.dto.SelectResultEIDto;
import sjht.erp.expendInformation.dto.SelectResultFileEIDto;
import sjht.erp.expendInformation.dto.UpdateParameterEIDto;
import sjht.erp.expendInformation.service.ExpendInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpendRestController {

    private final ExpendInformationService service;

    @PostMapping("/api/usertype")
    public String getUserType(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return employeeDto.getUsertype();
    }

    // parameterEIDto(입력 조건 dto)를 통해 조건에 맞는 리스트를 반환
    @PostMapping("/api/paramExpendInformation")
    public ResponseEntity<List<SelectResultEIDto>> expendParam(@RequestBody HashMap<String, String> map) {
        SelectParameterEIDto selectParameterEIDto = service.checkParam(map);
        List<SelectResultEIDto> result = service.selectEIByCondition(selectParameterEIDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 결의 번호 (dvno)에 따른 상세 내역을 반환
    @RequestMapping("/api/detailExpendInformation")
    public ResponseEntity<List<SelectResultEIDto>> expendOne(@RequestBody HashMap<String, String> map) {
        List<SelectResultEIDto> result = service.selectEIByDvno(map.get("dvno"));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 임원만 가능 / 승인 반려에 따른 데이터 업데이트
    @PostMapping("/api/updateEI")
    public void updateDvappyn(@RequestBody HashMap<String,String> map, HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        service.updateEI(map, employeeDto);
    }

    @PostMapping("/api/download")
    public ResponseEntity<Object> downloadFile(@RequestBody HashMap<String, String> map) {
        HashMap<String, Object> objectHashMap = service.selectFile(map.get("dvno"));
        System.out.println("objectHashMap = " + objectHashMap);
        System.out.println("objectHashMap.get(\"filename\") = " + objectHashMap.get("filename"));
        ResponseEntity<Object> result;
        Object resource = objectHashMap.get("resource");
        if (resource != null) {
            HttpHeaders headers = (HttpHeaders) objectHashMap.get("headers");
            result = new ResponseEntity<>(resource, headers, HttpStatus.OK);
            System.out.println("result = " + result);
        } else {
            result = new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }
        return result;
    }

    @PostMapping("/api/getFilename")
    public ResponseEntity<List<SelectResultFileEIDto>> getFilename(@RequestBody HashMap<String, String> map){
        HashMap<String, Object> objectHashMap = service.selectFile(map.get("dvno"));
        List list = new ArrayList();
        Object filename = objectHashMap.get("filename");
        list.add(filename);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
