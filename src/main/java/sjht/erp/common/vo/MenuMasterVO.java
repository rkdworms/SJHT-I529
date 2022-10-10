package sjht.erp.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuMasterVO {
    private Long menuid;
    private UserMasterVO usertype;
    private String menuname;
    private String menuurl;
    private Long groupnumber;
    private Long odr;
    private Long level;
}
