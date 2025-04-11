package kr.hhplus.be.domain.point.repository;

import kr.hhplus.be.domain.point.entity.Point;

public interface PointRepository {
    Point save(Point point);
    Point findUserById(Long userId);
}
