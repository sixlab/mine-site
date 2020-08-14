package tech.minesoft.mine.site.core.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import tech.minesoft.mine.site.core.utils.Ctx;

@Slf4j
@Component
public class MessageTemplate {
    private static final String QUEUE = "queue";

    @Autowired
    private QueueChannel queueChannel;

    public void send(String queue, Object payload){
        Message<Object> message = MessageBuilder.withPayload(payload)
                .setHeader(QUEUE, queue)
                .build();

        queueChannel.send(message);
    }

    @ServiceActivator(inputChannel = "queueChannel", poller = @Poller)
    public void onMessage(Message<Object> message) {
        MessageHeaders headers = message.getHeaders();
        if (headers.containsKey(QUEUE)) {
            String queue = String.valueOf(headers.get(QUEUE));
            MessageConsumer job = Ctx.getBean(MessageConsumer.class, queue);
            job.run(message);
        }
    }
}
