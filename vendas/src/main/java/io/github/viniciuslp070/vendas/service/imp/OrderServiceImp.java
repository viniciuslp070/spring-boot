package io.github.viniciuslp070.vendas.service.imp;

import io.github.viniciuslp070.vendas.domain.entity.Customer;
import io.github.viniciuslp070.vendas.domain.entity.Order;
import io.github.viniciuslp070.vendas.domain.entity.OrderProduct;
import io.github.viniciuslp070.vendas.domain.entity.Product;
import io.github.viniciuslp070.vendas.domain.enums.OrderStatus;
import io.github.viniciuslp070.vendas.domain.repository.CustomerRepository;
import io.github.viniciuslp070.vendas.domain.repository.OrderProductRepository;
import io.github.viniciuslp070.vendas.domain.repository.OrderRepository;
import io.github.viniciuslp070.vendas.domain.repository.ProductRepository;
import io.github.viniciuslp070.vendas.exception.OrderNotFoundException;
import io.github.viniciuslp070.vendas.rest.dto.OrderDTO;
import io.github.viniciuslp070.vendas.rest.dto.OrderProductDTO;
import io.github.viniciuslp070.vendas.exception.BusinessRuleException;
import io.github.viniciuslp070.vendas.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public Order saveOrder(OrderDTO orderDTO) {
        Integer customerId = orderDTO.getCustomer();
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new BusinessRuleException("Customer code invalid."));

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.ORDERED);

        List<OrderProduct> orderProducts = convertItems(order, orderDTO.getItems());
        orderRepository.save(order);
        orderProductRepository.saveAll(orderProducts);
        order.setOrderProducts(orderProducts);

        return order;
    }

    @Override
    public Optional<Order> getFullOrder(Integer id) {

        return orderRepository.findByIdFetchOrderProducts(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, OrderStatus orderStatus){
        orderRepository.findById(id).map(order -> {
            order.setStatus(orderStatus);
            return orderRepository.save(order);
        }).orElseThrow(() -> new OrderNotFoundException());
    }

    private List<OrderProduct> convertItems(Order order, List<OrderProductDTO> items) {
        if(items.isEmpty()) {
            throw new BusinessRuleException("It is not possible to order without items.");
        }
        return items.stream().map( dto -> {
            Integer productId = dto.getProduct();
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new BusinessRuleException("Invalid product code: " + productId)
            );

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setQuantity(dto.getQuantity());
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            return orderProduct;
        }).collect(Collectors.toList());
    }
}
