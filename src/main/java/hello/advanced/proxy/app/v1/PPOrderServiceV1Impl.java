package hello.advanced.proxy.app.v1;

public class PPOrderServiceV1Impl implements POrderServiceV1 {

    private final POrderRepositoryV1 orderRepository;

    public PPOrderServiceV1Impl(POrderRepositoryV1 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
