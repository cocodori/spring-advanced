package hello.advanced.proxy.config.v1_proxy;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.advanced.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.advanced.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    @Bean
    public POrderControllerV1 pOrderController(LogTrace logTrace) {

        POrderControllerV1Impl controllerImpl = new POrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    private POrderServiceV1 orderService(LogTrace logTrace) {
        PPOrderServiceV1Impl serviceImpl = new PPOrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    private POrderRepositoryV1 orderRepository(LogTrace logTrace) {
        POrderRepositoryV1Impl repositoryImpl = new POrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }
}
