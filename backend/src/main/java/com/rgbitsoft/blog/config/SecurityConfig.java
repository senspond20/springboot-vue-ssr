package com.rgbitsoft.blog.config;

import com.rgbitsoft.blog.model.entity.Account;
import com.rgbitsoft.blog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
   private final AccountRepository accountRepository;
   private static final String[] AUTH_WHITELIST = {
           "/swagger-resources/**",
           "/swagger-ui.html",
           "/v2/api-docs",
           "/webjars/**",
           "/h2-console/**"
   };
   // 시큐리티 적용하지 않는 정적자원
   @Override
   public void configure(WebSecurity web) {
      web.ignoring().antMatchers(AUTH_WHITELIST)
                   .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
   };

   //private final JwtAuthenticationEntryPoint unauthorizedEntryPoint;

   @RequiredArgsConstructor
   private static final class CustomUserDetailService implements UserDetailsService {
      private final AccountRepository accountRepository;

      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Account account = accountRepository.findById(email);
         if(account == null){
            throw new UsernameNotFoundException(email);
         }else{
            return new User(account.getEmail(), account.getPassword(), getAuthority(account));
         }
      }
      private Set<SimpleGrantedAuthority> getAuthority(Account account) {
         Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        // account.getRoles().forEach(role -> {
         //    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
         //});
         return authorities;
      }
   };

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(new CustomUserDetailService(accountRepository)).passwordEncoder(new BCryptPasswordEncoder());
   }

//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//      http.cors()
//              .and()
//              .csrf().disable().authorizeRequests()
//              .antMatchers("/","/signup","/authenticate").permitAll() // 홈, 회원가입, 로그인 검증 url에 접근허용
//              .anyRequest().authenticated();                           // 을 제외한 모든 요청에 인증 요구
////              .and()
// //             .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint).and()
//  //            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//      // http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);;
//      //  super.configure(http);
//   }

   @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

//   @Bean
//   public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
//      return new JwtAuthenticationFilter();
//   }

//   @Bean
//   public CustomUserDetailService userDetailServiceBean() {
//      return new CustomUserDetailService();
//   }




}
