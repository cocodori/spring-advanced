package hello.advanced.trace.proxy.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceImpl implements ServiceInterface {
    @Override
    public void save() {
        log.info("sav e호출");
    }

    @Override
    public void find() {
        log.info("find 호출");
    }
}
