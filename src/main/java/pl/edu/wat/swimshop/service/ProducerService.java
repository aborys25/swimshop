package pl.edu.wat.swimshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.swimshop.dto.ProducerRequest;
import pl.edu.wat.swimshop.dto.ProducerResponse;
import pl.edu.wat.swimshop.entity.Producer;
import pl.edu.wat.swimshop.repository.ProducerRepository;

import java.util.List;

@Service
public class ProducerService {
    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public List<Producer> showAll(){
        return producerRepository.findAll();
    }
    public ProducerResponse save(ProducerRequest producerRequest){
        Producer producer = new Producer();
        producer.setBrand(producerRequest.getBrand());
        producer.setCountry(producerRequest.getCountry());
        producer = producerRepository.save(producer);

        return new ProducerResponse(producer.getId(), producer.getBrand(), producer.getCountry());
    }
}
