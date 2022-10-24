package sjht.erp.expendinformation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRequestDto {
    private int empno;
    private String departmentcd;
    private LocalDate dvregdate;
    private int dvamt;
    private String dvno;
}
