package az.idtech.notificationservicems.dto;

import lombok.Data;

@Data
public class OrderEventDto {
    private Long orderId;
    private String productId;
    private int quantity;

}
