package com.axuanhogan.common.adapter.out.client

import com.axuanhogan.common.mapper.KeycloakOidcExceptionMapper
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.reactive.RestForm
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider

@RegisterRestClient(configKey = "keycloak-oidc")
@RegisterProvider(value = KeycloakOidcExceptionMapper::class)
interface KeycloakOidcClient {

    enum class Client() {
        CLEAN_ARCHITECTURE_IMPLEMENTATION;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/realms/clean-architecture-implementation/protocol/openid-connect/token")
    fun getAuthorizationTokenByPasswordGrant(
        @RestForm("grant_type") grantType: String,
        @RestForm("client_id") clientId: String,
        @RestForm("client_secret") clientSecret: String,
        @RestForm("username") username: String,
        @RestForm("password") password: String,
        @RestForm("scope") scope: String?,
    ): GetTokenByPasswordGrantResponse

    data class GetTokenByPasswordGrantResponse(
        @field:JsonProperty("access_token") val accessToken: String,
        @field:JsonProperty("expires_in") val expiresIn: Int,
        @field:JsonProperty("refresh_token") val refreshToken: String,
        @field:JsonProperty("refresh_expires_in") val refreshExpiresIn: Int,
        @field:JsonProperty("token_type") val tokenType: String,
        @field:JsonProperty("not-before-policy") val notBeforePolicy: Int,
        @field:JsonProperty("session_state") val sessionState: String,
        @field:JsonProperty("scope") val scope: String,
    )
}
