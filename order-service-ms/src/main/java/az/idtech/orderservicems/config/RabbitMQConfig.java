package az.idtech.orderservicems.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "order-queue";
    public static final String EXCHANGE_NAME = "order-exchange";
    public static final String ROUTING_KEY = "order-routing-key";

    @Bean
    public Queue orderQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(ROUTING_KEY);
    }

}
