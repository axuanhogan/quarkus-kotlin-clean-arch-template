package com.axuanhogan.application.port.out.repository

interface CommonRepository<T , ID> {

    fun findById(id: ID): T?

    fun save(pdo: T)

    fun delete(pdo: T)
}
