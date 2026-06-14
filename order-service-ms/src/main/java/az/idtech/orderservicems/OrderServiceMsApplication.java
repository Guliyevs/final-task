package az.idtech.orderservicems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceMsApplication.class, args);
    }

}
