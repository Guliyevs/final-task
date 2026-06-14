package az.idtech.orderservicems.client;

import az.idtech.orderservicems.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8082/api/v1/products")
public interface InvertoryClient {
    @PutMapping(value = "/{id}", params = "reduce-quantity")
    ProductResponse reduceStock(@PathVariable("id") Long productId, @RequestParam("reduce-quantity") int quantity);

}
