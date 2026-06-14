package az.idtech.notificationservicems.listener;

import az.idtech.notificationservicems.config.RabbitMQConfig;
import az.idtech.notificationservicems.dto.OrderEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(OrderEventDto orderEventDto) {
        log.info("Order ID: {} Product ID: {} Quantity: {}",
                orderEventDto.getOrderId(), orderEventDto.getProductId(), orderEventDto.getQuantity());
    }
}
