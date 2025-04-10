package kr.hhplus.be.domain.point.service;

import kr.hhplus.be.domain.point.dto.PointDTO;
import kr.hhplus.be.domain.point.entity.PointHistory;
import kr.hhplus.be.domain.point.entity.Point;
import kr.hhplus.be.domain.point.repository.PointHistoryRepository;
import kr.hhplus.be.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    private final PointHistoryRepository pointHistoryRepository;


    // 포인트 잔액 조회
    public PointDTO getBalance(Long userId) {
        Point point = pointRepository.findUserById(userId);
        return new PointDTO(userId, point.getBalance());
    }

    // 포인트 충전
    @Transactional
    public PointDTO charge(Long userId, BigDecimal chargeAmount) {
        Point point = pointRepository.findUserById(userId);
        point.charge(chargeAmount);
        pointRepository.save(point);
        pointHistoryRepository.save(PointHistory.from(point));

        return new PointDTO(userId, point.getBalance());
    }

    // 포인트 사용
    @Transactional
    public PointDTO use(Long userId, BigDecimal useAmount) {
        Point point = pointRepository.findUserById(userId);
        point.use(useAmount);
        pointRepository.save(point);
        pointHistoryRepository.save(PointHistory.from(point));

        return new PointDTO(userId, point.getBalance());
    }

}
