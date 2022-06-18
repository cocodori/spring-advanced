package hello.advanced.trace.proxy.pureproxy;

import hello.advanced.trace.proxy.pureproxy.code.ProxyPatternClient;
import hello.advanced.trace.proxy.pureproxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }
}
