package com.example.productsservice.command.aggregate;

import com.example.productsservice.command.commands.CreateProductCommand;
import com.example.productsservice.command.events.ProductCreateEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @TargetAggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate(CreateProductCommand createProductCommand){
        ProductCreateEvent productCreateEvent = new ProductCreateEvent();

        BeanUtils.copyProperties(createProductCommand,productCreateEvent);

        AggregateLifecycle.apply(productCreateEvent);

    }

    public ProductAggregate(){
    }

    @EventSourcingHandler
    public void on(ProductCreateEvent productCreateEvent){
        this.name = productCreateEvent.getName();
        this.productId = productCreateEvent.getProductId();
        this.price = productCreateEvent.getPrice();
        this.quantity = productCreateEvent.getQuantity();
    }
}
