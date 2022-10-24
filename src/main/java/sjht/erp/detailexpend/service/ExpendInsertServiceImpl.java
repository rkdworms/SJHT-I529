package sjht.erp.detailexpend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sjht.erp.detailexpend.dto.request.FileRequestDto;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.SelectEmployeeDto;
import sjht.erp.detailexpend.dto.request.UpdateRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.detailexpend.repository.ExpendInsertMapper;
import sjht.erp.login.dto.EmployeeDto;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class ExpendInsertServiceImpl implements ExpendInsertService {
    @Autowired
    ExpendInsertMapper expendInsertMapper;

    private int dno;
    private List<DetailResponseDto> detailExpends;

    @Transactional
    @Override
    public boolean insertExpend(InsertRequestDto insertRequestDto) {
        boolean isInsert = false;
        expendInsertMapper.insertDvno(makeDVNO());
        insertRequestDto.setDvno(makeDVNO());
        // selectkey가 제대로 작동하지 않을경우
        // selectkey로 가져오는 로직이 아닐경우가 있다.
        if (insertExpendColumn(insertRequestDto) != -1) {
            isInsert = true;
        }
        return isInsert;
    }

    @Transactional
    @Override
    public int insertFile(
            FileRequestDto fileRequestDto
            , EmployeeDto employeeDto
            , MultipartFile multipartFile
    ) throws IOException {
        ;
        int empno = selectEmpOne(employeeDto.getEmpno()).getEmpno();
        fileRequestDto.setEmpno(empno);
        fileRequestDto.setDno(this.dno);
        fileRequestDto.setFilename(multipartFile.getOriginalFilename());
        fileRequestDto.setFilesize((int) multipartFile.getSize());
        multipartFile.transferTo(new File("/file/expend"));
        fileRequestDto.setPhysicalpath("/file/expend");
        fileRequestDto.setKind("A");    // 지추결의서 영수증

        return expendInsertMapper.insertFile(fileRequestDto);
    }

    @Transactional
    @Override
    public int updateExpendInformation(EmployeeDto employeeDto, UpdateRequestDto updateRequestDto) {
        SelectEmployeeDto selectEmployeeDto = selectEmpOne(employeeDto.getEmpno());
        int empno = selectEmployeeDto.getEmpno();
        String departmentcd = selectEmployeeDto.getDepartmentcd();
        int dvamt = 0;
        for (DetailResponseDto i : detailExpends) {
            dvamt += i.getPrice();
        }

        updateRequestDto.setDvno(makeDVNO());
        updateRequestDto.setEmpno(empno);
        updateRequestDto.setDvamt(dvamt);
        updateRequestDto.setDvregdate(LocalDate.now());
        updateRequestDto.setDepartmentcd(departmentcd);
        return expendInsertMapper.updateExpendInformation(updateRequestDto);
    }

    @Override
    public List<DetailResponseDto> selectAllDetailExpend(String dvno) {
        return expendInsertMapper.selectDetailExpend(dvno);
    }

    @Override
    public boolean deleteDetail(int dno) {
        return expendInsertMapper.deleteDetailExpend(dno)!=0;
    }

    private String makeDVNO() {
        String thisYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        int i = 1;
        String dunno = "ss" + thisYear + "-" + i;
        if (!expendInsertMapper.selectAll().isEmpty()) {
            String[] str = expendInsertMapper.selectLastOne().split("-", 2);
            i = Integer.parseInt(str[1]) + 1;
            dunno = "ss" + thisYear + "-" + i;
        }
        this.detailExpends = expendInsertMapper.selectDetailExpend(dunno);
        return dunno;
    }

    //    dno를 출력하는 메소드
    @Transactional
    int insertExpendColumn(InsertRequestDto insertRequestDto) {
        return this.dno = expendInsertMapper.insertExpend(insertRequestDto);
    }

    private SelectEmployeeDto selectEmpOne(int i) {
        return expendInsertMapper
                .selectOneEmployee
                        (Math.toIntExact(i));
    }

}
