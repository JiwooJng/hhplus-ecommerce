package kr.hhplus.be.domain.user.repository;

import kr.hhplus.be.domain.user.entity.User;

public interface UserRepository {
    User save(User user);
    User findUserById(Long userId);
}
