package tech.minesoft.mine.site.core.queue;

import org.springframework.messaging.Message;

public interface MessageConsumer {
    void run(Message<Object> message);
}
