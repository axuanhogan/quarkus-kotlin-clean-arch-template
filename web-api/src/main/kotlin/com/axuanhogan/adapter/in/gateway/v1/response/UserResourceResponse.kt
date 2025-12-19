package com.axuanhogan.adapter.`in`.gateway.v1.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.util.UUID

interface UserResourceResponse {

    data class GetUser(
        @field:JsonProperty("email")
        @field:Schema(required = true)
        val email: String,
    )

    data class CreateUser(
        @field:JsonProperty("userId")
        @field:Schema(required = true)
        val userId: UUID,

        @field:JsonProperty("message")
        @field:Schema(required = true)
        val message: String,
    )
}
