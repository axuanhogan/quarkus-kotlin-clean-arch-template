package com.axuanhogan.common.adapter.out.repository_impl

import jakarta.enterprise.context.ApplicationScoped
import com.axuanhogan.common.mapper.toDomainEntity
import com.axuanhogan.common.mapper.toDAO
import com.axuanhogan.common.adapter.out.jpa_repository.UserJpaRepository
import com.axuanhogan.application.port.out.repository.UserRepository
import com.axuanhogan.domain.user.User
import com.axuanhogan.domain.user.value_object.UserId
import kotlin.jvm.optionals.getOrNull

/**
 * Adapter: UserRepository implementation
 *
 * Converts between domain entities and JPA DAOs.
 * This is the boundary between domain logic and infrastructure.
 */
@ApplicationScoped
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findById(id: UserId): User? {
        return userJpaRepository.findById(id.value)
            .getOrNull()
            ?.toDomainEntity()
    }

    override fun save(domainEntity: User) {
        userJpaRepository.save(domainEntity.toDAO())
    }

    override fun delete(domainEntity: User) {
        userJpaRepository.delete(domainEntity.toDAO())
    }
}
