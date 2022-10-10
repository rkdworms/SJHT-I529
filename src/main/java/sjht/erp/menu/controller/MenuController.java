package sjht.erp.menu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sjht.erp.login.dto.EmployeeDto;
import sjht.erp.menu.dto.MenuDto;
import sjht.erp.menu.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuservice;

    // 접근 권한별 메뉴구분
    @GetMapping("/api/menu")
    public ResponseEntity<List<List<MenuDto>>> menulist(HttpServletRequest request){

        //접근권한 및 아이디 참조
        EmployeeDto emp = (EmployeeDto)request.getAttribute("empNo");

        // -menu list 요청/ 접근 권한 넘겨주기(usertype)
        List<List<MenuDto>> menuDto = menuservice.getMenuList(emp.getUsertype());

        // -menu list 반환
        return ResponseEntity.status(HttpStatus.OK).body(menuDto);
    }

    @GetMapping("/menu/menu")
    public String menulist(){

        return "menu/menu";
    }
}
