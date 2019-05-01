package com.example.demoauth.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationManagerBuilder authenticationManagerBuilder;


  /**
   * 1\这里记得设置requestMatchers,不拦截需要token验证的url
   * 不然会优先被这个filter拦截,走用户端的认证而不是token认证
   * 2\这里记得对oauth的url进行保护,正常是需要登录态才可以
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {

    http
            .authorizeRequests().antMatchers("/oauth/**","/login/**","/logout/**","/sys/user/query/**").permitAll()
            .and().formLogin().permitAll()
            .and().authorizeRequests().anyRequest().authenticated()
            .and().csrf().disable();

  }


  /**
   * support password grant type
   * @return
   * @throws Exception
   */
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  @Qualifier(value = "airUserDetailsService")
  private UserDetailsService userDetailsService;

  @PostConstruct
  public void init() {
    try {
      authenticationManagerBuilder
          .userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder());
    } catch (Exception e) {
      throw new BeanInitializationException("Security configuration failed", e);
    }
  }
  @Override
  protected void configure(AuthenticationManagerBuilder builder) throws Exception{
    builder.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
  }


}
