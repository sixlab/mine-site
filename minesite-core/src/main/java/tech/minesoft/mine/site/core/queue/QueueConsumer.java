package tech.minesoft.mine.site.core.queue;

import org.springframework.messaging.Message;

public interface QueueConsumer {
    void run(Message<Object> message);
}
