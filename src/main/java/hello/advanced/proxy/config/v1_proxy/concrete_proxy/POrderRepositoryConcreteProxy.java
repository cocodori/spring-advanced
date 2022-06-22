package hello.advanced.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.proxy.app.v2.POrderRepositoryV2;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class POrderRepositoryConcreteProxy extends POrderRepositoryV2 {
    private final POrderRepositoryV2 target;
    private final LogTrace logTrace;

    public POrderRepositoryConcreteProxy(POrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("POrderRepository.request()");
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
