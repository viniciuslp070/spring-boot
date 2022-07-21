package io.github.viniciuslp070.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfoDTO {

    private Integer code;
    private String cpf;
    private String name;
    private BigDecimal total;
    private String orderDate;
    private List<OrderProductInfoDTO> items;

}
