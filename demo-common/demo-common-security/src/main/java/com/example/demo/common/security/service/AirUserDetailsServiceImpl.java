package com.example.demo.common.security.service;

import com.example.demo.user.api.dto.UserInfo;
import com.example.demo.user.api.entity.SysUser;
import com.example.demo.user.api.feign.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service(value = "airUserDetailsService")
public class AirUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 通过 Username 加载用户详情
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userClient.getUserInfo(username).getBody();

        if (userInfo!=null){
            SecurityUser securityUser = getSecurityUser(userInfo);
            return securityUser;
        }

//        if (username.equals("linyuan")) {
//            String          password        = passwordEncoder.encode("123456");
//            Set<String> set = new HashSet<>();
//            set.add("/t6");
//            set.add("/c/**");
//            set.add("ROLE_USER");
//            set.add("sys_dept_update");
//            UserDetails userDetails = new User("linyuan",
//                    password,
//                    AuthorityUtils.createAuthorityList(set.toArray(new String[]{})));
//            return userDetails;
////            UserDetails userDetails = new User("linyuan",
////                    password,
////                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
////            return userDetails;
//        }
        return null;
    }

    private SecurityUser getSecurityUser(UserInfo userInfo) {
        SysUser sysUser = userInfo.getSysUser();
        SecurityUser securityUser = new SecurityUser(sysUser.getUsername(),
                sysUser.getPassword(),
                AuthorityUtils.createAuthorityList(userInfo.getPermissions()));
        securityUser.setUserId(sysUser.getUserId());
        return securityUser;
    }
}
