package sjht.erp.paymanage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryExpendDto {
    private int paysequence;
    private int empno;
    private int dvamt;

    public SalaryExpendDto(List<PayEmpDto> payEmpDtoList, int empno){
        this.paysequence = payEmpDtoList.get(0).getPaysequence();
        this.empno = empno;
        this.dvamt = 0 ;

        for (PayEmpDto payEmpDto : payEmpDtoList) {
            this.dvamt += payEmpDto.getNetpay();
        }

    }
}
