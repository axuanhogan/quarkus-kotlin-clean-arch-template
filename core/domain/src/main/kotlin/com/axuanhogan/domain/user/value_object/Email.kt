package com.axuanhogan.domain.user.value_object

/**
 * Email Value Object - Encapsulates email validation logic
 *
 * Ensures all Email instances are valid according to business rules.
 * Validation happens at construction time (fail-fast).
 */
data class Email(val value: String) {

    init {
        require(value.isNotBlank()) { "Email cannot be blank" }
        require(isValidEmail(value)) { "Invalid email format: $value" }
        require(value.length <= 64) { "Email cannot exceed 64 characters" }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return emailRegex.matches(email)
    }

    override fun toString(): String = value
}
