package hsf302.lab02.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean {

    // TODO 4.1: Field initialized
    private boolean initialized = false;

    // TODO 4.2: @PostConstruct — set initialized = true, in "[LIFECYCLE] LifecycleBean initialized"
    @PostConstruct
    public void init() {
        initialized = true;
        System.out.println("[LIFECYCLE] LifecycleBean initialized");
    }

    // TODO 4.3: @PreDestroy — set initialized = false, in "[LIFECYCLE] LifecycleBean destroyed"
    @PreDestroy
    public void cleanup() {
        initialized = false;
        System.out.println("[LIFECYCLE] LifecycleBean destroyed");
    }

    // TODO 4.4: isInitialized() — trả về giá trị field initialized
    public boolean isInitialized() {
        return initialized;
    }
}
