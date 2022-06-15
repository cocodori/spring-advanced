package hello.advanced.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface POrderControllerV1 {
    @GetMapping("/p/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/p/v1/no-log")
    String noLog();
}
