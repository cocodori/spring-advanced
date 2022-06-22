package hello.advanced.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.proxy.app.v2.POrderControllerV2;
import hello.advanced.proxy.app.v2.POrderServiceV2;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class POrderControllerConcreteProxy extends POrderControllerV2 {
    private final POrderControllerV2 target;
    private final LogTrace logTrace;

    public POrderControllerConcreteProxy(POrderControllerV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        String result = "";
        try  {
            status = logTrace.begin("POrderControllerV2.request()");
            result = target.request(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
        return result;
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
