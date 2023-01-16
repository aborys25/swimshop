package pl.edu.wat.swimshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.edu.wat.swimshop.dto.ProducerResponse;
import pl.edu.wat.swimshop.dto.ProductsRequest;
import pl.edu.wat.swimshop.dto.ProductsResponse;
import pl.edu.wat.swimshop.entity.Producer;
import pl.edu.wat.swimshop.entity.Products;
import pl.edu.wat.swimshop.exception.EntityNotFound;
import pl.edu.wat.swimshop.repository.ProducerRepository;
import pl.edu.wat.swimshop.repository.ProductsRepository;

import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    private final ProducerRepository producerRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, ProducerRepository producerRepository) {
        this.productsRepository = productsRepository;
        this.producerRepository = producerRepository;
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public String deleteProductsById(String id){
        productsRepository.deleteById(id);
        return "Delete";
    }

    public ProductsResponse save(ProductsRequest productsRequest) throws EntityNotFound {
        Products products = new Products();
        products.setName(productsRequest.getName());
        products.setPrice(productsRequest.getPrice());
        Producer producer = producerRepository.findById(productsRequest.getProducer()).orElseThrow(EntityNotFound::new);
        products.setProducer(producer.getId());
        products = productsRepository.save(products);


        return new ProductsResponse(products.getId(), products.getName(), products.getPrice(),new ProducerResponse(producer.getId(), producer.getBrand(), producer.getCountry()));
    }

    public ProductsResponse update(String id, String name, Double price) throws EntityNotFound{
        Products products= productsRepository.findById(id).orElseThrow(EntityNotFound:: new);

        if (StringUtils.hasText(name)){
            products.setName(name);
        }

        if (StringUtils.hasText(String.valueOf(price))){
            products.setPrice(price);
        }

        products = productsRepository.save(products);
        return new ProductsResponse(products.getId(), products.getName(), products.getPrice(),null);
    }

}
