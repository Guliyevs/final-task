package az.idtech.orderservicems.service;

import az.idtech.orderservicems.client.InvertoryClient;
import az.idtech.orderservicems.config.RabbitMQConfig;
import az.idtech.orderservicems.dto.OrderEventDto;
import az.idtech.orderservicems.dto.OrderRequest;
import az.idtech.orderservicems.model.Order;
import az.idtech.orderservicems.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InvertoryClient inventoryClient;
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public Order createOrder(OrderRequest orderRequest) {
        try {
            inventoryClient.reduceStock(orderRequest.getProductId(), orderRequest.getQuantity());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to reduce stock");
        }
        Order saveOrder = orderRepository.save(Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .createdAt(LocalDateTime.now())
                .build());


        OrderEventDto orderEventDto = new OrderEventDto(saveOrder.getId(), saveOrder.getProductId(), saveOrder.getQuantity());
        String orderEventDtoString = objectMapper.writeValueAsString(orderEventDto);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, orderEventDtoString);

        return saveOrder;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }
}
