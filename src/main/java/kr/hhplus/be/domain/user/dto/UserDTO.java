package kr.hhplus.be.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UserDTO {
    Long userId;
    BigDecimal balance;
}
