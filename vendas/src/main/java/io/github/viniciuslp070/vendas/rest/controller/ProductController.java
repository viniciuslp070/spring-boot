package io.github.viniciuslp070.vendas.rest.controller;

import io.github.viniciuslp070.vendas.domain.entity.Product;
import io.github.viniciuslp070.vendas.domain.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository  productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id") Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not foud.")
        );
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        productRepository.findById(id).map(
                product -> {
                    productRepository.delete(product);
                    return Void.TYPE;
                }).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found.")
        );
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id,
                       @RequestBody @Valid Product product) {
        productRepository.findById(id).map(
                existingProduct -> {
                    product.setId(existingProduct.getId());
                    productRepository.save(product);
                    return existingProduct;
                }).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found.")
        );
    }

    @GetMapping
    public List<Product> findAll(Product filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        return productRepository.findAll(example);
    }
}

















