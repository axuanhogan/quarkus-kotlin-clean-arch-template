package com.axuanhogan.domain.user.value_object

/**
 * UserName Value Object - Encapsulates name validation logic
 *
 * Ensures all UserName instances are valid according to business rules.
 * Validation happens at construction time (fail-fast).
 */
data class UserName(val value: String) {

    init {
        require(value.isNotBlank()) { "Name cannot be blank" }
        require(value.length in 1..16) { "Name must be between 1 and 16 characters" }
        require(!value.contains(Regex("[<>\"']"))) { "Name contains invalid characters" }
    }

    override fun toString(): String = value
}
