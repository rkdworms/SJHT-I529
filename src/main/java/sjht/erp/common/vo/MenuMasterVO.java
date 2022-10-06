package sjht.erp.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuMasterVO {
    private String menuid;
    private UserMasterVO usertype;
    private String hir_menuid;
    private String menuname;
    private String menuurl;
    private String menu_dvs_code;
    private int groupnumber;
    private int odr;
    private int lavel;
    private String menuiconcd;
    private String use_poa;
    private String delete_poa;
}
