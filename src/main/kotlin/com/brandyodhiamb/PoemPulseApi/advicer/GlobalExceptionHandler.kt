package com.brandyodhiamb.PoemPulseApi.advicer

import com.brandyodhiamb.PoemPulseApi.exception.BadRequestException
import com.brandyodhiamb.PoemPulseApi.exception.NotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<Map<String, String>> {
        val body = mapOf("error" to ex.message)
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<Map<String, String>> {
        val body = mapOf("error" to ex.message)
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }
}