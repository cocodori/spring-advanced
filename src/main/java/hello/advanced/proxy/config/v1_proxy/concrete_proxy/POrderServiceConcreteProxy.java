package hello.advanced.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.proxy.app.v2.POrderServiceV2;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class POrderServiceConcreteProxy extends POrderServiceV2 {
    private final POrderServiceV2 target;
    private final LogTrace logTrace;

    public POrderServiceConcreteProxy(POrderServiceV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("POrderServiceV2.orderItem()");
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
