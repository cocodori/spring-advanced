package hello.advanced.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    //hello.advanced.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.advanced.aop.order..*(..))")
    public void allOrder() {}

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
