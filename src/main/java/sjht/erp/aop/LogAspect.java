//package sjht.erp.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//
//@Component
//@Aspect
//public class LogAspect {
//
//    @Before("@annotation(Token)") // method 실행 되는 전 과정
//    public Object LogGetDate(ProceedingJoinPoint joinPoint) throws Throwable {
//        Date date = new Date();
//        date.getTime(); // 메소드가 실행되는 현재 시간
//
//        Object ret = joinPoint.proceed();
//
//        return ret;
//    }
//
//    // 포인트컷 선언의 예
//    @Pointcut("execution(* hello(…))") // 포인트컷 파라미터는 없으며 표현식으로 정의 "execution ~"
//    private void all() {
//
//    }
//}