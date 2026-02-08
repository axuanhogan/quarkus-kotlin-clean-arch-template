package com.axuanhogan.application.port.out.service

interface AuthService {

    fun getAuthorizationTokenByPassword(
        username: String,
        password: String,
        scope: String? = null,
    ): AuthorizationToken

    data class AuthorizationToken(
        val accessToken: String,
        val expiresIn: Int,
        val refreshToken: String,
        val refreshExpiresIn: Int,
        val tokenType: String,
        val notBeforePolicy: Int,
        val sessionState: String,
        val scope: String,
    )
}
