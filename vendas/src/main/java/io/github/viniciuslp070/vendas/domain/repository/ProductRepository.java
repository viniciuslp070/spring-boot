package io.github.viniciuslp070.vendas.domain.repository;

import io.github.viniciuslp070.vendas.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
