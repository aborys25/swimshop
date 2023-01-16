package pl.edu.wat.swimshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Producer {

    @Id
    private String id;
    private String brand;
    private String country;

    public Producer() {

    }
}

