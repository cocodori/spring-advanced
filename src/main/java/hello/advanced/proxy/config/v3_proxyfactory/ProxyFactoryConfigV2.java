package hello.advanced.proxy.config.v3_proxyfactory;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.proxy.app.v2.POrderControllerV2;
import hello.advanced.proxy.app.v2.POrderRepositoryV2;
import hello.advanced.proxy.app.v2.POrderServiceV2;
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
public class ProxyFactoryConfigV2 {
    @Bean
    public POrderControllerV2 pOrderControllerV1(LogTrace logTrace) {
        POrderControllerV2 pOrderControllerV2 = new POrderControllerV2(pOrderServiceV1(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(pOrderControllerV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        POrderControllerV2 proxy = (POrderControllerV2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), pOrderControllerV2.getClass());
        return proxy;
    }

    @Bean
    public POrderServiceV2 pOrderServiceV1(LogTrace logTrace) {
        POrderServiceV2 ppOrderServiceV2 = new POrderServiceV2(pOrderRepositoryV2(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(ppOrderServiceV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        POrderServiceV2 proxy = (POrderServiceV2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), ppOrderServiceV2.getClass());
        return proxy;
    }

    @Bean
    public POrderRepositoryV2 pOrderRepositoryV2(LogTrace logTrace) {
        POrderRepositoryV2 pOrderRepositoryV2 = new POrderRepositoryV2();
        ProxyFactory factory = new ProxyFactory(pOrderRepositoryV2);
        factory.addAdvisor(getAdvisor(logTrace));
        POrderRepositoryV2 proxy = (POrderRepositoryV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), pOrderRepositoryV2.getClass());
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
