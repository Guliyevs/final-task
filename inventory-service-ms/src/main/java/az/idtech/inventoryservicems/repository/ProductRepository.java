package az.idtech.inventoryservicems.repository;

import az.idtech.inventoryservicems.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
