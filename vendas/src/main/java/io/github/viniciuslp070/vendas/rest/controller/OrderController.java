package io.github.viniciuslp070.vendas.rest.controller;

import io.github.viniciuslp070.vendas.domain.entity.Order;
import io.github.viniciuslp070.vendas.domain.entity.OrderProduct;
import io.github.viniciuslp070.vendas.domain.enums.OrderStatus;
import io.github.viniciuslp070.vendas.rest.dto.OrderDTO;
import io.github.viniciuslp070.vendas.rest.dto.OrderInfoDTO;
import io.github.viniciuslp070.vendas.rest.dto.OrderProductInfoDTO;
import io.github.viniciuslp070.vendas.rest.dto.UpdateOrderStatusDTO;
import io.github.viniciuslp070.vendas.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid OrderDTO orderDTO) {
        Order order = orderService.saveOrder(orderDTO);
        return order.getId();
    }

    @GetMapping("/{id}")
    public OrderInfoDTO getById(@PathVariable Integer id) {
        return orderService.getFullOrder(id).map(o ->convert(o)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.")
        );
    }

    private OrderInfoDTO convert(Order order) {
        return OrderInfoDTO.builder()
                .code(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getCustomer().getCpf())
                .name(order.getCustomer().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .items(convert(order.getOrderProducts()))
                .build();
    }

    @PatchMapping("/status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody UpdateOrderStatusDTO statusDTO) {
        String newStatus = statusDTO.getNewStatus();
        orderService.updateStatus(id, OrderStatus.valueOf(newStatus));
    }

    private List<OrderProductInfoDTO> convert(List<OrderProduct> items) {
        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(
                item -> OrderProductInfoDTO
                        .builder().description(item.getProduct().getDescription())
                        .cost(item.getProduct().getCost())
                        .quantity(item.getQuantity())
                        .build()
        ).collect(Collectors.toList());
    }
}
