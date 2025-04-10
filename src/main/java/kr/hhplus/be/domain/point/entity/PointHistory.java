package kr.hhplus.be.domain.point.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PointHistory {
    private Long userId;
    private BigDecimal balance;
    private LocalDateTime modifyDate;

    public static PointHistory from(Point point) {
        PointHistory pointHistory = new PointHistory();

        pointHistory.userId = point.getUserId();
        pointHistory.balance = point.getBalance();
        pointHistory.modifyDate = LocalDateTime.now();

        return pointHistory;
    }
}
