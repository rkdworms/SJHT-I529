package sjht.erp.expendInformation.service;

import org.springframework.transaction.annotation.Transactional;
import sjht.erp.expendInformation.dto.SelectParameterEIDto;
import sjht.erp.expendInformation.dto.SelectResultEIDto;
import sjht.erp.expendInformation.dto.UpdateParameterEIDto;
import sjht.erp.expendInformation.repository.ExpendInformationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sjht.erp.login.dto.EmployeeDto;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpendInformationServiceImpl implements ExpendInformationService {
    private final ExpendInformationMapper mapper;

    /**
     * 지출 결의 조건에 맞는 리스트 select 메소드
     **/
    @Override
    public List<SelectResultEIDto> selectEIByCondition(SelectParameterEIDto selectParameterEIDto) {
        List<SelectResultEIDto> result = mapper.selectExpendInformation(selectParameterEIDto);
        return result;
    }

    /**
     * 상세보기 dto select
     **/
    @Override
    public List<SelectResultEIDto> selectEIByDvnoOne(String dvno) {
        List<SelectResultEIDto> result = mapper.selectExpendInformationOne(dvno);
        return result;
    }

    /**
     * 승인 반려 업데이트
     **/
    @Override
    @Transactional
    public void updateEI(UpdateParameterEIDto updateParameterEIDto) {
        System.out.println("updateEI before");
        System.out.println("updateParameterEIDto = " + updateParameterEIDto);
        //for문 돌릴떄 list.size() 파라미터 값 dto
        mapper.updateExpendInformation(updateParameterEIDto);

        if(updateParameterEIDto.getDvappyn().equals("y")){
            System.out.println("insert before");

            System.out.println(selectEIByDvnoOne(updateParameterEIDto.getDvno()));
            //insertEH(updateParameterEIDto.getDvno());

            System.out.println("insert after");
        }

        System.out.println("updateEI after");
    }

//    @Override
//    public void insertEH(String dvno) {
//
//        mapper.insertExpendHistory();
//    }

    /**
     * 조건에 따른 디폴트 값 설정
     **/
    @Override
    public SelectParameterEIDto checkParam(HashMap<String, String> map) {
        int count = 0;

        for (String i : map.values()) {
            if (i.isEmpty())
                count++;
        }

        if (count == map.size()) {
            return new SelectParameterEIDto();
        } else
            return new SelectParameterEIDto(map);
    }
}
