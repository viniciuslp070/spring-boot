package io.github.viniciuslp070.vendas.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private Integer customer;
    private BigDecimal total;
    private List<OrderProductDTO> items;
}
