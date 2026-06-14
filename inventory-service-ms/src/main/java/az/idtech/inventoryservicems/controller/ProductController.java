package az.idtech.inventoryservicems.controller;

import az.idtech.inventoryservicems.model.Product;
import az.idtech.inventoryservicems.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = {"/{id}", "/{id}/"}, params = "!reduce-quantity")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping(path = {"/{id}", "/{id}/"}, params = "reduce-quantity")
    public Product reduceProductQuantityByQuery(@PathVariable Long id,
                                                @RequestParam("reduce-quantity") int quantity) {
        return productService.reduceProductQuantity(id, quantity);
    }


}
