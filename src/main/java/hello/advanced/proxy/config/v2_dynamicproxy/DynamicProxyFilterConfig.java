package hello.advanced.proxy.config.v2_dynamicproxy;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.advanced.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public POrderControllerV1 pOrderControllerV1(LogTrace logTrace) {
        POrderControllerV1 pOrderControllerV1 = new POrderControllerV1Impl(pOrderServiceV1(logTrace));
        POrderControllerV1 proxy = (POrderControllerV1) Proxy.newProxyInstance(
                pOrderControllerV1.getClass().getClassLoader(),
                new Class[]{POrderControllerV1.class},
                new LogTraceFilterHandler(pOrderControllerV1, logTrace, PATTERNS)
        );
        return proxy;
    }

    @Bean
    public POrderServiceV1 pOrderServiceV1(LogTrace logTrace) {
        POrderServiceV1 pOrderServiceV1 = new PPOrderServiceV1Impl(pOrderRepositoryV1(logTrace));
        POrderServiceV1 proxy = (POrderServiceV1) Proxy.newProxyInstance(
                pOrderServiceV1.getClass().getClassLoader(),
                new Class[]{POrderServiceV1.class},
                new LogTraceFilterHandler(pOrderServiceV1, logTrace, PATTERNS)
        );
        return proxy;
    }

    @Bean
    public POrderRepositoryV1 pOrderRepositoryV1(LogTrace logTrace) {
        POrderRepositoryV1 pOrderRepositoryV1 = new POrderRepositoryV1Impl();
        POrderRepositoryV1 proxy = (POrderRepositoryV1) Proxy.newProxyInstance(
                pOrderRepositoryV1.getClass().getClassLoader(),
                new Class[]{POrderRepositoryV1.class},
                new LogTraceFilterHandler(pOrderRepositoryV1, logTrace, PATTERNS)
        );
        return proxy;
    }
}
