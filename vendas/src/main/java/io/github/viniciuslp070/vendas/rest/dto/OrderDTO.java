package io.github.viniciuslp070.vendas.rest.dto;

import io.github.viniciuslp070.vendas.validation.NotEmptyList;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull(message = "{field.customer-code.required}")
    private Integer customer;

    @NotNull(message = "{field.total-order.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.order-items.required}")
    private List<OrderProductDTO> items;
}
