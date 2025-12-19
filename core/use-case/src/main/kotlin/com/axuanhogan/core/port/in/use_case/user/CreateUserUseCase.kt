package com.axuanhogan.core.port.`in`.use_case.user

import com.axuanhogan.core.port.out.repository.UserRepository
import com.axuanhogan.domain.user.User
import com.axuanhogan.domain.user.value_object.Email
import com.axuanhogan.domain.user.value_object.UserName
import java.util.UUID

/**
 * Use Case: Create new user
 *
 * Orchestrates domain logic for user creation.
 * Business rules are enforced in domain entities and value objects.
 */
class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    fun execute(input: CreateUserUseCaseInput): CreateUserUseCaseOutput {
        // Convert input to value objects (validation happens here)
        val email = Email(input.email)
        val name = UserName(input.name)

        // Use domain entity factory method (business logic in domain)
        val user = User.create(
            email = email,
            name = name
        )

        // Persist via repository
        userRepository.save(user)

        return CreateUserUseCaseOutput(
            userId = user.id.value
        )
    }
}

data class CreateUserUseCaseInput(
    val email: String,
    val name: String,
)

data class CreateUserUseCaseOutput(
    val userId: UUID,
)
