package pl.edu.wat.swimshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.swimshop.entity.Products;

public interface ProductsRepository extends MongoRepository<Products, String> {
}
