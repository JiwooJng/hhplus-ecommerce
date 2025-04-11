package kr.hhplus.be.domain.point.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PointDTO {
    Long userId;
    BigDecimal balance;
}
