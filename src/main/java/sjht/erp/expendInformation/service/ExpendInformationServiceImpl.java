package sjht.erp.expendInformation.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import sjht.erp.expendInformation.dto.*;
import sjht.erp.expendInformation.repository.ExpendInformationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sjht.erp.login.dto.EmployeeDto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpendInformationServiceImpl implements ExpendInformationService {
    private final ExpendInformationMapper mapper;

    /**
     * 지출 결의 조건에 따른 리스트 select
     * result type : List
     **/
    @Override
    public List<SelectResultEIDto> selectEIByCondition(SelectParameterEIDto selectParameterEIDto) {
        List<SelectResultEIDto> result = mapper.selectExpendInformation(selectParameterEIDto);
        return result;
    }

    /**
     * 결의 번호에 따른 상세보기 select
     * result type : Lits
     **/
    @Override
    public List<SelectResultEIDto> selectEIByDvno(String dvno) {
        List<SelectResultEIDto> result = mapper.selectExpendInformationOne(dvno);
        return result;
    }

    /**
     * 승인 반려에 따른 dvappyn update
     * result type : void
     **/
    @Override
    @Transactional
    public void updateEI(HashMap<String, String> map, EmployeeDto employeeDto) {

        UpdateParameterEIDto updateParameterEIDto = UpdateParameterEIDto.builder()
                .dvappdate(LocalDate.now())
                .dvappname(employeeDto.getName())
                .dvno(map.get("dvno"))
                .dvappyn(map.get("dvappyn"))
                .build();

        //for문 돌릴떄 list.size() 파라미터 값 dto
        mapper.updateExpendInformation(updateParameterEIDto);

        if (updateParameterEIDto.getDvappyn().equals("y")) {
            for (int i = 0; i < selectEIByDvno(updateParameterEIDto.getDvno()).size(); i++) {
                if (map.get("dvno").equals(selectEIByDvno(updateParameterEIDto.getDvno()).get(i).getDvno())) {
                    return;
                }
                else{
                    InsertParameterEIDto insertParameterEIDto = InsertParameterEIDto.builder()
                            .empno(selectEIByDvno(updateParameterEIDto.getDvno()).get(i).getEmpno())
                            .dvamt(selectEIByDvno(updateParameterEIDto.getDvno()).get(i).getDvamt())
                            .dvappname(employeeDto.getName())
                            .dvno(selectEIByDvno(updateParameterEIDto.getDvno()).get(i).getDvno())
                            .dvappdate(LocalDate.parse(selectEIByDvno(updateParameterEIDto.getDvno()).get(i).getDvappdate()))
                            .build();

                    insertEH(insertParameterEIDto);
                }
            }
        }
    }

    @Override
    public void insertEH(InsertParameterEIDto insertParameterEIDto) {
        mapper.insertExpendHistory(insertParameterEIDto);
    }

    /**
     * 조건에 따른 디폴트 값 설정
     * result type : SelectParameterEIDto
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

    /**
     * dvno에 따른 파일 데이터 select
     * result type : SelectResultFileDto
     */
    @Override
    public HashMap<String, Object> selectFile(String dvno) {
        List<SelectResultFileEIDto> result = mapper.selectFile(dvno);
        String path = result.get(0).getPhysicalpath();
        String filename = result.get(0).getFilename();

        try {
            Path filePath = Paths.get(path);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath));

            File file = new File(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("filename", filename);
            map.put("resource", resource);
            map.put("headers", headers);
            return map;
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("filename", filename);
            map.put("headers", null);
            return map;
        }
    }



}
