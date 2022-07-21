package io.github.viniciuslp070.vendas.domain.repository;

import io.github.viniciuslp070.vendas.domain.entity.Customer;
import io.github.viniciuslp070.vendas.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomer(Customer customer);

    @Query("select o from Order o left join fetch o.orderProducts where o.id = :id")
    Optional<Order> findByIdFetchOrderProducts(@Param("id") Integer id);
}
