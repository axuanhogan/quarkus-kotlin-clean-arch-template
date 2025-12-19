package com.axuanhogan.handler

import com.axuanhogan.common.util.RandomCodeUtil
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

/**
 * Exception handler for validation errors from value objects
 *
 * Value objects throw IllegalArgumentException when constructed with invalid data.
 * This handler maps those exceptions to HTTP 422 responses.
 */
@Provider
class IllegalArgumentExceptionHandler : ExceptionMapper<IllegalArgumentException> {

    override fun toResponse(exception: IllegalArgumentException): Response {
        val trackingCode = RandomCodeUtil.gen(length = 8, needTime = false)
        Log.error("[$trackingCode] Catch the IllegalArgumentException in IllegalArgumentExceptionHandler", exception)
        return ResponseHandler.unprocessableEntity(
            code = "VALIDATION_ERROR",
            message = exception.message ?: "Validation failed",
            trackingCode = trackingCode
        )
    }
}
