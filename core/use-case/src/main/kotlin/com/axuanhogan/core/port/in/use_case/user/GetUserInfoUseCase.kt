package com.axuanhogan.core.port.`in`.use_case.user

import com.axuanhogan.core.port.out.repository.UserRepository
import com.axuanhogan.core.port.`in`.exception.UserNotFoundException
import com.axuanhogan.domain.user.value_object.UserId
import java.util.*

/**
 * Use Case: Get user information
 *
 * Orchestrates domain retrieval and converts to output DTO.
 */
class GetUserInfoUseCase(
    private val userRepository: UserRepository
) {

    fun execute(input: GetUserInfoUseCaseInput): GetUserInfoUseCaseOutput {
        // Convert UUID to UserId value object
        val userId = UserId.from(input.userId)

        // Fetch domain entity
        val user = userRepository.findById(userId)
            ?: throw UserNotFoundException(userId)

        // Convert domain entity to output DTO
        return GetUserInfoUseCaseOutput(
            userId = user.id.value,
            email = user.email.value,
            name = user.name.value,
        )
    }
}

data class GetUserInfoUseCaseInput(
    val userId: UUID,
)

data class GetUserInfoUseCaseOutput(
    val userId: UUID,
    val email: String,
    val name: String,
)
