package hello.advanced.aop.internalcall;

import hello.advanced.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@Slf4j
@SpringBootTest
class CallServiceV3Test {
    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    void external(){
        log.info("target={}", callServiceV2.getClass());
        callServiceV2.external();
    }
}