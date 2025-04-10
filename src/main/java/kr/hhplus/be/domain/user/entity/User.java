package kr.hhplus.be.domain.user.entity;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class User {

    private Long userId;

    private String userName;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static User of(String userName) {
        User user = new User();

        user.userName = userName;
        user.createDate = LocalDateTime.now();

        return user;
    }
}
