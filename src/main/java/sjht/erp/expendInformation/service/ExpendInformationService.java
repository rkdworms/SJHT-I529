package sjht.erp.expendInformation.service;

import sjht.erp.expendInformation.dto.ParameterEIDto;
import sjht.erp.expendInformation.dto.ResultEIDto;

import java.util.HashMap;
import java.util.List;


public interface ExpendInformationService {

    //지출 결의 조건에 맞는 리스트 select 메소드
    List<ResultEIDto> selectEIByCondition(ParameterEIDto parameterEIDto);

    List<ResultEIDto> selectEIByDvnoOne(String dvno);

    Long updateEI();

    ParameterEIDto checkParam(HashMap<String,String> map);
}
