package com.example.demoauth.endpoint;

import com.example.demoauth.model.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Collection;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    private static Logger log = LoggerFactory.getLogger(RevokeTokenEndpoint.class);

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/oauth/token", method= RequestMethod.DELETE)
    public @ResponseBody
    Msg revokeToken(String accessToken){
        Msg msg = new Msg();
        if (consumerTokenServices.revokeToken(accessToken)){
            msg.setCode(Msg.SUCCESS);
            msg.setMsg("注销成功");
        }else {
            msg.setCode(Msg.FAILED);
            msg.setMsg("注销失败");
        }
        return msg;
    }

    @DeleteMapping(value = "/oauth/token/remove")
    public @ResponseBody Msg removeToken(String[] clientIds, String[] usernames){
        Msg msg = new Msg();
        try {
            for (String username : usernames) {
                for (String clientId : clientIds) {
                    Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(clientId, username);
                    tokens.forEach(x -> {
                        consumerTokenServices.revokeToken(x.getValue());
                        log.warn(String.format("移除用户[%s]token :[%s]", username, x.getValue()));
                    });
                }
            }
        }catch (Exception e){
            msg.setCode(Msg.FAILED);
            msg.setMsg(e.getMessage());
            return msg;
        }
        msg.setCode(Msg.SUCCESS);
        msg.setMsg("注销成功");
        return msg;
    }

}
