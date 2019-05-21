package com.example.demo.common.security.component;

import com.example.demo.common.core.response.AirResult;
import com.example.demo.common.security.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.*;

/**
 * Created by ouyanggang on 2018/11/15.
 */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private final static Logger log = LoggerFactory.getLogger(ResourceServerConfig.class);

  @Autowired
  private RemoteTokenServices remoteTokenServices;
  @Autowired
  private ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;
  @Autowired
  private AirAccessDeniedHandler accessDeniedHandler;

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

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
    UserAuthenticationConverter userTokenConverter = new AirUserAuthenticationConverter();
    accessTokenConverter.setUserTokenConverter(userTokenConverter);

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      public void handleError(ClientHttpResponse response) throws IOException {
        log.error("-------------------------"+String.valueOf(response.getStatusCode().value()));
      }
    });
    remoteTokenServices.setRestTemplate(restTemplate);
    remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
    resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
            .accessDeniedHandler(accessDeniedHandler)
            .tokenServices(remoteTokenServices);
  }

}
