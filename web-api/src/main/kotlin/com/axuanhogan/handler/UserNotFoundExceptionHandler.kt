package com.axuanhogan.handler

import com.axuanhogan.common.util.RandomCodeUtil
import com.axuanhogan.core.port.`in`.exception.UserNotFoundException
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class UserNotFoundExceptionHandler : ExceptionMapper<UserNotFoundException> {

    override fun toResponse(exception: UserNotFoundException): Response {
        val trackingCode = RandomCodeUtil.gen(length = 8, needTime = false)
        Log.error("[$trackingCode] Catch the UserNotFoundException in UserNotFoundExceptionHandler", exception)
        return ResponseHandler.unprocessableEntity(
            code = "USER_NOT_FOUND",
            message = exception.message ?: "User not found",
            trackingCode = trackingCode
        )
    }
}
