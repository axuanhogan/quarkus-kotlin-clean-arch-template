package com.axuanhogan.core.port.out.repository

import com.axuanhogan.domain.user.User
import com.axuanhogan.domain.user.value_object.UserId

/**
 * Repository port for User aggregate
 *
 * Works with rich domain entities, not anemic DTOs.
 * Follows the Repository pattern from Domain-Driven Design.
 */
interface UserRepository {

    /**
     * Find user by id
     * @param id User identifier
     * @return User domain entity or null if not found
     */
    fun findById(id: UserId): User?

    /**
     * Save user entity
     * Handles both create and update scenarios
     * @param user User domain entity to persist
     */
    fun save(user: User)

    /**
     * Delete user entity
     * @param user User domain entity to delete
     */
    fun delete(user: User)
}
