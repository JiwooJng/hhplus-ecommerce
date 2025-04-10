package kr.hhplus.be.domain.point.entity;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class Point {

    private Long userId;

    private BigDecimal balance;

    private static final BigDecimal MAX_BALANCE = new BigDecimal(1_000_000);

    // 포인트 충전
    public void charge(BigDecimal chargeAmount) {

        // 충전 금액 <= 0 Exception
        if (chargeAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal updateBalance = this.balance.add(chargeAmount);

        // 잔액 + 충전 금액 > 최대 충전 금액 Exception
        if (MAX_BALANCE.compareTo(updateBalance) < 0) {
            throw new IllegalArgumentException();
        }

        this.balance = updateBalance;

    }

    // 포인트 사용
    public void use(BigDecimal useAmount) {

        // 포인트 사용 금액 <= 0 Exception
        if (useAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal updateBalance = this.balance.subtract(useAmount);

        // 잔액 부족 Exception (잔액 - 포인트 사용 금액 < 0)
        if (updateBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }

        this.balance = updateBalance;
    }
}
