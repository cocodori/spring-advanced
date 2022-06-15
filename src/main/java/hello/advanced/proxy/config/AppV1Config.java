package hello.advanced.proxy.config;

import hello.advanced.proxy.app.v1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {
    @Bean
    public POrderControllerV1 pOrderControllerV1() {
        return new POrderControllerV1Impl(orderServiceV1());
    }

    private POrderServiceV1 orderServiceV1() {
        return new PPOrderServiceV1Impl(orderRepositoryV1());
    }

    private POrderRepositoryV1 orderRepositoryV1() {
        return new POrderRepositoryV1Impl();
    }
}
