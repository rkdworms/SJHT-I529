package sjht.erp.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentCodeVO {
    private String departmentcd;
    private String departmentname;
}
