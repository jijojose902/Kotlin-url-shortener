package com.jijojose902.urlshortenerkotlin.exception

import com.jijojose902.urlshortenerkotlin.constants.AppErrorCodes
import com.jijojose902.urlshortenerkotlin.constants.AppErrorMessages
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

private val logger = KotlinLogging.logger {}

@ControllerAdvice
class AppExceptionManager {
    @ExceptionHandler
    fun handleInvalidUrlException(ex: InvalidUrlException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            AppErrorCodes.INVALID_URL_ERROR_CODE,
            AppErrorMessages.INVALID_URL_ERROR_MESSAGE
        )
        logger.error { "InvalidUrlException captured at ControllerAdvice :: ${ex.message}" }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            AppErrorCodes.UNEXPECTED_SERVER_ERROR_CODE,
            ex.message
        )
        logger.error { "Exception captured at ControllerAdvice :: ${ex.message}" }
        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}