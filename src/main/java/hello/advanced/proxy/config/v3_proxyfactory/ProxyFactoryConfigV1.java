package hello.advanced.proxy.config.v3_proxyfactory;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public POrderControllerV1 pOrderControllerV1(LogTrace logTrace) {
        POrderControllerV1 pOrderControllerV1 = new POrderControllerV1Impl(pOrderServiceV1(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(pOrderControllerV1);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        POrderControllerV1 proxy = (POrderControllerV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), pOrderControllerV1.getClass());
        return proxy;
    }

    @Bean
    public POrderServiceV1 pOrderServiceV1(LogTrace logTrace) {
        POrderServiceV1 ppOrderServiceV1 = new PPOrderServiceV1Impl(pOrderRepositoryV1(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(ppOrderServiceV1);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        POrderServiceV1 proxy = (POrderServiceV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), ppOrderServiceV1.getClass());
        return proxy;
    }

    @Bean
    public POrderRepositoryV1 pOrderRepositoryV1(LogTrace logTrace) {
        POrderRepositoryV1 pOrderRepositoryV1 = new POrderRepositoryV1Impl();
        ProxyFactory factory = new ProxyFactory(pOrderRepositoryV1);
        factory.addAdvisor(getAdvisor(logTrace));
        POrderRepositoryV1 proxy = (POrderRepositoryV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), pOrderRepositoryV1.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
