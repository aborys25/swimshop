package pl.edu.wat.swimshop.dto;

import lombok.Data;
import pl.edu.wat.swimshop.entity.Producer;

@Data
public class ProductsRequest {
    private String name;
    private Double price;
    private String producer;
}
