package br.rodrigo.orders.services;

import br.rodrigo.orders.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${kafka.bootstrapTopic}")
    private String bootstrapTopic;

    @Override
    public void sendMessage(Order order) {
        ListenableFuture<SendResult<String, Order>> future = kafkaTemplate.send(bootstrapTopic, order);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Order>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + order.toString() + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Order> result) {
                System.out.println("Sent message=[" + order.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}
