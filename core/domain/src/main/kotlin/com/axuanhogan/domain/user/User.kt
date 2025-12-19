package com.axuanhogan.domain.user

import com.axuanhogan.domain.user.value_object.Email
import com.axuanhogan.domain.user.value_object.UserId
import com.axuanhogan.domain.user.value_object.UserName

/**
 * User Aggregate Root - Rich domain entity with business logic
 *
 * This entity encapsulates all business rules related to User.
 * No anemic data structures - behavior lives here.
 *
 * Design principles:
 * - Private constructor to enforce factory method usage
 * - Immutable public accessors
 * - Business behaviors for state changes
 * - Value objects for type safety and validation
 */
class User private constructor(
    val id: UserId,
    private var _email: Email,
    private var _name: UserName
) {

    // Public immutable accessors
    val email: Email get() = _email
    val name: UserName get() = _name

    companion object {
        /**
         * Factory method: Create a new user (for user registration)
         *
         * Business rule: User must be created with valid email and name
         * @param email Validated email value object
         * @param name Validated name value object
         * @return New User with generated ID
         */
        fun create(email: Email, name: UserName): User {
            return User(
                id = UserId.generate(),
                _email = email,
                _name = name
            )
        }

        /**
         * Factory method: Reconstitute user from persistence (for repository)
         *
         * Used when loading existing users from database.
         * @param id Existing user ID
         * @param email User email
         * @param name User name
         * @return Reconstituted User entity
         */
        fun reconstitute(id: UserId, email: Email, name: UserName): User {
            return User(
                id = id,
                _email = email,
                _name = name
            )
        }
    }

    /**
     * Business behavior: Update user email
     *
     * Encapsulates email change logic and validation
     * @param newEmail New validated email
     * @throws IllegalArgumentException if new email is same as current
     */
    fun updateEmail(newEmail: Email) {
        require(newEmail != _email) { "New email must be different from current email" }
        _email = newEmail
    }

    /**
     * Business behavior: Update user name
     *
     * Encapsulates name change logic and validation
     * @param newName New validated name
     * @throws IllegalArgumentException if new name is same as current
     */
    fun updateName(newName: UserName) {
        require(newName != _name) { "New name must be different from current name" }
        _name = newName
    }

    /**
     * Domain query: Check if user has specific email
     *
     * @param email Email to check
     * @return true if user has this email
     */
    fun hasEmail(email: Email): Boolean = this._email == email

    /**
     * Equality based on business identity (UserId)
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "User(id=$id, email=$email, name=$name)"
}
