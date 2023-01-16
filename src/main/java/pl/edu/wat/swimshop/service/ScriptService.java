package pl.edu.wat.swimshop.service;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.swimshop.repository.ProducerRepository;
import pl.edu.wat.swimshop.repository.ProductsRepository;


@Service
@Slf4j
public class ScriptService {
    private final ProductsRepository productsRepository;
    private final ProducerRepository producerRepository;

    @Autowired
    public ScriptService(ProducerRepository producerRepository,
                         ProductsRepository productsRepository) {
        this.producerRepository = producerRepository;
        this.productsRepository = productsRepository;
    }

    public String exec(String script){
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()){
            var bindings = context.getBindings("js");
            bindings.putMember("producerRepository", producerRepository);
            bindings.putMember("productsRepository", productsRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }



}