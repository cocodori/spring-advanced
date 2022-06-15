package hello.advanced.proxy.app.v1;

public class POrderControllerV1Impl implements POrderControllerV1 {

    private final POrderServiceV1 orderService;

    public POrderControllerV1Impl(POrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";

    }
}
