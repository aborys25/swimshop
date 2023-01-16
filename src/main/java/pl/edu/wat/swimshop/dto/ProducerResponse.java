package pl.edu.wat.swimshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProducerResponse {
    private String id;
    private String brand;
    private String country;
}
