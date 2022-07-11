package io.github.viniciuslp070.vendas.domain.repository;

import io.github.viniciuslp070.vendas.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select * from customers c where c.name like :name", nativeQuery = true)
    List<Customer> findByNameLike(@Param("name") String name);

    @Modifying
    @Query(value = "delete from customers c where c.name = :name", nativeQuery = true)
    void deleteByName(String name);

    @Query(value = "select c from Customer c left join fetch c.orders where c.id = :id")
    Customer findCustomerFetchOrders(@Param("id") Integer id);



}
