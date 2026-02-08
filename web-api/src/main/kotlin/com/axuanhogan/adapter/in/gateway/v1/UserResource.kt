package com.axuanhogan.adapter.`in`.gateway.v1

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import com.axuanhogan.handler.ResponseHandler
import com.axuanhogan.adapter.`in`.gateway.v1.request.UserResourceRequest
import com.axuanhogan.adapter.`in`.gateway.v1.response.UserResourceResponse
import com.axuanhogan.security.ResourcePermissionChecker
import com.axuanhogan.core.use_case.user.CreateUserUseCase
import com.axuanhogan.core.use_case.user.CreateUserUseCaseInput
import com.axuanhogan.core.use_case.user.GetUserInfoUseCaseInput
import com.axuanhogan.core.use_case.user.GetUserInfoUseCase
import io.quarkus.security.PermissionsAllowed
import java.util.*

@Tag(name = "User")
@Path("/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermissionsAllowed(ResourcePermissionChecker.SCOPE_USER)
@APIResponses(
    APIResponse(
        responseCode = "422",
        description = "Unprocessable Entity",
        content = [
            Content(
                schema = Schema(implementation = ResponseHandler.ErrorResponse::class),
                mediaType = MediaType.APPLICATION_JSON,
                example = """{
                        "error": {
                            "code": "ERROR_CODE",
                            "message": "ERROR_MESSAGE"
                        }
                    }"""
            )
        ]
    ),
    APIResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = [
            Content(
                schema = Schema(implementation = ResponseHandler.ErrorResponse::class),
                mediaType = MediaType.APPLICATION_JSON,
                example = """{
                        "error": {
                            "code": "ERROR_CODE",
                            "message": "ERROR_MESSAGE"
                        }
                    }"""
            )
        ]
    )
)
class UserResource(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val createUserUseCase: CreateUserUseCase,
) {

    @GET
    @Path("/{userId}")
    @Operation(
        summary = "Get user info",
        description = "取得 user info",
    )
    @APIResponses(
        APIResponse(
            responseCode = "200",
            description = "OK",
            content = [
                Content(
                    schema = Schema(
                        type = SchemaType.OBJECT,
                        properties = [
                            SchemaProperty(name = "data", implementation = UserResourceResponse.GetUser::class)
                        ]
                    ),
                    mediaType = MediaType.APPLICATION_JSON,
                )
            ]
        )
    )
    fun getUser(
        @PathParam("userId") userId: String,
    ): Response {
        val result = getUserInfoUseCase.execute(
            input = GetUserInfoUseCaseInput(
                userId = UUID.fromString(userId),
            )
        )

        return ResponseHandler.ok(
            data = UserResourceResponse.GetUser(
                email = result.email,
            )
        )
    }

    @POST
    @Path("/")
    @Operation(
        summary = "Create user",
        description = "建立 user",
    )
    @APIResponses(
        APIResponse(
            responseCode = "200",
            description = "OK",
            content = [
                Content(
                    schema = Schema(
                        type = SchemaType.OBJECT,
                        properties = [
                            SchemaProperty(name = "data", implementation = UserResourceResponse.CreateUser::class)
                        ]
                    ),
                    mediaType = MediaType.APPLICATION_JSON,
                )
            ]
        )
    )
    fun createUser(
        body: UserResourceRequest.CreateUser,
    ): Response {

        val result = createUserUseCase.execute(
            input = CreateUserUseCaseInput(
                email = body.email,
                name = body.name,
            )
        )

        return ResponseHandler.ok(
            data = UserResourceResponse.CreateUser(
                userId = result.userId,
                message = "User created successfully"
            )
        )
    }
}
