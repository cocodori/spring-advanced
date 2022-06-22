package hello.advanced.proxy.config.v1_proxy;

import hello.advanced.proxy.app.v2.POrderControllerV2;
import hello.advanced.proxy.app.v2.POrderRepositoryV2;
import hello.advanced.proxy.app.v2.POrderServiceV2;
import hello.advanced.proxy.config.v1_proxy.concrete_proxy.POrderControllerConcreteProxy;
import hello.advanced.proxy.config.v1_proxy.concrete_proxy.POrderRepositoryConcreteProxy;
import hello.advanced.proxy.config.v1_proxy.concrete_proxy.POrderServiceConcreteProxy;
import hello.advanced.trace.logtrace.LogTrace;
import jdk.jfr.Frequency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {
    @Bean
    public POrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        POrderRepositoryV2 repositoryV2 = new POrderRepositoryV2();
        return new POrderRepositoryConcreteProxy(repositoryV2, logTrace);
    }

    @Bean
    public POrderServiceV2 pOrderServiceV2(LogTrace logTrace) {
        POrderServiceV2 serviceV2 = new POrderServiceV2(orderRepositoryV2(logTrace));
        return new POrderServiceConcreteProxy(serviceV2, logTrace);
    }

    @Bean
    public POrderControllerV2 pOrderControllerV2(LogTrace logTrace) {
        POrderControllerV2 controllerV2 = new POrderControllerV2(pOrderServiceV2(logTrace));
        return new POrderControllerConcreteProxy(controllerV2, logTrace);
    }
}
