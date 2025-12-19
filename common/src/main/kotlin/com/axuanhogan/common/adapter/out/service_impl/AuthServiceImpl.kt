package com.axuanhogan.common.adapter.out.service_impl

import com.axuanhogan.common.adapter.out.client.KeycloakOidcClient
import com.axuanhogan.core.port.out.service.AuthService
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class AuthServiceImpl(
    @param:RestClient private val client: KeycloakOidcClient,
    @param:ConfigProperty(name = "quarkus.rest-client.keycloak-oidc.client-secret") val clientSecret: String,
): AuthService {

    private val clientId: String = KeycloakOidcClient.Client.CLEAN_ARCHITECTURE_IMPLEMENTATION.name

    override fun getAuthorizationTokenByPassword(
        username: String,
        password: String,
        scope: String?,
    ): AuthService.AuthorizationToken {
        val result = client.getAuthorizationTokenByPasswordGrant(
            grantType = "password",
            clientId = clientId,
            clientSecret = clientSecret,
            username = username,
            password = password,
            scope = scope
        )

        return AuthService.AuthorizationToken(
            accessToken = result.accessToken,
            expiresIn = result.expiresIn,
            refreshToken = result.refreshToken,
            refreshExpiresIn = result.refreshExpiresIn,
            tokenType = result.tokenType,
            notBeforePolicy = result.notBeforePolicy,
            sessionState = result.sessionState,
            scope = result.scope,
        )
    }
}
