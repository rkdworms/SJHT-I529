package sjht.erp.detailexpend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.SelectEmployeeDto;
import sjht.erp.detailexpend.dto.request.UpdateRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.detailexpend.repository.ExpendInsertMapper;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class ExpendInsertServiceImpl implements ExpendInsertService {
    @Autowired
    ExpendInsertMapper expendInsertMapper;

    private int dvamt;


    public String makeDVNO() {
        String thisYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String year = thisYear.substring(2, 4);
        int i = 1;
        String dvno = "ss" + year + "-" + i;
        if (!expendInsertMapper.selectAll().isEmpty()) {
            String[] str = expendInsertMapper.selectLastOne().split("-", 2);
            i = Integer.parseInt(str[1]) + 1;
            dvno = "ss" + year + "-" + i;
        }
        return dvno;
    }

    @Transactional
    @Override
    public String inputExpend() {
        String dvno = makeDVNO();
        if (expendInsertMapper.insertDvno(dvno) != 0) {
            return dvno;
        }
        return null;
    }

    @Transactional
    @Override
    public int inputDetail(InsertRequestDto insertRequestDto/*, MultipartFile multipartFile*/) {
        int i = expendInsertMapper.insertExpend(insertRequestDto);
        if (i >= 0) {
            // 파일입력부분이 들어가야 된다.
            return i;
        }
        return -1;
    }

    @Override
    public List<DetailResponseDto> selectDetailExpend(String dvno) {
        List<DetailResponseDto> detailResponseDtoList = expendInsertMapper.selectDetailExpend(dvno);
        if (!detailResponseDtoList.isEmpty()) {
            return detailResponseDtoList;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean updateExpend(int empno, String dvno) {
        UpdateRequestDto updateRequestDto = new UpdateRequestDto();
        SelectEmployeeDto selectEmployeeDto = expendInsertMapper.selectOneEmployee(empno);
        List<DetailResponseDto> detailResponseDtoList = expendInsertMapper.selectDetailExpend(dvno);
        if (detailResponseDtoList != null) {
            updateRequestDto.setEmpno(selectEmployeeDto.getEmpno());
            updateRequestDto.setDepartmentcd(selectEmployeeDto.getDepartmentcd());
            updateRequestDto.setDvregdate(LocalDate.now());
            updateRequestDto.setDvno(dvno);
        }
        return expendInsertMapper.updateExpendInformation(updateRequestDto) != 0;
    }

    @Transactional
    @Override
    public boolean deleteDetailData(String dvno) {
        return expendInsertMapper.deleteDetailExpend(dvno) != 0;
    }

    @Override
    public boolean deleteData(String dvno) {
        return expendInsertMapper.deleteExpendInformation(dvno) != 0;
    }

    @Override
    public String getDepartment(int empno){
        String departmentcd = expendInsertMapper.selectOneEmployee(empno).getDepartmentcd();
        return expendInsertMapper.selectOneDepartmentName(departmentcd);
    }
}
