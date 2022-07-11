package io.github.viniciuslp070.vendas;

import io.github.viniciuslp070.vendas.domain.entity.Customer;
import io.github.viniciuslp070.vendas.domain.entity.Order;
import io.github.viniciuslp070.vendas.domain.repository.CustomerRepository;
import io.github.viniciuslp070.vendas.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired CustomerRepository customerRepository,
            @Autowired OrderRepository orderRepository) {
        return args -> {
            System.out.println("Saving Customers: ");
            Customer customer = new Customer("Anna Júlia");
            customerRepository.save(customer);

            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderDate(LocalDate.now());
            order.setTotal(BigDecimal.valueOf(100));

            orderRepository.save(order);

//            Customer customer1 = customerRepository.findCustomerFetchOrders(customer.getId());
//            System.out.println(customer);
//            System.out.println(customer1.getOrders());

           orderRepository.findByCustomer(customer).forEach(System.out::println);


        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
