package pl.edu.wat.swimshop.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.swimshop.dto.ProductsRequest;
import pl.edu.wat.swimshop.dto.ProductsResponse;
import pl.edu.wat.swimshop.entity.Products;
import pl.edu.wat.swimshop.exception.EntityNotFound;
import pl.edu.wat.swimshop.service.ProductsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/products")
public class ProductsControler {

    private final ProductsService productsService;

    public ProductsControler(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping()
    public List<Products> callAllProducts(){
        return productsService.getAllProducts();
    }

    @PostMapping("/add")
    public ResponseEntity<String> createProducts(@RequestBody ProductsRequest productsRequest){
        try {
            return new ResponseEntity<>(productsService.save(productsRequest).getId(), HttpStatus.CREATED);
        }catch (EntityNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductsResponse>updateProducts(@PathVariable String id, @RequestParam(required = false)String name, @RequestParam(required = false) Double price){
        try {
            return new ResponseEntity<>(productsService.update(id, name, price), HttpStatus.OK);
        }catch (EntityNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/del/{id}")
    public @ResponseBody ResponseEntity deleteProducts(@PathVariable String id){
        productsService.deleteProductsById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
