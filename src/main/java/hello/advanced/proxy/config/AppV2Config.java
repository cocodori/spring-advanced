package hello.advanced.proxy.config;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.proxy.app.v2.POrderControllerV2;
import hello.advanced.proxy.app.v2.POrderRepositoryV2;
import hello.advanced.proxy.app.v2.POrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV2Config {
    @Bean
    public POrderControllerV2 pOrderControllerV2() {
        return new POrderControllerV2(orderServiceV2());
    }

    private POrderServiceV2 orderServiceV2() {
        return new POrderServiceV2(orderRepositoryV2());
    }

    private POrderRepositoryV2 orderRepositoryV2() {
        return new POrderRepositoryV2();
    }
}
