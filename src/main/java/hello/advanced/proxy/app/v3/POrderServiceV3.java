package hello.advanced.proxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class POrderServiceV3 {
    private final POrderRepositoryV3 orderRepository;

    public POrderServiceV3(POrderRepositoryV3 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
