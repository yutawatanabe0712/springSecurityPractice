package com.example.its.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
//下記アノテーションでfinalかつ初期化されていないフィールドを引数に取るコンストラクタが自動生成される
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                        .and()
                                .csrf().ignoringAntMatchers("/h2-console/**")
                        .and()
                                .headers().frameOptions().disable();
        http
                .authorizeRequests()
                .mvcMatchers("/users/**").hasAuthority("ADMIN")
                //ログインページへのリクエストに対しては、認証は不要。
                .mvcMatchers("/login/**").permitAll()
                //それ以外のリクエストは認証が必要
                .anyRequest().authenticated()
                .and()
                //formログインであることを示す
                .formLogin()
                .loginPage("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        //エンコードされたパスワードでログインできるように設定
        .passwordEncoder(passwordEncoder);
    }
}
