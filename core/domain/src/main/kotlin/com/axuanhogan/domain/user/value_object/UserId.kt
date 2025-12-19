package com.axuanhogan.domain.user.value_object

import java.util.UUID

/**
 * UserId Value Object - Type-safe identifier for User aggregate
 *
 * Prevents primitive obsession by wrapping UUID in a domain-specific type.
 */
data class UserId(val value: UUID) {

    companion object {
        /**
         * Generate a new random UserId
         */
        fun generate(): UserId = UserId(UUID.randomUUID())

        /**
         * Create UserId from existing UUID
         */
        fun from(uuid: UUID): UserId = UserId(uuid)

        /**
         * Create UserId from UUID string
         * @throws IllegalArgumentException if string is not a valid UUID
         */
        fun from(uuidString: String): UserId = UserId(UUID.fromString(uuidString))
    }

    override fun toString(): String = value.toString()
}
