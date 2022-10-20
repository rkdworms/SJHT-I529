package sjht.erp.expendinformation.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InsertRequestDto {
    private String dvno;
    private LocalDate dvusedate;
    private int price;
    private String dvmemo;
    private String divcd;
    private String detailuse;
}
