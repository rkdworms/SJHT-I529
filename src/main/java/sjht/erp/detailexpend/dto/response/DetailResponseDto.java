package sjht.erp.detailexpend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailResponseDto {
    private String dvno;
    private LocalDateTime dvusedate;
    private int price;
    private String dememo;
    private String divcd;
    private int detailuse;
    private int dno;
}
