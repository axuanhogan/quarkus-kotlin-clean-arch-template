package com.axuanhogan.common.mapper

import com.axuanhogan.common.dao.UserDAO
import com.axuanhogan.domain.user.User
import com.axuanhogan.domain.user.value_object.Email
import com.axuanhogan.domain.user.value_object.UserId
import com.axuanhogan.domain.user.value_object.UserName

/**
 * Mapper: Domain Entity (User) ↔ Data Access Object (UserDAO)
 *
 * Converts between rich domain model and persistence model.
 */

/**
 * Convert UserDAO to Domain Entity
 * Used when reading from database
 */
fun UserDAO.toDomainEntity(): User {
    return User.reconstitute(
        id = UserId.from(this.id),
        email = Email(this.email),
        name = UserName(this.name)
    )
}

/**
 * Convert Domain Entity to UserDAO
 * Used when persisting to database
 */
fun User.toDAO(): UserDAO {
    return UserDAO(
        id = this.id.value,
        email = this.email.value,
        name = this.name.value,
    )
}
