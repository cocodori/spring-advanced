package hello.advanced.trace.proxy.pureproxy.conccreteproxy.code;

public class ConcreteClient {
    public ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public void execute() {
        concreteLogic.operation();
    }
}
