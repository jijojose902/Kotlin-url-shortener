package com.jijojose902.urlshortenerkotlin.controller

import com.jijojose902.urlshortenerkotlin.dto.UrlRequest
import com.jijojose902.urlshortenerkotlin.model.URL
import com.jijojose902.urlshortenerkotlin.service.ShortUrlService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class ShortUrlController(private val shortUrlService: ShortUrlService) {

    @Operation(description = "Rest api for performing URL shortening save activities")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Successful Operation"),
        ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ApiResponse(responseCode = "400", description = "Bad Request")
    )
    @PostMapping("/generate")
    fun getShortUrl(@RequestBody @Valid request: UrlRequest): URL {
        return shortUrlService.saveUrlInfo(request)
    }

}