package io.github.viniciuslp070.vendas.domain.repository;

import io.github.viniciuslp070.vendas.domain.entity.Customer;
import io.github.viniciuslp070.vendas.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomer(Customer customer);
}
