package pl.edu.wat.swimshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.wat.swimshop.entity.Producer;

@Data
@AllArgsConstructor
public class ProductsResponse {
    private String id;
    private String name;
    private Double price;
    private ProducerResponse producer;
}
