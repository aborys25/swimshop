package pl.edu.wat.swimshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Products {

    @Id
    private String id;
    private String name;
    private Double price;
    private String producer;

    public Products() {

    }
}
