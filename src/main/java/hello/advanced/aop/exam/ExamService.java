package hello.advanced.aop.exam;

import hello.advanced.aop.exam.annotation.Retry;
import hello.advanced.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    @Trace
    @Retry
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
