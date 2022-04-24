package com.rgbitsoft.blog.config;

import com.rgbitsoft.blog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
   private final AccountRepository accountRepository;



}
