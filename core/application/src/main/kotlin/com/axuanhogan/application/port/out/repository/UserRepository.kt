package com.axuanhogan.application.port.out.repository

import com.axuanhogan.domain.user.User
import com.axuanhogan.domain.user.value_object.UserId

/**
 * Repository port for User aggregate
 *
 * Works with rich domain entities, not anemic DTOs.
 * Follows the Repository pattern from Domain-Driven Design.
 */
interface UserRepository: CommonRepository<User, UserId>
