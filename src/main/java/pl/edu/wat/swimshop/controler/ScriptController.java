package pl.edu.wat.swimshop.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.swimshop.repository.ProducerRepository;
import pl.edu.wat.swimshop.repository.ProductsRepository;
import pl.edu.wat.swimshop.service.ScriptService;

@RestController
@CrossOrigin
@RequestMapping("api/script")
public class ScriptController {
    private final ScriptService scriptService;
    private final ProducerRepository producerRepository;
    private final ProductsRepository productsRepository;


    public ScriptController(ScriptService scriptService, ProducerRepository producerRepository, ProductsRepository productsRepository) {
        this.scriptService = scriptService;
        this.producerRepository = producerRepository;
        this.productsRepository = productsRepository;
    }

    @PostMapping("/add-wrong-brand")
    public ResponseEntity<String> add(){
        String scirpt = """
                var Producer = Java.type('pl.edu.wat.swimshop.entity.Producer');
                var Set = Java.type('java.util.Set');
                
                var wrongBrand1 = new Producer();
                wrongBrand1.setBrand("");
                producerRepository.save(wrongBrand1);
                var wrongBrand2 = new Producer();
                wrongBrand2.setCountry("");
                producerRepository.save(wrongBrand2);
                """;

        ScriptService scriptService = new ScriptService(producerRepository, productsRepository);

        return new ResponseEntity<>(scriptService.exec(scirpt), HttpStatus.OK);
    }

    @GetMapping("/fix-brands")
    public String fix(){
        String script = """
                function fixBrand() {
                var brand = producerRepository.findAll();
                
                for (brands of brand){
                var check = brands.getBrand();
                if(check === "" || check === null){
                    brands.setBrand("null");
                    producerRepository.save(brands);
                }
                
                }
                var brand = producerRepository.findAll();
                return "Done";
                }
                
                fixBrand();
                """;

        ScriptService scriptService = new ScriptService(producerRepository, productsRepository);
        return scriptService.exec(script);
    }
}
