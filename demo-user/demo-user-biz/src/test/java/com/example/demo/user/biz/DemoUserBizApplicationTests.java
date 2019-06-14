package com.example.demo.user.biz;

import com.example.demo.user.api.entity.SysUser;
import com.example.demo.user.api.request.UserUpdateDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoUserBizApplicationTests {

    @Test
    public void contextLoads() {

        SysUser sysUser = new SysUser();
        sysUser.setPassword("234234324");
        sysUser.setEmail("sdfsdfsd");

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("-----");

        BeanUtils.copyProperties(userUpdateDTO, sysUser);
        System.err.println(sysUser);
    }

}
