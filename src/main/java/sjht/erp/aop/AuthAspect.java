//package sjht.erp.aop;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import sjht.erp.login.service.LoginService;
//
//@Aspect // AO 클래스 선언: 부가 기능을 주입하는 클래스
//@Component // Ioc 컨테이너가 해당 객체를 생성 및 관리
//@Slf4j
//@AllArgsConstructor
//public class AuthAspect {
//
//    // 기능 2 (추가사항) - // 서비스 실행 시, 컨트롤러에서 토큰 값 넘겨주기
//
//    @Autowired
//    private final LoginService loginService;
//
//    // 대상 메소드 선택: controller 패키지의 모든 메소드
//    @Pointcut("execution(* sjht.erp.hrmsList.controller.*.*(..))")
//    private void pointController() {}
//
//    //로그인 받아오기
//    // 컨트롤러 실행 around, 토큰 받아오기
////    public EmployeeDto StaySignedIn(Authentication authentication){
////        Long userId = ((EmployeeDto) authentication.getPrincipal()).getEmpno();
////        EmployeeDto findUser = loginService.findByUserId(userId);
////
////        System.out.println(" ******** ****************       **************       *************** * ");
////        System.out.println(userId);
////        System.out.println(findUser);
////        System.out.println(findUser.getName());
////        return findUser;
////    }
//
//    //@Around("@annotation(PerLogging)")
//    //@Around("pointController() ")
//    //@Around("pointController() && ")
//
//    // 컨트롤러 메소드가 실행되기전에 !
//    // 공통 부분을 실행한다.
//
//    // 실행 시점 설정
//    //@Around("execution(* *(.., @EmployeeDto (*), ..))")
//    @Around("pointController() && @annotation(emp)")
//    public Object token(ProceedingJoinPoint pjp, Authentication authentication, EmployeeDto emp) throws Throwable {
//
//        System.out.println("            ===========  Around ===========            ");
//        // 공통 로직
//
//        Long userId = ((EmployeeDto) authentication.getPrincipal()).getEmpno();
//        EmployeeDto findUser = loginService.findByUserId(userId);
//
//        // 타겟 메소드 실행
//        Object returnObj = pjp.proceed(new Object[] {findUser});
//
//        returnObj.getClass();
//        System.out.println("성공 vBefore");
//        //반환
//        return returnObj;
//    }
//
////    @AfterReturning(value = "pointController() && args(authentication, ..)", returning = "emp")
////    public void token(EmployeeDto emp) {
////        emp.getName();
////        System.out.println("성공 AfterReturning");
////    }
//}
