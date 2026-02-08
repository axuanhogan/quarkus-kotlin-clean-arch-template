package com.axuanhogan.common.config

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import com.axuanhogan.application.port.out.repository.UserRepository
import com.axuanhogan.application.port.out.service.AuthService
import com.axuanhogan.application.use_case.auth.SignInUseCase
import com.axuanhogan.application.use_case.user.CreateUserUseCase
import com.axuanhogan.application.use_case.user.GetUserInfoUseCase

@ApplicationScoped
class UseCaseConfig {

    @Produces
    fun getUserInfoUseCase(userRepository: UserRepository): GetUserInfoUseCase {
        return GetUserInfoUseCase(
            userRepository = userRepository
        )
    }

    @Produces
    fun createUserUseCase(userRepository: UserRepository): CreateUserUseCase {
        return CreateUserUseCase(
            userRepository = userRepository
        )
    }

    @Produces
    fun signInUseCase(authService: AuthService): SignInUseCase {
        return SignInUseCase(
            authService = authService
        )
    }
}
