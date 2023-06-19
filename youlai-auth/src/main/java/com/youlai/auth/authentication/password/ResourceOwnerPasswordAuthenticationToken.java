package com.youlai.auth.authentication.password;

import jakarta.annotation.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.*;

/**
 * 密码授权模式身份验证令牌
 * <p>
 * 封装用户提供的用户名和密码信息
 *
 * @author haoxr
 * @see org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientCredentialsAuthenticationToken
 * @since 3.0.0
 */
public class ResourceOwnerPasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    /**
     * 令牌申请访问范围
     */
    private final Set<String> scopes;

    /**
     * 密码模式身份验证令牌
     *
     * @param clientPrincipal      客户端信息
     * @param scopes               令牌申请访问范围
     * @param additionalParameters 自定义额外参数(用户名和密码)
     */
    public ResourceOwnerPasswordAuthenticationToken(
            Authentication clientPrincipal,
            Set<String> scopes,
            @Nullable Map<String, Object> additionalParameters
    ) {
        super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());

    }

    /**
     * 用户凭证(密码)
     */
    @Override
    public Object getCredentials() {
        return this.getAdditionalParameters().get(OAuth2ParameterNames.PASSWORD);
    }

    public Set<String> getScopes() {
        return scopes;
    }
}