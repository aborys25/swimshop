package pl.edu.wat.swimshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.swimshop.entity.Producer;

public interface ProducerRepository extends MongoRepository<Producer, String> {

}
