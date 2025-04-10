package kr.hhplus.be.interfaces.point.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequestDTO {
    private BigDecimal chargeAmount;
}
