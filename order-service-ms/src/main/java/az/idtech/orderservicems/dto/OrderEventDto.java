package az.idtech.orderservicems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEventDto {
    private Long orderId;
    private Long productId;
    private int quantity;
}
