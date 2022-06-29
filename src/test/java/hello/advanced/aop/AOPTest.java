package hello.advanced.aop;

import hello.advanced.aop.order.AOrderRepository;
import hello.advanced.aop.order.AOrderService;
import hello.advanced.aop.order.aop.AspectV1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@SpringBootTest
@Import(AspectV1.class)
class AOPTest {
    @Autowired
    AOrderService orderService;

    @Autowired
    AOrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}