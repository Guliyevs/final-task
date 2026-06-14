package az.idtech.orderservicems.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private int quantity;
}
