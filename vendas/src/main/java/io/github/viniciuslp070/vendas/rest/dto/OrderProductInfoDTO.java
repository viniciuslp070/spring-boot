package io.github.viniciuslp070.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductInfoDTO {

    private String description;
    private BigDecimal unityValue;
    private Integer quantity;
}
