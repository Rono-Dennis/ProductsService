package com.example.productsservice.command.events;

import com.example.productsservice.command.data.Product;
import com.example.productsservice.command.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public  void on(ProductCreateEvent createEvent){
        Product product = new Product();
        BeanUtils.copyProperties(createEvent,product);
        productRepository.save(product);
    }
}
