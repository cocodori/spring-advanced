package hello.advanced.proxy.app.v2;

public class POrderServiceV2 {
    private final POrderRepositoryV2 orderRepository;

    public POrderServiceV2(POrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
