package sjht.erp.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankCodeVO {
    private String bankcd;
    private String bankname;
}
