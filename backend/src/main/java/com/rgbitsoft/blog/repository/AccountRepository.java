package com.rgbitsoft.blog.repository;

import com.rgbitsoft.blog.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 참고 소셜로그인
//https://deeplify.dev/back-end/spring/oauth2-social-login

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(String userId);
}
