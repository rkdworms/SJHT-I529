package sjht.erp.paymanage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sjht.erp.paymanage.dto.SalaryExpendDto;
import sjht.erp.paymanage.repository.PayManageMapper;
import sjht.erp.paymanage.dto.PayEmpDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayManageService {

    final PayManageMapper payManageMapper;
    public List<PayEmpDto> getPayEmpList() {
        List<PayEmpDto> list = payManageMapper.getPayEmpList();

        return list;
    }

    public void payEmpDto(List<PayEmpDto> payEmpDtoList, int empno) {

        SalaryExpendDto sed = new SalaryExpendDto(payEmpDtoList, empno);

        payManageMapper.paySalary(payEmpDtoList);
        payManageMapper.paySalaryHistory(payEmpDtoList);
        payManageMapper.paySalaryExpend(sed);
        payManageMapper.updateExpendHistroy();
    }
}
