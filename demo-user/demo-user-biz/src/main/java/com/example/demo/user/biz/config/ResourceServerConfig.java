package com.example.demo.user.biz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by ouyanggang on 2018/11/15.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


  @Override
  public void configure(HttpSecurity http) throws Exception {

//    http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/**").access("#oauth2.hasScope('all')");
    http
            .authorizeRequests().antMatchers("/oauth/**","/login/**","/logout/**","/sys/user/query/**").permitAll()
            .and().formLogin().permitAll()
            .and().authorizeRequests().anyRequest().authenticated()
            .and().csrf().disable();
  }


}
