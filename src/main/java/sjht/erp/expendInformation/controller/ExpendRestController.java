package sjht.erp.expendInformation.controller;

import org.springframework.web.bind.annotation.*;
import sjht.erp.expendInformation.dto.ParameterEIDto;
import sjht.erp.expendInformation.dto.ResultEIDto;
import sjht.erp.expendInformation.service.ExpendInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpendRestController {

    private final ExpendInformationService service;

    // parameterEIDto(입력 조건 dto)를 통해 조건에 맞는 리스트를 반환
    @PostMapping("/api/param")
    public ResponseEntity<List<ResultEIDto>> expendParam(@RequestBody HashMap<String, String> map){
        ParameterEIDto parameterEIDto = service.checkParam(map);
        List<ResultEIDto> result = service.selectEIByCondition(parameterEIDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping("/api/detail")
    public ResponseEntity<List<ResultEIDto>> expendOne(@RequestBody HashMap<String, String> map){
        List<ResultEIDto> result = service.selectEIByDvnoOne(map.get("dvno"));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }



}