package com.forguta.libs.web.auth0.proxy;

import com.forguta.libs.web.auth0.proxy.config.Auth0AuthenticationAPIProxyConfig;
import com.forguta.libs.web.auth0.proxy.handler.OauthTokenClientExceptionHandler;
import com.forguta.libs.web.auth0.proxy.handler.SignupClientExceptionHandler;
import com.forguta.libs.web.auth0.proxy.model.request.*;
import com.forguta.libs.web.auth0.proxy.model.response.Auth0SignupResponse;
import com.forguta.libs.web.auth0.proxy.model.response.Auth0TokenResponse;
import com.forguta.libs.web.common.feign.HandleFeignError;
import com.forguta.libs.web.common.feign.APIClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth0authenticationapi", url = "${web-advanced.security.auth0.issuer}", configuration = Auth0AuthenticationAPIProxyConfig.class)
public interface Auth0AuthenticationAPIClient extends APIClient {

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @HandleFeignError(OauthTokenClientExceptionHandler.class)
    Auth0TokenResponse clientAuthorize(@RequestBody Auth0ClientTokenRequest auth0ClientTokenRequest);

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @HandleFeignError(OauthTokenClientExceptionHandler.class)
    Auth0TokenResponse authorize(@RequestBody Auth0TokenRequest tokenRequest);

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @HandleFeignError(OauthTokenClientExceptionHandler.class)
    Auth0TokenResponse refreshToken(@RequestBody Auth0RefreshTokenRequest refreshTokenRequest);

    @PostMapping(value = "/dbconnections/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @HandleFeignError(SignupClientExceptionHandler.class)
    Auth0SignupResponse signup(@RequestBody Auth0SignupRequest signupRequest);

    @GetMapping(value = "/v2/logout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String logout(@SpringQueryMap Auth0LogoutRequest logoutRequest);
}
