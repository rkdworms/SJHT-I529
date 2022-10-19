package sjht.erp.expendInformation.service;

import sjht.erp.expendInformation.dto.ParameterEIDto;
import sjht.erp.expendInformation.dto.ResultEIDto;
import sjht.erp.expendInformation.repository.ExpendInformationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpendInformationServiceImpl implements ExpendInformationService {
    private final ExpendInformationMapper mapper;

    //지출 결의 조건에 맞는 리스트 select 메소드
    @Override
    public List<ResultEIDto> selectEIByCondition(ParameterEIDto parameterEIDto) {
        List<ResultEIDto> result = mapper.selectExpendInformation(parameterEIDto);
        return result;
    }

    @Override
    public List<ResultEIDto> selectEIByDvnoOne(String dvno) {
        List<ResultEIDto> result = mapper.selectExpendInformationOne(dvno);
        return result;
    }

    @Override
    public Long updateEI() {
        Long result = mapper.updateExpendInformation();
        return result;
    }

    @Override
    public ParameterEIDto checkParam(HashMap<String, String> map) {
        int count = 0;

        for (String i : map.values()) {
            if (i.isEmpty())
                count++;
        }

        if (count == map.size()) {
            return new ParameterEIDto();
        } else
            return new ParameterEIDto(map);
    }
}
