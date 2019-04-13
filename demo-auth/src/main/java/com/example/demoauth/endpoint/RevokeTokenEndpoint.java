package com.example.demoauth.endpoint;

import com.example.demoauth.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/oauth/token", method= RequestMethod.DELETE)
    public @ResponseBody
    Msg revokeToken(String access_token){
        Msg msg = new Msg();
        if (consumerTokenServices.revokeToken(access_token)){
            msg.setCode(Msg.SUCCESS);
            msg.setMsg("注销成功");
        }else {
            msg.setCode(Msg.FAILED);
            msg.setMsg("注销失败");
        }
        return msg;
    }
}
