package pl.edu.wat.swimshop.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.swimshop.dto.ProducerRequest;
import pl.edu.wat.swimshop.dto.ProducerResponse;
import pl.edu.wat.swimshop.entity.Producer;
import pl.edu.wat.swimshop.service.ProducerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/producer")
public class ProducerControler {
    private final ProducerService producerService;
    @Autowired
    public ProducerControler(ProducerService producerService) {
        this.producerService = producerService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> createProducer(@RequestBody ProducerRequest producerRequest){
        ProducerResponse producerResponse = producerService.save(producerRequest);
        return new ResponseEntity<>(producerResponse.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Producer> showAllProducer(){
        return producerService.showAll();
    }
}
