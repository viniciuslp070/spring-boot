package io.github.viniciuslp070.vendas.service;

import io.github.viniciuslp070.vendas.domain.entity.Order;
import io.github.viniciuslp070.vendas.domain.enums.OrderStatus;
import io.github.viniciuslp070.vendas.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {
    Order saveOrder(OrderDTO orderDTO);

    Optional<Order> getFullOrder(Integer id);

    void updateStatus(Integer id, OrderStatus orderStatus);

}
