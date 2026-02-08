package com.axuanhogan.application.port.`in`.exception

import com.axuanhogan.domain.user.value_object.UserId

/**
 * Domain exception for user not found scenarios
 *
 * Thrown when attempting to access a user that doesn't exist in the system.
 */
class UserNotFoundException(
    val userId: UserId,
    message: String = "User with id ${userId.value} not found",
    cause: Throwable? = null
) : RuntimeException(message, cause)
