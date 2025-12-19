package com.axuanhogan.core.port.`in`.use_case.auth

import com.axuanhogan.core.port.out.service.AuthService

class SignInUseCase(
    private val authService: AuthService
) {

    fun execute(input: SignInUseCaseInput): SignInUseCaseOutput {

        val authorizationToken =  authService.getAuthorizationTokenByPassword(
            username = input.username,
            password = input.password,
            scope = input.scope,
        )

        return SignInUseCaseOutput(
            accessToken = authorizationToken.accessToken,
            expiresIn = authorizationToken.expiresIn,
            refreshToken = authorizationToken.refreshToken,
            refreshExpiresIn = authorizationToken.refreshExpiresIn,
            tokenType = authorizationToken.tokenType,
            notBeforePolicy = authorizationToken.notBeforePolicy,
            sessionState = authorizationToken.sessionState,
            scope = authorizationToken.scope,
        )
    }
}

data class SignInUseCaseInput(
    val username: String,
    val password: String,
    val scope: String? = null,
)

data class SignInUseCaseOutput(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshExpiresIn: Int,
    val tokenType: String,
    val notBeforePolicy: Int,
    val sessionState: String,
    val scope: String,
)
