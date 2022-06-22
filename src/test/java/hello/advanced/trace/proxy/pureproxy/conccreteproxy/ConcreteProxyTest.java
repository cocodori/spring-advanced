package hello.advanced.trace.proxy.pureproxy.conccreteproxy;

import hello.advanced.trace.proxy.pureproxy.conccreteproxy.code.ConcreteClient;
import hello.advanced.trace.proxy.pureproxy.conccreteproxy.code.ConcreteLogic;
import hello.advanced.trace.proxy.pureproxy.conccreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {
    @Test
    void noProxy() {
        ConcreteLogic logic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(logic);

        client.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic logic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(logic);
        ConcreteClient client = new ConcreteClient(timeProxy);

        client.execute();
    }
}
