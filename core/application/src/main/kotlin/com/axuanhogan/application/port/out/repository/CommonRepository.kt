package com.axuanhogan.application.port.out.repository

interface CommonRepository<T, ID> {

    /**
     * Find {T} by id
     * @param id ID identifier
     * @return {T} domain entity or null if not found
     */
    fun findById(id: ID): T?

    /**
     * Save {T} entity
     * Handles both create and update scenarios
     * @param domainEntity {T} domain entity to persist
     */
    fun save(domainEntity: T)

    /**
     * Delete {T} entity
     * @param domainEntity {T} domain entity to delete
     */
    fun delete(domainEntity: T)
}
