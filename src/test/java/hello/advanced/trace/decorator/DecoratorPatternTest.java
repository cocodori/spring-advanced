package hello.advanced.trace.decorator;

import hello.advanced.trace.decorator.code.Component;
import hello.advanced.trace.decorator.code.DecoratorPatternClient;
import hello.advanced.trace.decorator.code.MessageDecorator;
import hello.advanced.trace.decorator.code.RealComponent;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {
    @Test
    void noDecorator() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }
}