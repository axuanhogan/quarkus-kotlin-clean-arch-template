package com.axuanhogan.adapter.`in`.gateway.v1

import com.axuanhogan.handler.ResponseHandler
import com.axuanhogan.adapter.`in`.gateway.v1.request.AuthResourceRequest
import com.axuanhogan.adapter.`in`.gateway.v1.response.AuthResourceResponse
import com.axuanhogan.security.ResourcePermissionChecker
import com.axuanhogan.security.ResourcePermissionChecker.Companion.SCOPE_USER
import com.axuanhogan.core.use_case.auth.SignInUseCase
import com.axuanhogan.core.use_case.auth.SignInUseCaseInput
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.NewCookie
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Auth")
@Path("/v1/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
                            "code": "ERROR",
                            "message": "ERROR_MESSAGE"
                        },
                        "extra": null
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
                            "code": "ERROR",
                            "message": "ERROR_MESSAGE"
                        },
                        "extra": null
                    }"""
            )
        ]
    )
)
class AuthResource (
    @param:ConfigProperty(name = "application.domain-name") val domainName: String,
    private val signInUseCase: SignInUseCase
) {

    @POST
    @Path("/sign-in")
    @Operation(
        summary = "Sign in",
        description = "登入",
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
                            SchemaProperty(
                                name = "data",
                                implementation = AuthResourceResponse.SignIn::class
                            )
                        ]
                    ),
                    mediaType = MediaType.APPLICATION_JSON,
                )
            ]
        )
    )
    fun signIn(
        body: AuthResourceRequest.SignIn
    ) : Response {

        val result = signInUseCase.execute(
            input = SignInUseCaseInput(
                username = body.email,
                password = body.password,
                scope = SCOPE_USER,
            )
        )

        val maxAge = result.expiresIn - 180 // 提前過期避免早於或相等於 session
        val cookie = NewCookie.Builder(ResourcePermissionChecker.authCookieName)
            .domain(domainName)
            .value(result.accessToken)
            .path("/")
            .maxAge(maxAge)
            .httpOnly(true)
            .secure(true)
            .sameSite(NewCookie.SameSite.LAX)
            .build()

        return ResponseHandler.ok(
            data = AuthResourceResponse.SignIn(
                message = "So far so good."
            ),
            cookie = cookie
        )
    }
}
